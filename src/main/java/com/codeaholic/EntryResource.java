package com.codeaholic;

import com.codeaholic.Entry;
import com.codeaholic.EntryDTO;

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

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EntryResource {

    private ArrayList<Entry> entries;

    public EntryResource() {
	entries = new ArrayList();
        Entry root = new Entry();
	root.setId(Long.valueOf(4));
	root.setTitle("Root");
	root.setSlug("root");
	root.setContent("");
	entries.add(root);
        Entry js = new Entry();
	js.setId(Long.valueOf(5));
	js.setTitle("JavaScript");
	js.setSlug("js");
	js.setContent("...");
        js.setParent(root);
	entries.add(js);
        Entry euler = new Entry();
	euler.setId(Long.valueOf(6));
	euler.setTitle("Project Euler");
	euler.setSlug("euler");
	euler.setContent("...");
        euler.setParent(root);
	entries.add(euler);
    }

    @GET
    public Multi<EntryDTO> list() {
	return Multi.createFrom().iterable(entries).onItem().transform(EntryDTO::ofEntry);
    }

    @GET
    @Path("/{slug}")
    public Uni<Entry> getById(String slug) {
        return Entry.find("slug", slug).firstResult();
    }

    private Function<Entry, EntryDTO> toEntryDTO = e -> EntryDTO.builder()
        .id(e.getId())
        .title(e.getTitle())
        .slug(e.getSlug())
        .content(e.getContent())
        .parent(e.getParent())
        .children(e.getChildren()).build();

}
