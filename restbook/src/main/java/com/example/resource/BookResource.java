package com.example.resource;

import com.example.service.BookServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.io.FileNotFoundException;


@Path("/api/books")
@Tag(name = "Book REST Endpoint")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookServiceImpl bookService;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a new book")
    @Retry(maxRetries = 1, delay = 2000)
    @Fallback(fallbackMethod = "fallbackOnCreatingABook")//calling fallback method
    public Response createABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublication, @FormParam("genre") String genre) {
       return bookService.createABook(title,author,yearOfPublication,genre);
    }

    public Response fallbackOnCreatingABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublication, @FormParam("genre") String genre) throws FileNotFoundException {
       return bookService.fallbackOnCreatingABook(title,author,yearOfPublication,genre);
    }

//    What's very important is both methods need to have exactly the same signature, the same number of parameters
//    to pass in, and also the same return value.
//    If we leave the code like that, if the book resource cannot invoke the number microservice, it will
//    automatically fall back on the fallback on creating a book method.
}
