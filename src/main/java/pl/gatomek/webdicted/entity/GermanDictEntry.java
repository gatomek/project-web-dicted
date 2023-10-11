package pl.gatomek.webdicted.entity;

import jakarta.persistence.*;

@Table( schema = "web_dicted_schema", name="GERMAN", indexes = {
        @Index( name = "german_query_index", columnList = "REQUEST", unique = false)
})
@Entity
public class GermanDictEntry extends DictEntry{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "german_dict_sequence")
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
