package com.sc.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class StringToSqlDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(source == null || "".equals(source.trim()))
            return null;
        return Date.valueOf(source);
    }
}
