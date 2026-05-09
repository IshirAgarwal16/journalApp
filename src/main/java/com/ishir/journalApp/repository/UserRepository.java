 package com.ishir.journalApp.repository;

import com.ishir.journalApp.entity.JournalEntry;
import com.ishir.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

 public interface UserRepository extends MongoRepository<User, ObjectId>  {

    User findByUserName(String username);

    void deleteByUserName(String username);

    List<User> getUserForSA();
}
