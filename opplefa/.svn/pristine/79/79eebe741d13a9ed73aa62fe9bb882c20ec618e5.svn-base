package com.opple.fa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

/**
 * easyui的datagrid模型
 *
 * @author zhangtao
 */
public class StringDataCas implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6509933955930424372L;

    public static Date StringToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String DateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(date);
        return dateString;
    }

    public static String TimeToString(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String dateString = sdf.format(dateTime);
        return dateString;
    }

    public static boolean notEmpty(Object o) {
        if (null == o || "".equals(o.toString().trim())) {
            return false;
        }
        return true;
    }
}
