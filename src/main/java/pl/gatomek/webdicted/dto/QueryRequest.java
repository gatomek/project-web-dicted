package pl.gatomek.webdicted.dto;

public class QueryRequest {
    String query;
    String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "QueryRequest{" +
                "query='" + query + '\'' +
                ", language='" + lang + '\'' +
                '}';
    }
}
