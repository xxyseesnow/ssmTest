package com.itheima.ssm.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
    //日期转换成字符串
    public static String dateToString(Date date,String str){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        String format = simpleDateFormat.format(date);
        return format;

    }

    //字符串转换成日期
    public static Date stringToDate(String str,String patter) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);
        Date date = simpleDateFormat.parse(str);
        return date;
    }


}
