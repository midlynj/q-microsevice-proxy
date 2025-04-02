package com.example.entity;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


import java.time.Instant;

@Schema(description = "A book")
public class Book {

    @JsonbProperty("isbn_13")
    @Schema(required = true)
    public String isbn13;
    @Schema(required = true)
    public String title;
    public String author;
    @JsonbProperty("year_of_publication")
    public int yearOfPublication;
    public String genre;
    @JsonbDateFormat("yyyy/MM/dd")
    @JsonbProperty("creation_date")
    @Schema(implementation = String.class, format = "date")
    public Instant creationDate;

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}