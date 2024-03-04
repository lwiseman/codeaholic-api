package com.codeaholic;

import com.codeaholic.Entry;

import io.quarkus.hibernate.reactive.panache.Panache;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.core.Vertx;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.stream.Collectors;

import io.quarkus.hibernate.reactive.panache.common.WithSession;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;
import org.hibernate.reactive.mutiny.Mutiny;


import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.TransactionManager;
//import jakarta.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.reactive.RestResponse; 
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/entries")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class EntryResource {
    Entry root;
    private final Mutiny.SessionFactory sessionFactory;

    @Inject
    EntryResource(JsonFileReader jsonFileReader, Vertx vertx, EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(Mutiny.SessionFactory.class);
        try {
            root = jsonFileReader.readFile(this.getClass().getClassLoader().getResourceAsStream("/META-INF/resources/CodeaholicRedesign.json"), Entry.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
System.out.println(root.children);
System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        sessionFactory.withTransaction((session, tx) -> session.persist(root));
//        root.persist().subscribe().with(System.out::println);
//        Panache.withTransaction(root::persist).await();//.subscribe().with(v -> {});
    }

    @GET
    public Uni<List<Entry>> get() {
	return Entry.listAll();
    }

    @GET
    @Path("/{slug}")
    public Uni<Entry> getBySlug(String slug) {
        return Entry.findBySlug(slug);
    }

    /*
    @GET
    @Path("/add")
    public Uni<Void> addSlug() {
        return Panache.withTransaction(entry::persist).
    }
    */
}
