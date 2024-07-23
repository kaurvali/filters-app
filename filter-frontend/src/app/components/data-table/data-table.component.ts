import { Component, OnInit } from '@angular/core';
import { Filter } from '../../model/filter.model';
import { ComparisonMapperService } from '../../services/comparison-mapper.service';
import { ComparisonType } from '../../enums/comparison-type.enum';
import { BackendDataService } from '../../services/backend-data.service';
import { CommonModule } from '@angular/common';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Component({
  selector: 'app-data-table',
  standalone: true,
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss'],
  imports: [CommonModule]
})
export class DataTableComponent implements OnInit {
  data: Filter[] | undefined;

  private dataSubject = new BehaviorSubject<Filter[] | undefined>(undefined);
  data$ = this.dataSubject.asObservable();

  constructor(private comparisonMapperService: ComparisonMapperService, private dataService: BackendDataService) { }

  ngOnInit(): void {
    this.loadData()
  }

  getReadableComparisonType(comparison: ComparisonType): string {
    return this.comparisonMapperService.getReadableComparisonType(comparison);
  }

  loadData() {
    this.dataService.getAllFilters().subscribe({
      next: (response) => {
        this.data = response
      },
      error: (err) => {
        console.log(err.message);
      }
    });
  }
}
