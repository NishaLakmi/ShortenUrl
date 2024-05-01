package com.zinkworks.bountyhuntersurlshortener.service;

import com.zinkworks.bountyhuntersurlshortener.exceptions.BlackListedUrlException;
import com.zinkworks.bountyhuntersurlshortener.exceptions.InvalidUrlException;
import com.zinkworks.bountyhuntersurlshortener.exceptions.UrlNotFoundException;
import com.zinkworks.bountyhuntersurlshortener.model.BountyUrlTable;
import com.zinkworks.bountyhuntersurlshortener.repository.RepositoryUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UrlService {//

    @Autowired
    private RepositoryUrl repositoryUrl;  // creating object of RepositoryUrl class
    public List<BountyUrlTable> getAllUrlInfo() {
        return repositoryUrl.findAll();    // returns all database info from the repository class.
    }
//test
    public String addNewUrl(String originalUrl) throws InvalidUrlException, IOException, BlackListedUrlException {
        boolean blackList = BlackList.checkBlackList(originalUrl);
        String createdShortUrl = "";
        if (UrlValiditation.isValid(originalUrl)) {
            if (blackList) {
                throw new BlackListedUrlException("The url you entered has keywords on our blacklist. Please try again.");
            } else {
                createdShortUrl = UriComponentsBuilder.fromHttpUrl(originalUrl)
                        .replaceQuery(null)
                        .build()
                        .toUriString();

                createdShortUrl = MD5Hash.MD5HashingMethod(originalUrl);
                BountyUrlTable newRecord = new BountyUrlTable();
                newRecord.setShortUrl(createdShortUrl);
                newRecord.setOriginalUrl(originalUrl);


                repositoryUrl.save(newRecord);
            }
        }
        //return "http://localhost:8080/api/v1/BountyURL/" + createdShortUrl;
        return createdShortUrl;
    }

    // READ
    public String findOriginalUrl(String shortUrl) throws UrlNotFoundException {

        var entity = repositoryUrl.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("No entity with " + shortUrl + " found."));
        return entity.getOriginalUrl();
    }


    public boolean deleteUrl (String short_url){

        var entity = repositoryUrl.findByShortUrl(short_url);
//                .orElseThrow(() -> new EntityNotFoundException("No entity with " + short_url + " found."));

        Long id = entity.get().getId();

        if (id != null) {
            repositoryUrl.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

}
