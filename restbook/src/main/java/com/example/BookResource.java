package com.example;

import com.example.entity.Book;
import com.example.proxy.NumberProxy;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
public class BookResource {

    @Inject
    @RestClient
    NumberProxy proxy;

    @Inject
    Logger logger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a new book")
    @Retry(maxRetries = 1, delay = 2000)
    @Fallback(fallbackMethod = "fallbackOnCreatingABook")//calling fallback method
    public Response createABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumbers().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        logger.info("Book created: " + book);
        return Response.status(201).entity(book).build();
    }

    public Response fallbackOnCreatingABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublication, @FormParam("genre") String genre) throws FileNotFoundException {
        Book book = new Book();
        book.isbn13 = "Will be set later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        saveBookOnDisk(book);
        logger.warn("Book saved on disk: " + book);
        return Response.status(206).entity(book).build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);//json representation of book object
        try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        }
    }

//    What's very important is both methods need to have exactly the same signature, the same number of parameters
//    to pass in, and also the same return value.
//    If we leave the code like that, if the book resource cannot invoke the number microservice, it will
//    automatically fall back on the fallback on creating a book method.
}
