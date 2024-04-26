package com.zinkworks.bountyhuntersurlshortener.exceptions;

public class BlackListedUrlException extends Exception{
    public BlackListedUrlException(String message) {
        super(message);
    }
}
