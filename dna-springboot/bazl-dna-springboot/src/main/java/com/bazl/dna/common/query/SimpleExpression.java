package com.bazl.dna.common.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.bazl.dna.util.DateUtil;

/**
 * 简单条件表达式
 * @author zhaoyong
 */
public class SimpleExpression implements Criterion {

	/**
	 * 属性名
	 */
	private final String fieldName;

	/**
	 * 对应值
	 */
	private final Object value;

	/**
	 * 计算符
	 */
	private final Operator operator;

	protected SimpleExpression(String fieldName, Object value, Operator operator) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getValue() {
		return value;
	}

	public Operator getOperator() {
		return operator;
	}

	@Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Path expression;
		if (fieldName.contains(".")) {
			String[] names = StringUtils.split(fieldName, ".");
			if(names[0].contains("List")){
				expression = root.join(names[0], JoinType.LEFT);
			}else{
				expression = root.get(names[0]);
			}
			for (int i = 1; i < names.length; i++) {
				expression = expression.get(names[i]);
			}
		} else {
			expression = root.get(fieldName);
		}

		switch (operator) {
		case EQ:
			return builder.equal(expression, value);
		case NE:
			return builder.notEqual(expression, value);
		case LIKE:
			return builder.like((Expression<String>) expression, "%" + value + "%");
		case LT:
			return builder.lessThan(expression, (Comparable) value);
		case GT:
			return builder.greaterThan(expression, (Comparable) value);
		case LTE:
			return builder.lessThanOrEqualTo(expression, (Comparable) value);
		case GTE:
			return builder.greaterThanOrEqualTo(expression, (Comparable) value);
		case BE:
			String param = String.valueOf(value);
			String[] array = StringUtils.split(param, "|");
			return builder.between(expression, DateUtil.stringToDate(array[0], DateUtil.FULL_TIME_FORMAT),
					DateUtil.stringToDate(array[1], DateUtil.FULL_TIME_FORMAT));
		default:
			return null;
		}
	}

}
