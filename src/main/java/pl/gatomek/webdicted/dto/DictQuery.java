package pl.gatomek.webdicted.dto;

import pl.gatomek.webdicted.entity.Language;

public class DictQuery {
    private Language lang;
    private String query;

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void trim() {
        query = query.trim();
    }
}
