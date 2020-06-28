package com.silin.mongdb.repository;

import com.silin.mongdb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Create by silin
 * Date:  2019/6/27 23:57
 */
public interface UserRepository extends MongoRepository<User, Long> {


}
