package pl.gatomek.webdicted.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gatomek.webdicted.entity.EnglishDictEntry;

import java.util.List;

@Repository
public interface EnglishDictRepository extends CrudRepository<EnglishDictEntry, Long> {

    @Query("SELECT e FROM EnglishDictEntry e WHERE e.request=:req")
    public List<EnglishDictEntry> findByRequestIs(@Param("req") String req);
}
