package com.codeaholic;

import com.codeaholic.Entry;

import java.util.List;

import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/entries")
@ApplicationScoped
public class EntryResource {

    @GET
    public Uni<List<Entry>> get() {
        return Entry.listAll();
    }

}
