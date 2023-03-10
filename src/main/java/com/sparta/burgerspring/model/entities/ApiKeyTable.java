package com.sparta.burgerspring.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "api_key_table")
public class ApiKeyTable {
    @Id
    @Size(max = 255)
    @Column(name = "user_name", nullable = false)
    private String id;

    @Size(max = 6)
    @NotNull
    @Column(name = "user_level", nullable = false, length = 6)
    private String userLevel;

    @Size(max = 255)
    @NotNull
    @Column(name = "api_key", nullable = false)
    private String apiKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}