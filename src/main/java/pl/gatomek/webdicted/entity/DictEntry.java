package pl.gatomek.webdicted.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@MappedSuperclass
public class DictEntry {

    @Column( name="REQUEST", nullable = false)
    private String request;

    @Column( name="LANG", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language lang;

    @Column( name="RESPONSE")
    private String response;

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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
}
