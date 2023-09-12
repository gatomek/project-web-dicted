package pl.gatomek.webdicted.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gatomek.webdicted.entity.GermanDictEntry;

import java.util.List;

@Repository
public interface GermanDictRepository extends CrudRepository<GermanDictEntry, Long> {
    @Query("SELECT e FROM GermanDictEntry e WHERE e.request=:req")
    public List<GermanDictEntry> findByRequestIs(@Param("req") String req);
}
