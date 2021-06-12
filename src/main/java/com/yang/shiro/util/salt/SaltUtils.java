package com.yang.shiro.util.salt;

import java.util.Random;

public class SaltUtils {
    /**
     * 获取盐
     *
     * @param n
     * @return
     */
    public static String getSalt(int n) {
        char[] chars = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                "1234567890!@#$%^&*()_+").toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
