package com.zinkworks.bountyhuntersurlshortener.repository;

import com.zinkworks.bountyhuntersurlshortener.model.BountyUrlTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface RepositoryUrl extends JpaRepository<BountyUrlTable, Long> {
    @Query(value = "SELECT * FROM bounty_url_table WHERE short_url = :shortUrl", nativeQuery = true)
    Optional<BountyUrlTable> findByShortUrl(String shortUrl);



}
