package com.zinkworks.bountyhuntersurlshortener.service;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class UrlValiditationTest {

    @Test
    void returnIsNotNull() throws MalformedURLException {
        assertNotNull(UrlValiditation.isValid("http://example.com"));
    }

}