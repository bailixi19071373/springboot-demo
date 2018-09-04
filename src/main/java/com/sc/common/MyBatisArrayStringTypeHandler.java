package com.sc.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * MyBtais自定义类型转换器
 * 将数组类型参数与逗号分隔的字符串形式互转
 * @author Administrator
 *
 */
public class MyBatisArrayStringTypeHandler implements TypeHandler<String[]>{

	@Override
	public String[] getResult(ResultSet rs, String col) throws SQLException {
		String value = rs.getString(col);
		if(value == null)
			return null;
		String[] arr = value.split(",");
		return arr;
	}

	@Override
	public String[] getResult(ResultSet rs, int colIndex) throws SQLException {
		String value = rs.getString(colIndex);
		if(value == null)
			return null;
		String[] arr = value.split(",");
		return arr;
	}

	@Override
	public String[] getResult(CallableStatement cs, int colIndex) throws SQLException {
		// TODO Auto-generated method stub
		String value = cs.getString(colIndex);
		if(value == null)
			return null;
		String[] arr = value.split(",");
		return arr;
	}

	@Override
	public void setParameter(PreparedStatement ps, int parameterIndex, String[] arr, JdbcType jt) throws SQLException {
		// TODO Auto-generated method stub
		if(arr == null) {
			ps.setNull(parameterIndex, Types.VARCHAR);
		}
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
				if(i < arr.length-1) {
					sb.append(",");
				}
			}
			ps.setString(parameterIndex, sb.toString());
		}
	}

}
