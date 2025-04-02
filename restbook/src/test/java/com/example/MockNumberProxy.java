package com.example;

import com.example.entity.IsbnThirteen;
import com.example.proxy.NumberProxy;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {

    @Override
    public IsbnThirteen generateIsbnNumbers() {
        IsbnThirteen isbnThirteen = new IsbnThirteen();
        isbnThirteen.isbn13 = "13-mock";
        return isbnThirteen;
    }
}