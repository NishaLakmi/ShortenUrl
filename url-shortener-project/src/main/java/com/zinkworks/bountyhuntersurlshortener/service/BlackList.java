package com.zinkworks.bountyhuntersurlshortener.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackList {

    public static boolean checkBlackList(String originalURL) throws IOException {
        List<String> blackList = new ArrayList<>();
        // Load the file from the classpath
        InputStream inputStream = BlackList.class.getResourceAsStream("/keywordsForBlacklist.txt");
        if (inputStream == null) {
            throw new FileNotFoundException("keywordsForBlacklist.txt not found in classpath");
        }
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String str = scanner.next();
            blackList.add(str);
        }
        scanner.close();
        inputStream.close();

        for (String substring : blackList) {
            if (originalURL.contains(substring)) {
                return true;
            }
        }
        return false;
    }
}


