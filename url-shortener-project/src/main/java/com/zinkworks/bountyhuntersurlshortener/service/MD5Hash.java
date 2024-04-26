package com.zinkworks.bountyhuntersurlshortener.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Hash {
    public static String MD5HashingMethod(String originalUrl) {
        String finalShortUrl = "";
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5"); // Create an instance of Message Digest. Message Digests are secure one-way hash functions
            // it outputs a fixed length hash value.
            // 'getInstance' returns a message digest object which implements the specified algorithm (md5)
            byte[] inputBytes = originalUrl.getBytes();    // convert original url to bytes
            md.update(inputBytes);                         // update the message digest with the input bytes
            byte[] md5Bytes = md.digest();                 // Calculate the MD5 hash, returns an array of bytes 'md5Bytes'
            // digest() performs final hashing computations.

            String base64Encoded = Base64.getEncoder().encodeToString(md5Bytes);   // encodes the byte array 'md5Bytes' to base64
            finalShortUrl = base64Encoded.substring(0, 7);    // Substring of base64Encoded with first 7 characters

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }
        return finalShortUrl;
    }
}
