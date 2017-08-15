package com.opple.fa.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class VerifyAnnotationMyLimit {
    private MyLimit limit;
    private Class obj;
    private ArrayList<Exception> ListExceptions;

    public VerifyAnnotationMyLimit(Object object) throws Exception {
        obj = object.getClass();
        ListExceptions = new ArrayList<Exception>();
        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            // 设置field为private时设置可以访问权限,  
            field.setAccessible(true);
            // 开始验证  
            verify(field, object);
            // 重新对field设置权限  
            field.setAccessible(false);

        }
    }

    public ArrayList<Exception> getListExceptions() {
        return ListExceptions;
    }

    private void verify(Field field, Object object) throws Exception {
        limit = field.getAnnotation(MyLimit.class);
        // 检查这个field是否被MyLimit注释  
        if (!field.isAnnotationPresent(MyLimit.class)) {
            return;
        }
        // 取出object对象中的field值  
        Object objectvalue = field.get(object);
        String description = "".equals(limit.description()) ? field.getName()
                : limit.description();

        // 先验证是否为必填，如果必填且value为空，则抛出异常，如果不是必填，那我们  
        if (limit.isNotNull()) {
            if ("".equals(objectvalue) || objectvalue == null) {
                ListExceptions.add(new Exception(description + "不能为空"));
            }
        } else {
            // 不是必填项先检查是否有值，没有就直接返回，有就继续向下验证  
            if (objectvalue == null) {
                return;
            }
        }

        if (limit.isInt()) {
            int value = Integer.valueOf(objectvalue.toString());
            if (value < limit.MinNum() || value > limit.MaxNum()) {
                ListExceptions.add(new Exception(description + "必须在"
                        + limit.MinNum() + "到" + limit.MaxNum() + "之间"));
            }
            return;
        }
        int cou = this.getWordCount(objectvalue.toString());
        if (cou < limit.MinLength()
                || cou > limit.MaxLength()) {
            ListExceptions.add(new Exception(description + "长度必须在"
                    + limit.MinLength() + "到" + limit.MaxLength() + "之间"));
        }
    }

    private int getWordCount(String s) {
        s = s.trim();
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        } 
       /* if(length>c){
            return "输入字符不能超过"+c+"个字节,现在是"+length+"个字节";
        } */
        return length;
    }
}  