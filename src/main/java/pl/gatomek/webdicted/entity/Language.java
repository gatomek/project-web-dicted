package pl.gatomek.webdicted.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Language {
    EN("en"),
    DE("de");

    private final String label;

    Language(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Language getLanguageFromString(String lang) {

        for (Language l : Language.values()) {
            if (l.getLabel().equals(lang)) {
                return l;
            }
        }

        return null;
    }
}
