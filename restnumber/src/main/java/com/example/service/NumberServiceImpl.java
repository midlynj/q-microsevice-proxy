package com.example.service;

import com.example.entity.IsbnNumbers;
import com.example.interfaces.NumberInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.Random;

@ApplicationScoped
public class NumberServiceImpl implements NumberInterface {

    @Inject
    Logger logger;
    @Override
    public IsbnNumbers generateIsbnNumbers() {
        IsbnNumbers isbnNumbers = new IsbnNumbers();
        isbnNumbers.isbn13 = "13-" + new Random().nextInt(100_000_000);
        isbnNumbers.isbn10 = "10-" + new Random().nextInt(100_000);
        isbnNumbers.generationDate = Instant.now();
        logger.info("Numbers generated " + isbnNumbers);
        return isbnNumbers;
    }
}
