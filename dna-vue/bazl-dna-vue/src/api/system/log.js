import request from '@/utils/request'

// 查询日志列表
export function listLog(data) {
  return request({
    url: '/system/log/list',
    method: 'post',
    data: data
  })
}

// 查询日志详细
export function getLog(id) {
  return request({
    url: '/system/log/get/' + id,
    method: 'get'
  })
}

// 保存日志
export function saveLog(data) {
  return request({
    url: '/system/log/save',
    method: 'put',
    data: data
  })
}

// 删除日志
export function delLog(data) {
  return request({
    url: '/system/log/delete',
    method: 'delete',
    data: data
  })
}

