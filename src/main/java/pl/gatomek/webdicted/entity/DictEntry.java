package pl.gatomek.webdicted.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@MappedSuperclass
public class DictEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( name="REQUEST", nullable = false)
    private String request;

    @Column( name="LANG", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language lang;

    @Column( name="RESPONSE")
    @Lob
    private String response;

    @Column(name="VALID")
    private boolean valid;

    @CreationTimestamp
    @Column(name="DATE_TIME", nullable = false, updatable = false)
    private Instant dateTime;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
