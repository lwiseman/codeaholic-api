package com.codeaholic;

import io.vertx.core.Vertx;
import io.quarkus.vertx.VertxContextSupport;

import java.io.IOException;
import java.util.List;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.common.annotation.Blocking;
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

    @RunOnVirtualThread
    void onStart(@Observes StartupEvent ev, JsonFileReader jsonFileReader, Mutiny.SessionFactory sf) throws Throwable {
        root = jsonFileReader.readFile(this.getClass().getClassLoader().getResourceAsStream("import.json"), Entry.class);
        System.out.println(Thread.currentThread());
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
