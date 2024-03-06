package com.codeaholic;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.InputStream;
import java.io.IOException;

@Singleton
public class JsonFileReader {
    private final ObjectMapper mapper;

    @Inject
    public JsonFileReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <T> T readFile(InputStream inputStream, Class<T> elementClass) throws IOException {
        return mapper.readValue(inputStream, elementClass);
    }
}
