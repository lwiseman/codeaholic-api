package com.codeaholic;

import com.codeaholic.Entry;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.stream.Collectors;

import io.quarkus.hibernate.reactive.panache.common.WithSession;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.reactive.RestResponse; 
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

@Path("entries")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class EntryResource {

    @GET
    public Uni<List<Entry>> get() {
	return Entry.listAll();
    }

    @GET
    @Path("{id}")
    public Uni<Entry> getSingle(Long id) {
        return Entry.findById(id);
    }

}
