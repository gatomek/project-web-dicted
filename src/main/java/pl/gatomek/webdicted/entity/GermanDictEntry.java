package pl.gatomek.webdicted.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table( name="GERMAN", indexes = {
        @Index( name = "german_query_index", columnList = "REQUEST", unique = true)
})
@Entity
public class GermanDictEntry extends DictEntry{
}
