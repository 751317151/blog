package com.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date2StringUtil {

    public static String date2String(Date date){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}
