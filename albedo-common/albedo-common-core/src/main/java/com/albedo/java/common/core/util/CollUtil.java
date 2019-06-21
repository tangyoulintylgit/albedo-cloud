package com.albedo.java.common.core.util;

import cn.hutool.core.bean.BeanUtil;
import com.albedo.java.common.core.vo.ComboData;
import com.albedo.java.common.core.vo.SelectResult;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@UtilityClass
@Slf4j
public class CollUtil extends cn.hutool.core.collection.CollUtil {

	/**
	 * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
	 */
	public static String convertToString(final Collection collection, final String separator) {
		return StringUtils.join(collection, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 */
	public static String convertToString(final Collection collection, final String prefix, final String postfix) {
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}
	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 *
	 * @param collection   来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List extractToList(final Collection collection, final String propertyName) {
		List list = Lists.newArrayList();
		try {
			if (collection != null) {
				Object item = null;
				for (Object obj : collection) {
					if (obj instanceof Map) {
						item = ((Map) obj).get(propertyName);
					} else {
						item = BeanUtil.getFieldValue(obj, propertyName);
					}
					if (item != null) {
						list.add(item);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return list;
	}

	public static List<ComboData> convertComboDataList(List<?> dataList, String idFieldName, String nameFieldName) {
		List<ComboData> comboDataList = Lists.newArrayList();
		dataList.forEach(item -> comboDataList.add(
			new ComboData(StringUtil.toStrString(BeanUtil.getFieldValue(item, idFieldName)),
				StringUtil.toStrString(BeanUtil.getFieldValue(item, nameFieldName)))));
		return comboDataList;
	}

	public static List<SelectResult> convertSelectDataList(List<?> dataList, String idFieldName, String nameFieldName) {
		List<SelectResult> selectResultList = Lists.newArrayList();
		dataList.forEach(item -> selectResultList.add(new SelectResult(StringUtil.toStrString(BeanUtil.getFieldValue(item, idFieldName)),
			StringUtil.toStrString(BeanUtil.getFieldValue(item, nameFieldName)))));
		return selectResultList;
	}
}