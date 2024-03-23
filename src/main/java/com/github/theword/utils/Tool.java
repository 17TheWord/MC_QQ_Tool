package com.github.theword.utils;

public class Tool {
    /**
     * 字符串转为 unicode 编码
     *
     * @param string 字符串
     * @return unicode编码
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }

    public static String unicodeDecode(String unicodeString) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicodeString.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // Convert hex to decimal
            int data = Integer.parseInt(hex[i], 16);
            // Convert the decimal to character
            string.append((char) data);
        }
        return string.toString();
    }

}
