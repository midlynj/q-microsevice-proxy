package com.example.interfaces;

import com.example.entity.Book;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.Response;

import java.io.FileNotFoundException;

public interface BookImpl {
    Response createABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublication, @FormParam("genre") String genre);
    Response fallbackOnCreatingABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublication, @FormParam("genre") String genre) throws FileNotFoundException;
    void saveBookOnDisk(Book book) throws FileNotFoundException;
}
