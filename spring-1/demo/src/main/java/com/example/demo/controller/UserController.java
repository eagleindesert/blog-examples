package com.example.demo.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserBasicResponse;
import com.example.demo.dto.UserDetailResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * 외부 JSON을 받아서 MongoDB에 그대로 저장
     * 예제 JSON:
     * {
     *   "name": "홍길동",
     *   "email": "hong@example.com",
     *   "age": 30,
     *   "address": "서울시 강남구"
     * }
     */
    @PostMapping
    public ResponseEntity<Document> createUser(@RequestBody Document document) {
        Document savedUser = userService.saveUser(document);
        return ResponseEntity.ok(savedUser);
    }
    
    /**
     * Converter를 이용해 기본 정보만 포함한 응답 (id, name, email)
     */
    @GetMapping("/basic")
    public ResponseEntity<List<UserBasicResponse>> getUsersBasic() {
        List<UserBasicResponse> users = userService.getAllUsersBasic();
        return ResponseEntity.ok(users);
    }
    
    /**
     * Converter를 이용해 상세 정보를 포함한 응답 (모든 필드 + displayInfo)
     */
    @GetMapping("/detail")
    public ResponseEntity<List<UserDetailResponse>> getUsersDetail() {
        List<UserDetailResponse> users = userService.getAllUsersDetail();
        return ResponseEntity.ok(users);
    }
    
    /**
     * ObjectId로 단일 Document 조회 후 기본 정보 응답
     * GET /api/users/{id}/basic
     */
    @GetMapping("/{id}/basic")
    public ResponseEntity<UserBasicResponse> getUserBasicById(@PathVariable("id") String id) {
        UserBasicResponse user = userService.getUserBasicById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    /**
     * ObjectId로 단일 Document 조회 후 상세 정보 응답
     * GET /api/users/{id}/detail
     */
    @GetMapping("/{id}/detail")
    public ResponseEntity<UserDetailResponse> getUserDetailById(@PathVariable("id") String id) {
        UserDetailResponse user = userService.getUserDetailById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
