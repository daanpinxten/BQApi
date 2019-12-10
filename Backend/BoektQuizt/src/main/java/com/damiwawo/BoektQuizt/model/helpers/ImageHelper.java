package com.damiwawo.BoektQuizt.model.helpers;

import java.util.Base64;

public class ImageHelper {

    public static byte[] decode(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);

        return bytes;
    }

    public static String encode(byte[] bytes) {
        byte[] base64String = Base64.getEncoder().encode(bytes);

        return new String(base64String);
    }
}
