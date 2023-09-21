package pl.gatomek.webdicted.service;

import pl.gatomek.webdicted.dto.DictQuery;
import pl.gatomek.webdicted.entity.DictEntry;

public interface DictService {

    DictEntry find(String query);

    DictEntry save(DictQuery dictQuery, String response);
}
