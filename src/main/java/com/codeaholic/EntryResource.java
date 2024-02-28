package com.codeaholic;

import io.quarkus.hibernate.reactive.panache.Panache;

import com.codeaholic.Entry;
import com.codeaholic.EntryDTO;

import java.util.function.Function;
import java.util.List;
import java.util.Optional;
import java.lang.RuntimeException;

import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import java.util.Set;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

@Path("/entries")
@ApplicationScoped
public class EntryResource {

    private Set<Entry> entries = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private Entry root;

    public EntryResource() {
        root = new Entry(Long.valueOf(1), "Root", "root", "...", null);
        Entry js = new Entry(Long.valueOf(2), "JavaScript utilities", "js-utilities", "...", root);
	Entry euler = new Entry(Long.valueOf(3), "Project Euler", "project-euler", "...", root);
        entries.add(root);
        entries.add(js);
	entries.add(euler);
    }

    /*
    @GET
    public Uni<List<Entry>> list() {
        return listAll();
    }
    */

/*
    @GET
    @Path("/{slug}")
    public Uni<List<Entry>> get(String slug) {
        return Entry.list("slug", slug);
    }
*/
    @GET
    @Path("/{id}")
    public RestResponse getById(Long id) {
//return Panache.withTransaction(() -> {
        return ResponseBuilder.ok(Stream.of(root).map(mapToEntryDTO).findAny().orElse(null)).build();
//	return ResponseBuilder.ok(.map(mapToEntryDTO);//.orElseThrow(EntityNotFoundException::new);
//        Uni<EntryDTO> entryDTOUni = entryUni.map(mapToEntryDTO);
//	return entryDTOUni;
//	return ResponseBuilder.ok(entryDTOUni, MediaType.APPLICATION_JSON).build();
//});
//	return entryDTOUni.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    private Function<Entry, EntryDTO> mapToEntryDTO = e -> EntryDTO.builder().id(e.getId()).title(e.getTitle()).slug(e.getSlug()).content(e.getContent()).parent(e.getParent()).children(e.getChildren()).build();

}
