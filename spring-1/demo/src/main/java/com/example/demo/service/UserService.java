package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserBasicResponse;
import com.example.demo.dto.UserDetailResponse;

@Service
public class UserService {
    
    private final MongoTemplate mongoTemplate;
    
    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    /**
     * 원본 JSON 데이터를 받아서 MongoDB demo 컬렉션에 그대로 저장
     */
    public Document saveUser(Document document) {
        document.put("createdAt", LocalDateTime.now());
        
        // MongoTemplate을 이용해 demo 컬렉션에 저장
        return mongoTemplate.save(document, "demo");
    }
    
    /**
     * MongoTemplate의 getConverter()를 이용해 UserBasicResponse로 변환하여 조회
     */
    public List<UserBasicResponse> getAllUsersBasic() {
        List<Document> documents = mongoTemplate.findAll(Document.class, "demo");
        
        // Stream 사용
        return documents.stream()
            .map(doc -> mongoTemplate.getConverter()
                .read(UserBasicResponse.class, doc))
            .collect(java.util.stream.Collectors.toList());
        
        // Stream 사용하지 않은 버전 (for문)
        /*
        List<UserBasicResponse> responses = new java.util.ArrayList<>();
        for (Document doc : documents) {
            UserBasicResponse response = mongoTemplate.getConverter()
                .read(UserBasicResponse.class, doc);
            responses.add(response);
        }
        return responses;
        */
    }
    
    /**
     * MongoTemplate의 getConverter()를 이용해 UserDetailResponse로 변환하여 조회
     */
    public List<UserDetailResponse> getAllUsersDetail() {
        List<Document> documents = mongoTemplate.findAll(Document.class, "demo");
        
        // Stream 사용
        return documents.stream()
            .map(doc -> mongoTemplate.getConverter()
                .read(UserDetailResponse.class, doc))
            .collect(java.util.stream.Collectors.toList());
        
        // Stream 사용하지 않은 버전 (for문)
        /*
        List<UserDetailResponse> responses = new java.util.ArrayList<>();
        for (Document doc : documents) {
            UserDetailResponse response = mongoTemplate.getConverter()
                .read(UserDetailResponse.class, doc);
            responses.add(response);
        }
        return responses;
        */
    }
}
