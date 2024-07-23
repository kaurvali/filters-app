package io.kaurvali.filters.filtering.usecase;

import io.kaurvali.filters.application.error.exception.ResourceAlreadyExistsException;
import io.kaurvali.filters.application.error.exception.ResourceNotFoundException;
import io.kaurvali.filters.filtering.function.data.FilterDataResponse;
import io.kaurvali.filters.filtering.function.data.FilterUpdateRequest;
import io.kaurvali.filters.filtering.function.data.FilterUpdateResponse;
import io.kaurvali.filters.filtering.mapper.FilterDataMapper;
import io.kaurvali.filters.filtering.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterUseCase {

    private final FilterService filterService;
    private final FilterDataMapper filterDataMapper;

    public FilterUpdateResponse createFilter(FilterUpdateRequest filterUpdateRequest) {
        var filterName = filterUpdateRequest.getName();
        if (filterService.existsByName(filterName)) {
            throw new ResourceAlreadyExistsException("Filter already exists with name: " + filterName);
        }

        var newFilter = filterDataMapper.map(filterUpdateRequest);

        filterService.createFilter(newFilter);

        return new FilterUpdateResponse()
                .setStatus("ok")
                .setStatusMessage("Filter created");
    }

    public FilterDataResponse getFilter(Long filterId) {
        validateFilterIdExists(filterId);
        var filter = filterService.getFilterById(filterId);
        return filterDataMapper.map(filter);
    }

    public List<FilterDataResponse> getAllFilters() {
        var filters = filterService.getAllFilters();
        return filterDataMapper.map(filters);
    }

    public FilterUpdateResponse updateFilter(Long filterId,
                                             FilterUpdateRequest filterUpdateRequest) {
        validateFilterIdExists(filterId);

        var filter = filterDataMapper.map(filterUpdateRequest);
        filterService.updateFilterCriteriaById(filterId, filter);

        return new FilterUpdateResponse()
                .setStatus("ok")
                .setStatusMessage("Filter updated");
    }

    private void validateFilterIdExists(Long filterId) {
        if (!filterService.existsById(filterId)) {
            throw new ResourceNotFoundException("Filter not found, ID: " + filterId);
        }
    }
}
