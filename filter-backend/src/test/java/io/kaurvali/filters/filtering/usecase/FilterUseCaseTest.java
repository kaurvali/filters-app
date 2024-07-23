package io.kaurvali.filters.filtering.usecase;

import io.kaurvali.filters.application.error.exception.ResourceAlreadyExistsException;
import io.kaurvali.filters.application.error.exception.ResourceNotFoundException;
import io.kaurvali.filters.filtering.domain.Filter;
import io.kaurvali.filters.filtering.function.data.FilterDataResponse;
import io.kaurvali.filters.filtering.function.data.FilterUpdateRequest;
import io.kaurvali.filters.filtering.mapper.FilterDataMapper;
import io.kaurvali.filters.filtering.service.FilterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterUseCaseTest {

    @Mock
    private FilterService filterService;
    @Mock
    private FilterDataMapper filterDataMapper;
    @InjectMocks
    private FilterUseCase target;

    @Test
    void createFilter_success() {
        var request = new FilterUpdateRequest();
        request.setName("TestFilter");

        when(filterService.existsByName("TestFilter")).thenReturn(false);
        when(filterDataMapper.map(request)).thenReturn(new Filter());

        var response = target.createFilter(request);

        assertThat(response.getStatus(), is("ok"));
        assertThat(response.getStatusMessage(), is("Filter created"));
        verify(filterService).createFilter(any(Filter.class));
    }

    @Test
    void createFilter_alreadyExists() {
        var request = new FilterUpdateRequest();
        request.setName("ExistingFilter");

        when(filterService.existsByName("ExistingFilter")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> target.createFilter(request));
    }

    @Test
    void getFilter_success() {
        var filterId = 1L;
        var filter = new Filter();
        var response = new FilterDataResponse();

        when(filterService.existsById(filterId)).thenReturn(true);
        when(filterService.getFilterById(filterId)).thenReturn(filter);
        when(filterDataMapper.map(filter)).thenReturn(response);

        var result = target.getFilter(filterId);

        assertThat(result, is(response));
    }

    @Test
    void getFilter_notFound() {
        var filterId = 1L;

        when(filterService.existsById(filterId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> target.getFilter(filterId));
    }

    @Test
    void getAllFilters_success() {
        var filters = List.of(new Filter(), new Filter());
        var responses = List.of(new FilterDataResponse(), new FilterDataResponse());

        when(filterService.getAllFilters()).thenReturn(filters);
        when(filterDataMapper.map(filters)).thenReturn(responses);

        var result = target.getAllFilters();

        assertThat(result, is(responses));
    }

    @Test
    void updateFilter_success() {
        var filterId = 1L;
        var request = new FilterUpdateRequest();
        var filter = new Filter();

        when(filterService.existsById(filterId)).thenReturn(true);
        when(filterDataMapper.map(request)).thenReturn(filter);

        var response = target.updateFilter(filterId, request);

        assertThat(response.getStatus(), is("ok"));
        assertThat(response.getStatusMessage(), is("Filter updated"));
        verify(filterService).updateFilterCriteriaById(filterId, filter);
    }

    @Test
    void updateFilter_notFound() {
        var filterId = 1L;
        var request = new FilterUpdateRequest();

        when(filterService.existsById(filterId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> target.updateFilter(filterId, request));
    }
}
