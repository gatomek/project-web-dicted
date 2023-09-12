package pl.gatomek.webdicted.dto;

import java.util.List;

public class Translation {

    public Translation( List<Object> list) {
        this.response = list;
    }
    private List<Object> response;

    public List<Object> getDictEntries() {
        return response;
    }
}
