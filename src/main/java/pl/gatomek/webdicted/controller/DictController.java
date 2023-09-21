package pl.gatomek.webdicted.controller;

import jakarta.transaction.NotSupportedException;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import pl.gatomek.webdicted.dto.DictQuery;
import pl.gatomek.webdicted.dto.Translation;
import pl.gatomek.webdicted.entity.DictEntry;
import pl.gatomek.webdicted.entity.Language;
import pl.gatomek.webdicted.service.GermanDictService;
import pl.gatomek.webdicted.service.DictService;
import pl.gatomek.webdicted.service.EnglishDictService;
import pl.gatomek.webdicted.service.ExternalDictionaryService;

import java.util.List;
import java.util.Objects;

@Component
public class DictController {

    private final EnglishDictService englishDictService;
    private final GermanDictService germanDictService;

    private final ExternalDictionaryService externalDictService;
    public DictController(EnglishDictService englishDictService, GermanDictService germanDictService, ExternalDictionaryService externalDictService) {
        this.englishDictService = englishDictService;
        this.germanDictService = germanDictService;
        this.externalDictService = externalDictService;
    }

    private DictService selectServiceByLang(Language lang) throws NotSupportedException {
        if( Language.DE.equals( lang))
            return germanDictService;

        if( Language.EN.equals( lang))
            return englishDictService;

        throw new NotSupportedException();
    }

    private synchronized DictEntry saveEntry( DictService dictService, DictQuery query, String translation) {
        return dictService.save( query, translation);
    }

    public Translation lookup(DictQuery query) throws NotSupportedException {

        DictService dictService = selectServiceByLang( query.getLang());
        DictEntry dictEntry = dictService.find( query.getQuery());

        if( Objects.isNull(dictEntry)) {
            String translation = externalDictService.getTranslation(query.getLang(), query.getQuery());
            dictEntry = saveEntry( dictService, query, translation);
        }

        JsonParser parser = JsonParserFactory.getJsonParser();
        List<Object> list = parser.parseList( dictEntry.getResponse());

        return new Translation( list);
    }
}
