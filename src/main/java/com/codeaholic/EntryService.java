package com.codeaholic;

import com.codeaholic.Entry;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

import io.vertx.mutiny.pgclient.PgPool;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@ApplicationScoped
public class EntryService {
    @Inject
    EntryService(io.vertx.mutiny.pgclient.PgPool client) {
    }
}

