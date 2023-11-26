package com.wesley.converter;

import org.springframework.core.convert.converter.Converter;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {
        try{
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
