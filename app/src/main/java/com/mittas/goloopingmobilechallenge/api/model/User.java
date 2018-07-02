package com.mittas.goloopingmobilechallenge.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class used to save responses from the REST API
 */
public class User {
    @SerializedName("user_id")
    private String userId;

    private String email;

    private String token;

    @SerializedName("avatar_url")
    private String avatarUrl;
}
