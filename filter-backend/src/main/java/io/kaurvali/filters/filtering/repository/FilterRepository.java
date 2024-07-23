package io.kaurvali.filters.filtering.repository;

import io.kaurvali.filters.filtering.domain.Filter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends CrudRepository<Filter, Long> {

    @Query(value = "SELECT f FROM Filter f WHERE f.name = :filterName")
    List<Filter> findAllByName(String filterName);

    @Query(value = "SELECT f FROM Filter f WHERE f.id = :id")
    Filter findFilterById(long id);

    @Modifying
    @Transactional
    @Query("UPDATE Filter f SET f.name = :newName WHERE f.id = :id")
    int updateFilterNameById(Long id, String newName);

    boolean existsByName(String name);
}
