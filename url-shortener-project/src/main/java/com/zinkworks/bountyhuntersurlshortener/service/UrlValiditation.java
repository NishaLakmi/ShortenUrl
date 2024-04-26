package com.zinkworks.bountyhuntersurlshortener.service;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
public class UrlValiditation {
    public static boolean isValid(String originalUrl) throws MalformedURLException {
        try{
            new URL(originalUrl).toURI();
            return true;
        }
        catch (URISyntaxException e) {
            return false;
        }
    }
}
