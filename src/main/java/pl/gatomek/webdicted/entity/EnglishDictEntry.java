package pl.gatomek.webdicted.entity;

import jakarta.persistence.*;

@Table( schema = "web_dicted_schema", name="ENGLISH", indexes = {
        @Index( name = "english_query_index", columnList = "REQUEST", unique = false)
})
@Entity
public class EnglishDictEntry extends DictEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "english_dict_sequence")
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
