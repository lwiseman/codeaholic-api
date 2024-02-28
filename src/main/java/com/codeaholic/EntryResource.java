package com.codeaholic;

import com.codeaholic.Entry;
import com.codeaholic.EntryDTO;

import java.util.List;
import java.util.Optional;

import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EntryResource {

    @GET
    public Uni<List<Entry>> list() {
        return Entry.listAll();
    }

/*
    @GET
    @Path("/{slug}")
    public Uni<List<Entry>> get(String slug) {
        return Entry.list("slug", slug);
    }
*/
    @GET
    @Path("/{id}")
    public Uni<EntryDTO> getById(Long id) {
        Uni<Entry> entryUni = Entry.findById(id);
        Uni<EntryDTO> entryDTOUni = entryUni.map(EntryDTO::ofEntry);
        return entryDTOUni;
    }

}
