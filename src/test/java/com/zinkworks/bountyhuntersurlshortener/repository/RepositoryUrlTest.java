package com.zinkworks.bountyhuntersurlshortener.repository;

import com.zinkworks.bountyhuntersurlshortener.model.BountyUrlTable;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
class RepositoryUrlTest {

    @Autowired
    private RepositoryUrl repositoryUrl;

    @Test
    @Transactional
    public void SaveBountyTableURL_Test(){
        //Arrange
        BountyUrlTable newRecord =new BountyUrlTable();

        newRecord.setShortUrl("MD5HasShortURL");
        newRecord.setOriginalUrl("https://www.Test_Original_URL.com");
//        newRecord.setCreatedDate(LocalDateTime.now());

        //Act
        BountyUrlTable savedRecord = repositoryUrl.save(newRecord);

        //Assert
        assertNotNull(savedRecord.getId());
        assertEquals("MD5HasShortURL", savedRecord.getShortUrl());
        assertEquals("https://www.Test_Original_URL.com", savedRecord.getOriginalUrl());
//        assertNotNull(LocalDateTime.now(), savedRecord.getCreatedDate());
    }


    @Test
    public void findByOShortenURL_Test(){


        //Arrange
        BountyUrlTable newRecord =new BountyUrlTable();

//        newRecord.setShortUrl("MD5HShortURL");
//        newRecord.setOriginalUrl("https://www.Test_Original_URL.com");
//        newRecord.setCreatedDate(LocalDateTime.now());

        Optional<BountyUrlTable> foundEntity = repositoryUrl.findByShortUrl("HuuMy3K");


        assertTrue(foundEntity.isPresent());

        BountyUrlTable foundShortenUrl = foundEntity.get();
        assertEquals("HuuMy3K", foundShortenUrl.getShortUrl());
        assertEquals("https://www.youtube.com/watch?v=SDwqcFwvwY0", foundShortenUrl.getOriginalUrl());


    }



}