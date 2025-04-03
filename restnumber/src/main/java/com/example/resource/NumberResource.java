package com.example.resource;

import com.example.entity.IsbnNumbers;
import com.example.service.NumberServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/numbers")
@Tag(name = "Number REST Endpoint")//changes how swagger see resource
@Produces(MediaType.APPLICATION_JSON)
public class NumberResource {
    @Inject
    NumberServiceImpl numberService;

    @GET
    @Path("/generate")
    @Operation(summary = "Generates book numbers")//summary of method in swagger
    public IsbnNumbers generateIsbnNumbers() {
        return numberService.generateIsbnNumbers();
    }
}
