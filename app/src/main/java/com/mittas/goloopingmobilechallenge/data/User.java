package com.mittas.goloopingmobilechallenge.data;

import com.google.gson.annotations.SerializedName;

/**
 * Class used to send and receive responses from the REST API
 */
public class User {
    @SerializedName("user_id")
    private String userId;

    private String email;

    private String password;

    private String token;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("avatar")
    private String base64EncodedAvatar;

    public User() {
    }

    public String getBase64EncodedAvatar() {
        return base64EncodedAvatar;
    }

    public void setBase64EncodedAvatar(String base64EncodedAvatar) {
        this.base64EncodedAvatar = base64EncodedAvatar;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
