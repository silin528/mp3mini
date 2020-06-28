package com.silin.mongdb.repository;

import com.silin.mongdb.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Create by silin
 * Date:  2020/3/20 22:56
 */
public interface CategoryRepository extends MongoRepository<Category, Long> {
}
