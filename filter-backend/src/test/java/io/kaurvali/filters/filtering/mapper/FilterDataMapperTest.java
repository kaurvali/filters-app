package io.kaurvali.filters.filtering.mapper;

import io.kaurvali.filters.filtering.domain.ComparisonType;
import io.kaurvali.filters.filtering.domain.Criteria;
import io.kaurvali.filters.filtering.domain.FieldType;
import io.kaurvali.filters.filtering.domain.Filter;
import io.kaurvali.filters.filtering.function.data.FilterUpdateRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class FilterDataMapperTest {

    private final FilterDataMapper target = new FilterDataMapper();
    private static final Criteria criteria = new Criteria()
            .setValue(1.0)
            .setComparisonType(ComparisonType.EQUAL_TO)
            .setField(FieldType.AMOUNT);

    @Test
    void mapFilterUpdateRequest_toFilter_mappedCorrectly() {
        var updateRequest = new FilterUpdateRequest()
                .setName("new filter name")
                        .setCriteriaList(List.of(criteria));
        var res = target.map(updateRequest);

        assertThat(res.getName(), is(updateRequest.getName()));
        assertThat(res.getCriteriaList(), is(updateRequest.getCriteriaList()));
    }

    @Test
    void mapFilter_toFilterDataResponse_mappedCorrectly() {
        var filter = new Filter()
                .setId(1L)
                .setName("filter name")
                .setCriteriaList(List.of(criteria));
        var res = target.map(filter);

        assertThat(res.getId(), is(filter.getId()));
        assertThat(res.getName(), is(filter.getName()));
        assertThat(res.getCriteriaList(), is(filter.getCriteriaList()));
    }

    @Test
    void mapFilter_toFilterDataResponseList_mappedCorrectly() {
        var filter = new Filter()
                .setId(1L)
                .setName("filter name")
                .setCriteriaList(List.of(criteria, criteria));
        var filterList = List.of(filter);
        var res = target.map(filterList);

        assertThat(res.size(), is(filterList.size()));
        assertThat(res.getFirst().getId(), is(filter.getId()));
    }
}
