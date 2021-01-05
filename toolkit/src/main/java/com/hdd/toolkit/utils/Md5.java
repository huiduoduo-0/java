package com.hdd.toolkit.utils;


import com.hdd.toolkit.model.User;

import java.security.MessageDigest;

public class Md5
{

    public static String encode(User user)
    {
        byte[] unencodedPassword = user.getUserPassword().getBytes();
        MessageDigest md = null;
        try
        {
            String algorithm="MD5";
            md = MessageDigest.getInstance(algorithm);
        }
        catch (Exception e)
        {
            return user.getUserPassword();
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++)
        {
            if ((encodedPassword[i] & 0xFF) < 16)
            {
                buf.append("0");
            }
            buf.append(Long.toString(encodedPassword[i] & 0xFF, 16));
        }
        return buf.toString();
    }
}