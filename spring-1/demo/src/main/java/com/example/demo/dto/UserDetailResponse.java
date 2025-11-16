package com.example.demo.dto;

import java.time.LocalDateTime;

public class UserDetailResponse {
    private String id;
    private String name;
    private String email;
    private Integer age;
    private String address;
    private LocalDateTime createdAt;
    private String displayInfo;

    public UserDetailResponse() {
    }

    public UserDetailResponse(String id, String name, String email, Integer age, String address, LocalDateTime createdAt, String displayInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.createdAt = createdAt;
        this.displayInfo = displayInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDisplayInfo() {
        return displayInfo;
    }

    public void setDisplayInfo(String displayInfo) {
        this.displayInfo = displayInfo;
    }
}
