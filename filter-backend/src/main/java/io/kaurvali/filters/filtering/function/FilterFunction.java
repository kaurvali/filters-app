package io.kaurvali.filters.filtering.function;

import io.kaurvali.filters.application.request.RequestBodyValidator;
import io.kaurvali.filters.filtering.function.data.FilterDataResponse;
import io.kaurvali.filters.filtering.function.data.FilterUpdateRequest;
import io.kaurvali.filters.filtering.function.data.FilterUpdateResponse;
import io.kaurvali.filters.filtering.usecase.FilterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilterFunction {

    private final RequestBodyValidator requestBodyValidator;
    private final FilterUseCase filterUsecase;

    @Tag(name = "Filter")
    @GetMapping(value = "/api/v1/filter/{id}")
    @Operation(summary = "Get a filter", description = "Returns a filter with an certain id")
    public ResponseEntity<FilterDataResponse> handleGetFilter(
            @RequestHeader(name = "X-Request-ID") String requestId,
            @PathVariable @NotNull @Positive Long id) {

        return ResponseEntity.ok().body(filterUsecase.getFilter(id));
    }

    @Tag(name = "Filter")
    @Operation(summary = "Get all filters", description = "Returns a JSON list of all filters")
    @GetMapping(value = "/api/v1/filters")
    public ResponseEntity<List<FilterDataResponse>> handleGetFilters(
            @RequestHeader(name = "X-Request-ID") String requestId) {

        return ResponseEntity.ok().body(filterUsecase.getAllFilters());
    }

    @Tag(name = "Filter")
    @Operation(summary = "Update filter", description = "Updates an existing filter with the supplied data")
    @PutMapping(value = "/api/v1/filter/{id}")
    public ResponseEntity<FilterUpdateResponse> handleUpdateFilter(
            @PathVariable @NotNull @Positive Long id,
            @RequestHeader(name = "X-Request-ID") String requestId,
            @RequestBody FilterUpdateRequest request) {
        requestBodyValidator.validate(request);

        return ResponseEntity.ok().body(filterUsecase.updateFilter(id, request));
    }

    @Tag(name = "Filter")
    @Operation(summary = "New filter", description = "Creates a new filter with the supplied data")
    @PostMapping(value = "/api/v1/filter", consumes = "application/json")
    public ResponseEntity<FilterUpdateResponse> handleCreateFilter(
            @RequestHeader(name = "X-Request-ID") String requestId,
            @RequestBody FilterUpdateRequest request) {
        requestBodyValidator.validate(request);

        return ResponseEntity.ok().body(filterUsecase.createFilter(request));
    }
}
