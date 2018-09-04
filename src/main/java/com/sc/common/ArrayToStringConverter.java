package com.sc.common;

import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/**
 * SpringMVC自定义转换器
 * 将页面数组类型参数转为逗号分隔的字符串形式
 * 该demo中没有启用
 * @author Administrator
 *
 */
@Component
public class ArrayToStringConverter implements Converter<String[],String>{

	@Override
	public String convert(String[] arr) {
		System.out.println(Arrays.toString(arr));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if(i < arr.length-1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
