package com.wusw.utils;

import java.util.Random;

public class RandomUtils {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";
    public static final String NUMBERANDCAPITALCHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    
    /** 
     * 返回一个定长的带因子的固定的随机字符串(只包含大小写字母、数字)
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */
    public static String createStringByKey(int length, int channel) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random(channel);
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    /** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */
    public static String createString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    /** 
     * 返回一个定长的随机纯字母字符串(只包含大小写字母) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */
    public static String createLetterString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(52)));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机大写字母和数字字符串(只包含大写字母和数字) 
     * 
     * @param length
     * @return
     */
    public static String createUpperAndNumString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERANDCAPITALCHAR.charAt(random.nextInt(36)));
        }
        return sb.toString();
    }

    
    
    /** 
     * 返回一个定长的随机纯小写字母字符串 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */
    public static String createLowerString(int length) {
        return createLetterString(length).toLowerCase();
    }

    /** 
     * 返回一个定长的随机纯大写字母字符串 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */
    public static String createUpperString(int length) {
        return createLetterString(length).toUpperCase();
    }

    /** 
     * 生成一个定长的纯0字符串 
     *  
     * @param length 
     *            字符串长度 
     * @return 纯0字符串 
     */
    public static String createZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /** 
     * 根据数字生成一个定长的字符串，长度不够前面补0 
     *  
     * @param num 
     *            数字 
     * @param fixdlenth 
     *            字符串长度 
     * @return 定长的字符串 
     */
    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(createZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /** 
     * 每次生成的len位数都不相同 
     *  
     * @param param 
     * @return 定长的数字 
     */
    public static int getNotSimple(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }
}
