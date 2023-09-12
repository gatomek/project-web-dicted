package pl.gatomek.webdicted.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.gatomek.webdicted.dto.DictQuery;
import pl.gatomek.webdicted.entity.DictEntry;
import pl.gatomek.webdicted.entity.EnglishDictEntry;
import pl.gatomek.webdicted.repository.EnglishDictRepository;

import java.util.List;

@Service
public class EnglishDictService implements DictService {
    public EnglishDictService(EnglishDictRepository repository) {
        this.repository = repository;
    }

    private final EnglishDictRepository repository;

    @Transactional( propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public DictEntry find(String query) {
        List<EnglishDictEntry> list = repository.findByRequestIs( query);
        if( list.isEmpty())
            return null;

        return list.get(0);
    }

    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public DictEntry save(DictQuery query, String translation, boolean vld) {

        List<EnglishDictEntry> list = repository.findByRequestIs( query.getQuery().trim());
        if( ! list.isEmpty())
            return list.get(0);

        EnglishDictEntry entry = new EnglishDictEntry();
        entry.setRequest( query.getQuery().trim());
        entry.setLang(query.getLang());
        entry.setResponse(translation);
        entry.setValid( true);

        return repository.save( entry);
    }
}
