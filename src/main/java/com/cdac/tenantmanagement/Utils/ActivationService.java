package com.cdac.tenantmanagement.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class ActivationService {

    private final static String key = "Megh@cdac.in";

    public String generateActivationCode(String email) {

        String input = email + key;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
