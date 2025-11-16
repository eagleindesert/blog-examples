package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.document.UserDocument;
import com.example.demo.dto.UserBasicResponse;
import com.example.demo.dto.UserDetailResponse;
import com.example.demo.dto.UserRequest;

@Service
public class UserService {
    
    private final MongoTemplate mongoTemplate;
    
    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    /**
     * JSON 데이터를 받아서 MongoDB demo 컬렉션에 저장
     */
    public UserDocument saveUser(UserRequest request) {
        UserDocument document = new UserDocument();
        document.setName(request.getName());
        document.setEmail(request.getEmail());
        document.setAge(request.getAge());
        document.setAddress(request.getAddress());
        document.setCreatedAt(LocalDateTime.now());
        
        // MongoTemplate을 이용해 demo 컬렉션에 저장
        return mongoTemplate.insert(document, "demo");
    }
    
    /**
     * MongoTemplate의 getConverter()를 이용해 UserBasicResponse로 변환하여 조회
     */
    public List<UserBasicResponse> getAllUsersBasic() {
        List<UserDocument> documents = mongoTemplate.findAll(UserDocument.class, "demo");
        
        return documents.stream()
            .map(doc -> mongoTemplate.getConverter().getConversionService()
                .convert(doc, UserBasicResponse.class))
            .collect(Collectors.toList());
    }
    
    /**
     * MongoTemplate의 getConverter()를 이용해 UserDetailResponse로 변환하여 조회
     */
    public List<UserDetailResponse> getAllUsersDetail() {
        List<UserDocument> documents = mongoTemplate.findAll(UserDocument.class, "demo");
        
        return documents.stream()
            .map(doc -> mongoTemplate.getConverter().getConversionService()
                .convert(doc, UserDetailResponse.class))
            .collect(Collectors.toList());
    }
}
