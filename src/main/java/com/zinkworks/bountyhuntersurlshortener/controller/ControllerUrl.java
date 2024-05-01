package com.zinkworks.bountyhuntersurlshortener.controller;


import com.zinkworks.bountyhuntersurlshortener.exceptions.BlackListedUrlException;
import com.zinkworks.bountyhuntersurlshortener.exceptions.InvalidUrlException;
import com.zinkworks.bountyhuntersurlshortener.exceptions.UrlNotFoundException;
import com.zinkworks.bountyhuntersurlshortener.model.BountyUrlTable;
import com.zinkworks.bountyhuntersurlshortener.repository.RepositoryUrl;
import com.zinkworks.bountyhuntersurlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import java.util.List;



@RestController
@RequestMapping( path = "api/v1/BountyURL")
public class ControllerUrl {


    //Variable declaration from ServiceUrl
    @Autowired
    private UrlService urlService;
    private RepositoryUrl repositoryUrl;


    @GetMapping
    public List<BountyUrlTable> getAllUrls() {
        return urlService.getAllUrlInfo();
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) throws UrlNotFoundException {
        String originalUrl = urlService.findOriginalUrl(shortUrl);
        if (originalUrl != null) {
            return ResponseEntity.ok(originalUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }//test3
    @PostMapping
    public ResponseEntity<String> createShortUrl(@RequestBody String originalUrl )
            throws IOException, BlackListedUrlException, InvalidUrlException {
        String shortUrl = urlService.addNewUrl(originalUrl);
        if(shortUrl.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shorten URL not created");
        }
        else {

            return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
        }
    }

//    @DeleteMapping(path = "/{id}")
//@DeleteMapping(path = "/{short_url}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//   public void deleteUrlById(@PathVariable("id") Long id){
//
//    repositoryUrl.deleteById(id);
//
//   }

    @DeleteMapping("{short_url}")
    public ResponseEntity<String> deleteShortUrl(@PathVariable String short_url) {

        //The URL to delete passed to server and got the responses.
        boolean deleted = urlService.deleteUrl(short_url);


        if (deleted) {
            //Delete successful completed.
            return ResponseEntity.ok("Short URL deleted");

        } else {
            //Build error message
            return ResponseEntity.notFound().build();

        }

    }
}