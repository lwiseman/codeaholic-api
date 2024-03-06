package com.codeaholic;

import io.vertx.core.Vertx;
import io.quarkus.vertx.VertxContextSupport;

import java.io.IOException;
import java.util.List;

import io.quarkus.runtime.StartupEvent;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.EntityManagerFactory;
//import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.enterprise.event.Observes;

@Path("/entries")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class EntryResource {
    private Entry root;

    @Inject
    EntryResource(JsonFileReader jsonFileReader, Vertx vertx, EntityManagerFactory entityManagerFactory) {
        Mutiny.SessionFactory sessionFactory = entityManagerFactory.unwrap(Mutiny.SessionFactory.class);
        try {
            root = jsonFileReader.readFile(this.getClass().getClassLoader().getResourceAsStream("/META-INF/resources/CodeaholicRedesign.json"), Entry.class);
            System.out.println(root.children);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void onStart(@Observes StartupEvent ev, Mutiny.SessionFactory sf) throws Exception, Throwable {
        VertxContextSupport.subscribeAndAwait(() -> {
            return sf.withTransaction(session -> session.persist(root));
        });
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

}
