package io.kaurvali.filters.filtering.service;

import io.kaurvali.filters.filtering.domain.Filter;
import io.kaurvali.filters.filtering.repository.FilterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;

    public boolean existsById(Long filterId) {
        return filterRepository.existsById(filterId);
    }

    public boolean existsByName(String name) {
        return filterRepository.existsByName(name);
    }

    public List<Filter> getAllFilters() {
        return (List<Filter>) filterRepository.findAll();
    }

    public List<Filter> getFiltersByName(String name) {
        return filterRepository.findAllByName(name);
    }

    public Filter getFilterById(Long id) {
        return filterRepository.findFilterById(id);
    }

    @Transactional
    public void createFilter(Filter filter) {
        filterRepository.save(filter);
    }

    @Transactional
    public void updateFilterCriteriaById(Long id, Filter newFilter) {
        var filter = filterRepository.findFilterById(id);
        filter.setName(newFilter.getName());
        filter.setCriteriaList(newFilter.getCriteriaList());

        filterRepository.save(filter);
    }

}
