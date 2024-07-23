package io.kaurvali.filters.filtering.mapper;

import io.kaurvali.filters.filtering.domain.Filter;
import io.kaurvali.filters.filtering.function.data.FilterDataResponse;
import io.kaurvali.filters.filtering.function.data.FilterUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterDataMapper {

    public Filter map(FilterUpdateRequest filterUpdateRequest) {
        return new Filter()
                .setName(filterUpdateRequest.getName())
                .setCriteriaList(filterUpdateRequest.getCriteriaList());
    }

    public FilterDataResponse map(Filter filter) {
        return new FilterDataResponse()
                .setId(filter.getId())
                .setName(filter.getName())
                .setCriteriaList(filter.getCriteriaList());
    }

    public List<FilterDataResponse> map(List<Filter> filters) {
        return filters.stream().map(this::map).toList();
    }
}
