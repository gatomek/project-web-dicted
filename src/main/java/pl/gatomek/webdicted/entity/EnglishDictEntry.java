package pl.gatomek.webdicted.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table( name="ENGLISH", indexes = {
        @Index( name = "english_query_index", columnList = "REQUEST", unique = true)
})
@Entity
public class EnglishDictEntry extends DictEntry {
}
