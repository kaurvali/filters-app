import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Filter } from '../model/filter.model';
import { v4 as uuidv4 } from 'uuid';
import { Criteria } from '../model/criteria.model';

@Injectable({
    providedIn: 'root'
})
export class BackendDataService {
    private baseUri = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    getAllFilters() {
        return this.http.get<Filter[]>(this.baseUri + '/api/v1/filters', { headers: this.getHeaders() })
            .pipe(
                catchError(this.handleError)
            );
    }

    addFilter(data: { name: string, criteria_list: Criteria[] }) {
        return this.http.post<any>(this.baseUri + '/api/v1/filter', data, { headers: this.getHeaders() })
            .pipe(
                catchError(this.handleError)
            );
    }

    updateFilter(id: number, data: { name: string, criteria_list: Criteria[] }) {
        const url = `${this.baseUri}/${id}`;
        return this.http.put<any>(url, data, { headers: this.getHeaders() })
            .pipe(
                catchError(this.handleError)
            );
    }

    private handleError(error: HttpErrorResponse) {
        let errorMessage;
        switch (error.status) {
            case 400:
                errorMessage = `Error: Something is wrong with the Filter - ${error.error.message}`;
                break;
            case 404:
                errorMessage = `Error: Filter not found - ${error.error.message}`;
                break;
            case 409:
                errorMessage = `Error: Filter with such name already exists - ${error.error.message}`;
                break;
            default:
                errorMessage = 'Error: Please try again or contact the administrator.';
        }
        return throwError(() => new Error(errorMessage));
    }

    private getHeaders(): HttpHeaders {
        return new HttpHeaders({
            'Content-Type': 'application/json',
            'X-Request-Id': uuidv4()
        });
    }
}
