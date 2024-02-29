package com.codeaholic;

import com.codeaholic.Entry;
import com.codeaholic.EntryDTO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand;

import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.reactive.RestResponse; 
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EntryResource {

	/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
//        return Entry.listAll();
        return Response.status(200).entity("{\"test\": 42 }").build();
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
    @WithSessionOnDemand
    public Uni<EntryDTO> getById(Long id) {
        Uni<Entry> entry = Entry.findById(id);
	return entry.map(toEntryDTO);
	//return entry.map(EntryDTO::ofEntry);
        //return ResponseBuilder.ok(entryDTO).build();
    }

    private Function<Entry, EntryDTO> toEntryDTO = e -> EntryDTO.builder()
        .id(e.getId())
        .title(e.getTitle())
        .slug(e.getSlug())
        .content(e.getContent())
        .parent(e.getParent())
        .children(e.getChildren()).build();

}
