package com.codeaholic;

import com.codeaholic.Entry;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class EntryDTO {
    private Long id;
    private String title;
    private String slug;
    private String content;
    private Entry parent;
    private Set<Entry> children;

    public static EntryDTO ofEntry(Entry entry) {
        return EntryDTO.builder()
            .id(entry.getId())
            .title(entry.getTitle())
            .slug(entry.getSlug())
            .content(entry.getContent())
            .parent(entry.getParent())
            .children(entry.getChildren()).build();
    }
}

