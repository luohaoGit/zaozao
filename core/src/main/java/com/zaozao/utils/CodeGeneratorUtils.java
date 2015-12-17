package com.zaozao.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by luohao on 15/12/17.
 */
public class CodeGeneratorUtils {

    private static String[] nums = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String generateSmsCode(){
        List list = Arrays.asList(nums);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

}
