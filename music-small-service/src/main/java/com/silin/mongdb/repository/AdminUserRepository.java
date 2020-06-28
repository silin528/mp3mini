package com.silin.mongdb.repository;

import com.silin.mongdb.entity.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Create by silin
 * Date:  2020/3/28 16:42
 */
public interface AdminUserRepository extends MongoRepository<AdminUser, Long> {


}
