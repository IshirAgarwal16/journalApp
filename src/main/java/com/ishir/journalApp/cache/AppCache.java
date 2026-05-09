package com.ishir.journalApp.cache;

import com.ishir.journalApp.entity.ConfigJournalAppEntity;
import com.ishir.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
/*
spring:
  application:
    name: Journal App
  data:
    mongodb:
      uri: mongodb+srv://ishiragarwal16_db_user:Ishir12345@cluster0.yupdh7a.mongodb.net/journaldb?retryWrites=true&w=majority
      database: journaldb
      auto-index-creation: true

 */