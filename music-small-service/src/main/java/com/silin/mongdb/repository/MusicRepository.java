package com.silin.mongdb.repository;

import com.silin.mongdb.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Create by silin
 * Date:  2020/3/7 18:21
 */
public interface MusicRepository extends MongoRepository<Music, Long> {
}
