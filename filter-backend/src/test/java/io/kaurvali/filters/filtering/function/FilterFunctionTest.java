package io.kaurvali.filters.filtering.function;

import io.kaurvali.filters.application.request.RequestBodyValidator;
import io.kaurvali.filters.filtering.function.data.FilterDataResponse;
import io.kaurvali.filters.filtering.function.data.FilterUpdateRequest;
import io.kaurvali.filters.filtering.function.data.FilterUpdateResponse;
import io.kaurvali.filters.filtering.usecase.FilterUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterFunctionTest {

    private static final String REQUEST_ID = "requestId";
    @Mock
    private RequestBodyValidator requestBodyValidator;
    @Mock
    private FilterUseCase filterUsecase;
    @InjectMocks
    private FilterFunction target;

    @Test
    void handleGetFilter_success() {
        when(filterUsecase.getFilter(any())).thenReturn(new FilterDataResponse());

        target.handleGetFilter(REQUEST_ID, 1L);

        verify(filterUsecase).getFilter(any());
    }

    @Test
    void handleGetFilters_success() {
        when(filterUsecase.getAllFilters()).thenReturn(List.of(new FilterDataResponse()));

        target.handleGetFilters(REQUEST_ID);

        verify(filterUsecase).getAllFilters();
    }

    @Test
    void handleUpdateFilter_success() {
        when(filterUsecase.updateFilter(any(), any())).thenReturn(new FilterUpdateResponse());

        var result = target.handleUpdateFilter(1L, REQUEST_ID, new FilterUpdateRequest());

        assertThat(result, notNullValue());

        verify(filterUsecase).updateFilter(any(), any());
        verify(requestBodyValidator).validate(any());
    }

    @Test
    void handleCreateFilter_success() {
        when(filterUsecase.createFilter(any())).thenReturn(new FilterUpdateResponse());

        var result = target.handleCreateFilter(REQUEST_ID, new FilterUpdateRequest());

        assertThat(result, notNullValue());

        verify(filterUsecase).createFilter(any());
        verify(requestBodyValidator).validate(any());
    }

}
