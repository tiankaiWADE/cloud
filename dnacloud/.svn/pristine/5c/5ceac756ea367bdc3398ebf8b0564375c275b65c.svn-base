package com.bazl.dna.sys.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.cache.RedisCache;
import com.bazl.dna.common.cache.annotation.RedisCacheAble;
import com.bazl.dna.common.cache.annotation.RedisCachePut;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.common.jwt.JwtUtils;
import com.bazl.dna.common.query.Criteria;
import com.bazl.dna.common.query.QueryCriteriaBean;
import com.bazl.dna.common.query.QueryUtils;
import com.bazl.dna.common.query.Restrictions;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.dao.ISysUserJPADao;
import com.bazl.dna.sys.dao.ISysUserJobJPADao;
import com.bazl.dna.sys.dao.ISysUserOrgJPADao;
import com.bazl.dna.sys.dao.ISysUserRoleJPADao;
import com.bazl.dna.sys.entity.SysJob;
import com.bazl.dna.sys.entity.SysOrganization;
import com.bazl.dna.sys.entity.SysRole;
import com.bazl.dna.sys.entity.SysUser;
import com.bazl.dna.sys.entity.SysUserJob;
import com.bazl.dna.sys.entity.SysUserOrg;
import com.bazl.dna.sys.entity.SysUserRole;
import com.bazl.dna.sys.service.ISysJobService;
import com.bazl.dna.sys.service.ISysOrganizationService;
import com.bazl.dna.sys.service.ISysRoleService;
import com.bazl.dna.sys.service.ISysUserService;
import com.bazl.dna.util.DataUtils;
import com.bazl.dna.util.DateUtil;
import com.bazl.dna.util.RequestUtils;
import com.google.common.base.Strings;

/**
 * 系统用户接口实现类
 * @author zhaoyong
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

	private static final String CACHE_LOGIN_NAME = "Auth:Login";
	private static final String CACHE_NAME = "SysUser";

	@Autowired
    private RedisCache redisCache;

	@Autowired
	private ISysUserJPADao userJPADao;

	@Autowired
	private ISysRoleService sysRoleService;

	@Autowired
	private ISysOrganizationService sysOrganizationService;

	@Autowired
	private ISysJobService sysJobService;

	@Autowired
	private ISysUserRoleJPADao userRoleJPADao;

	@Autowired
	private ISysUserOrgJPADao userOrgJPADao;

	@Autowired
	private ISysUserJobJPADao userJobJPADao;

	@Value("${ACCESS_TOKEN_SECRET}")
	public String accessTokenSecret;

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	public JSONObject login(String userName, String p) {
		String password = DigestUtils.md5Hex(p).toUpperCase();
		SysUser user = getUserByUserName(userName, password);
		if (user != null) {
			String key = CACHE_LOGIN_NAME + ":" + userName;
			String cache = redisCache.getCacheObject(key);
			if (cache != null) {
				return JSON.parseObject(cache);
			}

			Date time = DateUtil.after(new Date(), 30 * 24, Calendar.HOUR);
			JSONObject tokenJson = new JSONObject();
			tokenJson.put(RequestUtils.TOKEN_TYPE, "login");

			tokenJson.put(SysConstants.ID, user.getId());
			tokenJson.put(SysConstants.USER_NAME, user.getUserName());
			tokenJson.put(SysConstants.PASS, user.getPassword());
			tokenJson.put(SysConstants.IS_ADMIN, user.getIsAdmin());
			tokenJson.put(SysConstants.USER_TYPE, user.getUserType());

			List<String> roles = userRoleJPADao.findUserRoleByUserId(user.getId());
			List<String> orgs = userOrgJPADao.findUserOrgByUserId(user.getId());
			List<String> jobs = userJobJPADao.findUserJobByUserId(user.getId());
			tokenJson.put(SysConstants.ROLES, roles);
			tokenJson.put(SysConstants.JOBS, jobs);
			tokenJson.put(SysConstants.ORGS, orgs);
			String accessToken = JwtUtils.generateToken(tokenJson.toJSONString(), time, accessTokenSecret);

			JSONObject result = new JSONObject();
			result.put(RequestUtils.ACCESS_TOKEN, accessToken);
			result.put(RequestUtils.EXPIRED, time.getTime());

			result.put(SysConstants.REAL_NAME, user.getRealName());
			result.put(SysConstants.USER_ID, user.getId());
			result.put(SysConstants.IS_ADMIN, user.getIsAdmin());
			result.put(SysConstants.AVATAR, user.getAvatar());
			result.put(SysConstants.ROLES, roles);
			result.put(SysConstants.JOBS, jobs);
			result.put(SysConstants.ORGS, orgs);
			redisCache.setCacheObject(key, JSON.toJSONString(result), PublicConstants.EXPIRE_TIME, TimeUnit.MINUTES);

			return result;
		}
		return null;
	}

	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public Page<SysUser> pageList(JSONObject paramJson) {
		try {
			QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
			Pageable pageable = QueryUtils.buildPageRequest(data);
			Page<SysUser> page = userJPADao.findAll(getCriteria(paramJson), pageable);
			page.getContent().forEach(this::setName);
			return page;
		} catch (Exception e) {
			LOGGER.error("Error pageList:", e);
		}
		return null;
	}
	
	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public List<SysUser> findList(JSONObject paramJson) {
		Criteria<SysUser> criteria = getCriteria(paramJson);
		criteria.add(Restrictions.eq(SysConstants.STATUS, PublicConstants.STATUS_YES));
		return userJPADao.findAll(getCriteria(paramJson));
	}
	
	private Criteria<SysUser> getCriteria(JSONObject paramJson) {
		Criteria<SysUser> criteria = new Criteria<>();
		criteria.add(Restrictions.like(SysConstants.USER_NAME, paramJson.getString(SysConstants.USER_NAME)));
		criteria.add(Restrictions.like(SysConstants.REAL_NAME, paramJson.getString(SysConstants.REAL_NAME)));
		if (paramJson.get(SysConstants.BEGIN_TIME) != null && paramJson.get(SysConstants.END_TIME) != null) {
			criteria.add(Restrictions.between(SysConstants.CREATE_TIME,
				paramJson.getString(SysConstants.BEGIN_TIME) + "|" + paramJson.getString(SysConstants.END_TIME)));
		}
		return criteria;
	}

	@Override
	@RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	public SysUser getById(String id) {
		try {
			Optional<SysUser> optional = userJPADao.findById(id);
			if (optional.isPresent()) {
				SysUser entity = optional.get();
				setName(entity);
				return entity;
			}
		} catch (Exception e) {
			LOGGER.error("Error getById:", e);
		}
		return null;
	}

	private SysUser getUserByUserName(String userName, String password) {
		Criteria<SysUser> criteria = new Criteria<>();
		criteria.add(Restrictions.eq(SysConstants.USER_NAME, userName));
		criteria.add(Restrictions.eq(SysConstants.PASS, password));
		criteria.add(Restrictions.eq(SysConstants.STATUS, PublicConstants.STATUS_YES));
		Optional<SysUser> optional = userJPADao.findOne(criteria);
		return optional.orElse(null);
	}
	
	private void setName(SysUser entity) {
		List<String> roleList = userRoleJPADao.findUserRoleByUserId(entity.getId());
		entity.setRoles(roleList);
		entity.setRoleNameList(roleList.stream().map(s -> sysRoleService.getById(s).getRoleName()).collect(Collectors.toList()));

		List<String> jobList = userJobJPADao.findUserJobByUserId(entity.getId());
		entity.setJobs(jobList);
		entity.setJobNameList(jobList.stream().map(s -> sysJobService.getById(s).getJobName()).collect(Collectors.toList()));

		List<String> orgList = userOrgJPADao.findUserOrgByUserId(entity.getId());
		entity.setOrgs(orgList);
		entity.setOrgNameList(orgList.stream().map(s -> sysOrganizationService.getById(s).getOrgName()).collect(Collectors.toList()));
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public SysUser saveUser(SysUser entity) {
		try {
			Timestamp time = new Timestamp(System.currentTimeMillis());
			entity.setActiveStatus(PublicConstants.STATUS_YES);
			entity.setCreateTime(time);
			entity.setLastTime(time);

			List<String> roles = entity.getRoles();
			List<String> jobs = entity.getJobs();
			List<String> orgs = entity.getOrgs();
			entity = userJPADao.save(entity);

			// 删除用户角色关联
			userRoleJPADao.deleteUserRoleByUserId(entity.getId());
			for (String id : roles) {
				SysRole sysRole = sysRoleService.getById(id);
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setSysRole(sysRole);
				sysUserRole.setSysUser(entity);
				userRoleJPADao.save(sysUserRole);
			}

			// 删除用户职位关联
			userJobJPADao.deleteUserJobByUserId(entity.getId());
			for (String id : jobs) {
				SysJob sysJob = sysJobService.getById(id);
				SysUserJob sysUserJob = new SysUserJob();
				sysUserJob.setSysJob(sysJob);
				sysUserJob.setSysUser(entity);
				sysUserJob.setPositionOrder(1);
				sysUserJob.setStatus(PublicConstants.STATUS_YES);
				sysUserJob.setCreateTime(time);
				sysUserJob.setUpdateTime(time);
				userJobJPADao.save(sysUserJob);
			}

			// 删除用户单位关联
			userOrgJPADao.deleteUserOrgByUserId(entity.getId());
			for (String id : orgs) {
				SysOrganization sysOrg = sysOrganizationService.getById(id);
				SysUserOrg sysUserOrg = new SysUserOrg();
				sysUserOrg.setSysOrganization(sysOrg);
				sysUserOrg.setSysUser(entity);
				sysUserOrg.setPositionOrder(1);
				sysUserOrg.setStatus(PublicConstants.STATUS_YES);
				sysUserOrg.setCreateTime(time);
				sysUserOrg.setUpdateTime(time);
				userOrgJPADao.save(sysUserOrg);
			}
			entity.setRoles(roles);
			entity.setJobs(jobs);
			entity.setOrgs(orgs);
			setName(entity);
			return entity;
		} catch (Exception e) {
			LOGGER.error("Error saveUser: ", e);

			throw new DnaRuntimeException();
		}
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Integer deleteById(String id) {
		userJobJPADao.deleteUserJobByUserId(id);
		userOrgJPADao.deleteUserOrgByUserId(id);
		userRoleJPADao.deleteUserRoleByUserId(id);
		userJPADao.deleteById(id);
		return 1;
	}

	@Override
	public long checkUserName(String userId, String userName) {
		try {
			Criteria<SysUser> criteria = new Criteria<>();
			criteria.add(Restrictions.eq(SysConstants.USER_NAME, DataUtils.trimToNull(userName)));
			if (!Strings.isNullOrEmpty(userId)) {
				criteria.add(Restrictions.ne(SysConstants.ID, userId));
			}
			return userJPADao.count(criteria);
		} catch (Exception e) {
			LOGGER.error("Error checkUserName:", e);
		}
		return 0L;
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Integer editPassword(String id, String p) {
		Optional<SysUser> optional = userJPADao.findById(id);
		if (optional.isPresent()) {
			optional.get().setPassword(DigestUtils.md5Hex(p).toUpperCase());
			userJPADao.save(optional.get());
			return 1;
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public Integer editStatus(String id, String status) {
		Optional<SysUser> optional = userJPADao.findById(id);
		if (optional.isPresent()) {
			optional.get().setStatus(status);
			userJPADao.save(optional.get());
			return 1;
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@RedisCachePut(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public SysUser update(SysUser sysUser) {
		SysUser result = userJPADao.save(sysUser);

		String key = CACHE_LOGIN_NAME + ":" + sysUser.getUserName();
		redisCache.deleteObject(key);
		return result;
	}

}
