package com.example.proxy;


import com.example.entity.IsbnThirteen;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "number.proxy")//to use as url in application.properties
@Path("/api/numbers")//represent number microservice so need to specify root path
public interface NumberProxy {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    IsbnThirteen generateIsbnNumbers();
}