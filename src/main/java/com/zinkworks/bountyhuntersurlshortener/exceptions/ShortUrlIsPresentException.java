package com.zinkworks.bountyhuntersurlshortener.exceptions;

public class ShortUrlIsPresentException extends Exception{
    public ShortUrlIsPresentException(String message) {
        super(message);
    }
}
