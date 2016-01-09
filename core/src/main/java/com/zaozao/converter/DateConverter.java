package com.zaozao.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ReadingConverter
public class DateConverter implements Converter<String, Date> {

    public static final Logger error = LoggerFactory.getLogger("ERROR");

    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            error.error(e.getMessage(), e);
        }
        return null;
    }

}