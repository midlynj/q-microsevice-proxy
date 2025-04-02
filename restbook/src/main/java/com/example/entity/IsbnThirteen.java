package com.example.entity;

import jakarta.json.bind.annotation.JsonbProperty;

public class IsbnThirteen {
    @JsonbProperty("isbn_13")//specify property so the incoming json gets mapped to it from the number microservice
    public String isbn13;
}
