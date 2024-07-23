package io.kaurvali.filters.filtering.service;

import io.kaurvali.filters.filtering.domain.ComparisonType;
import io.kaurvali.filters.filtering.domain.Criteria;
import io.kaurvali.filters.filtering.domain.FieldType;
import io.kaurvali.filters.filtering.domain.Filter;
import io.kaurvali.filters.filtering.repository.FilterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilterServiceTest {

    @Mock
    private FilterRepository filterRepository;
    @InjectMocks
    private FilterService target;

    @Test
    void existsById_shouldReturnTrue_whenFilterExists() {
        when(filterRepository.existsById(1L)).thenReturn(true);

        boolean exists = target.existsById(1L);

        assertThat(exists, is(true));
        verify(filterRepository, times(1)).existsById(1L);
    }

    @Test
    void existsById_shouldReturnFalse_whenFilterDoesNotExist() {
        when(filterRepository.existsById(1L)).thenReturn(false);

        boolean exists = target.existsById(1L);

        assertThat(exists, is(false));
        verify(filterRepository, times(1)).existsById(1L);
    }

    @Test
    void existsByName_shouldReturnTrue_whenFilterExists() {
        when(filterRepository.existsByName("Test Filter")).thenReturn(true);

        boolean exists = target.existsByName("Test Filter");

        assertThat(exists, is(true));
        verify(filterRepository, times(1)).existsByName("Test Filter");
    }

    @Test
    void existsByName_shouldReturnFalse_whenFilterDoesNotExist() {
        when(filterRepository.existsByName("Test Filter")).thenReturn(false);

        boolean exists = target.existsByName("Test Filter");

        assertThat(exists, is(false));
        verify(filterRepository, times(1)).existsByName("Test Filter");
    }

    @Test
    void getAllFilters_shouldReturnListOfFilters() {
        var filters = List.of(new Filter());
        when(filterRepository.findAll()).thenReturn(filters);

        List<Filter> result = target.getAllFilters();

        assertThat(result, is(filters));
        verify(filterRepository, times(1)).findAll();
    }

    @Test
    void getFiltersByName_shouldReturnListOfFilters() {
        var filters = List.of(new Filter());
        when(filterRepository.findAllByName("Test Filter")).thenReturn(filters);

        List<Filter> result = target.getFiltersByName("Test Filter");

        assertThat(result, is(filters));
        verify(filterRepository, times(1)).findAllByName("Test Filter");
    }

    @Test
    void getFilterById_shouldReturnFilter() {
        var filter = new Filter();
        when(filterRepository.findFilterById(1L)).thenReturn(filter);

        Filter result = target.getFilterById(1L);

        assertThat(result, is(filter));
        verify(filterRepository, times(1)).findFilterById(1L);
    }

    @Test
    void createFilter_shouldSaveFilter() {
        var filter = new Filter();

        target.createFilter(filter);

        verify(filterRepository, times(1)).save(filter);
    }

    @Test
    void updateFilterCriteriaById_shouldUpdateFilter() {
        var filter = new Filter();
        var newCriteria = new Criteria()
                .setComparisonType(ComparisonType.EQUAL_TO)
                .setValue(20)
                .setField(FieldType.AMOUNT);
        var newFilter = new Filter()
                .setName("Updated Name")
                .setCriteriaList(List.of(newCriteria));

        when(filterRepository.findFilterById(1L)).thenReturn(filter);

        target.updateFilterCriteriaById(1L, newFilter);

        assertThat(filter.getName(), is("Updated Name"));
        assertThat(filter.getCriteriaList(), is(List.of(newCriteria)));
        verify(filterRepository, times(1)).findFilterById(1L);
        verify(filterRepository, times(1)).save(filter);
    }
}
