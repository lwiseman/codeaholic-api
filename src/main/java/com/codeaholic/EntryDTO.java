package com.codeaholic;

import com.codeaholic.Entry;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class EntryDTO {
    public Long id;
    public String title;
    public String slug;
    public String content;
    public Entry parent;
    public Set<Entry> children;

    public static EntryDTO ofEntry(Entry entry) {
        return EntryDTO.builder().id(entry.getId()).title(entry.getTitle()).slug(entry.getSlug()).content(entry.getContent()).build();
    }
}

