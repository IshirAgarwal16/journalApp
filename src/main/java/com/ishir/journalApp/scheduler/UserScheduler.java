package com.ishir.journalApp.scheduler;

import com.ishir.journalApp.cache.AppCache;
import com.ishir.journalApp.entity.JournalEntry;
import com.ishir.journalApp.entity.User;
import com.ishir.journalApp.enums.Sentiment;
import com.ishir.journalApp.repository.UserRepository;
import com.ishir.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.Arrays.stream;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppCache appCache;

    //@Scheduled(cron = "0 0 9 * * SUN")
    //@Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user: users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments){
                if(sentiment != null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if(entry.getValue() > maxCount) {
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for 7 days", mostFrequentSentiment.toString());
            }
        }
    }
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
