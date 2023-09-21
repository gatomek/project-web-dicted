package pl.gatomek.webdicted.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.gatomek.webdicted.dto.DictQuery;
import pl.gatomek.webdicted.entity.DictEntry;
import pl.gatomek.webdicted.entity.GermanDictEntry;
import pl.gatomek.webdicted.repository.GermanDictRepository;

import java.util.List;

@Service
public class GermanDictService implements DictService {

    public GermanDictService(GermanDictRepository repository) {
        this.repository = repository;
    }

    private final GermanDictRepository repository;

    @Transactional( propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public DictEntry find(String query) {
        List<GermanDictEntry> list = repository.findByRequestIs( query);
        if( list.isEmpty())
            return null;

        return list.get(0);
    }

    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public DictEntry save(DictQuery query, String translation) {

        List<GermanDictEntry> list = repository.findByRequestIs( query.getQuery().trim());
        if( ! list.isEmpty())
            return list.get(0);

        GermanDictEntry entry = new GermanDictEntry();
        entry.setRequest( query.getQuery().trim());
        entry.setLang(query.getLang());
        entry.setResponse(translation);

        return repository.save( entry);
    }
}
