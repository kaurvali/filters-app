import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ComparisonMapperService } from '../../services/comparison-mapper.service';
import { ComparisonType } from '../../enums/comparison-type.enum';
import { FieldType } from '../../enums/field-type.enum';
import { Criteria } from '../../model/criteria.model';
import { BackendDataService } from '../../services/backend-data.service';

@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss'],
})
export class FilterComponent implements OnInit {
  @Input() isModal: boolean = false;
  @Input() isCollapsed: boolean = false;
  @Output() closeFilter = new EventEmitter<string>();

  name: string = "";
  criterias: Criteria[] = [];
  showErrorPopup: boolean = false;
  errorMessage: string | undefined;

  constructor(private comparisonMapperService: ComparisonMapperService, private dataService: BackendDataService) { }

  ngOnInit() {
    this.addCriteria();
  }

  addCriteria() {
    this.criterias.push({ field: FieldType.AMOUNT, comparison: ComparisonType.EQUAL_TO, value: undefined });
  }

  removeCriteria(index: number) {
    this.criterias.splice(index, 1);
  }

  getComparisonTypes(field: FieldType): ComparisonType[] {
    return this.comparisonMapperService.getComparisonTypes(field);
  }

  onFieldChange(filter: any) {
    const comparisonTypes = this.getComparisonTypes(filter.field);
    filter.comparison = comparisonTypes[0];
    filter.value = undefined;
  }

  getReadableComparisonType(type: ComparisonType): string {
    return this.comparisonMapperService.getReadableComparisonType(type);
  }

  toggleView() {
    if (this.isModal) {
      this.close();
    }
  }

  toggleCollapse() {
    if (!this.isModal) {
      this.isCollapsed = !this.isCollapsed;
    }
  }

  close() {
    this.closeFilter.emit(this.isModal ? 'modal' : 'non-modal');
    this.isCollapsed = true;
  
  }

  save() {
    if (this.criterias.length > 0) {
      this.makeBackEndRequest()
    } else {
      this.errorMessage = "Please add at least one criteria."
      this.showErrorPopup = true;
    }
  }

  closeErrorPopup() {
    this.showErrorPopup = false;
    this.errorMessage = undefined;
  }

  openErrorPopup(errorMessage: string) {
    this.showErrorPopup = true;
    this.errorMessage = errorMessage;
  }

  makeBackEndRequest() {
    this.dataService.addFilter({
      name: this.name,
      criteria_list: this.criterias
    }).subscribe({
      next: (response) => {
        this.close();
      },
      error: (err) => {
        console.log(err.message);
        this.openErrorPopup(err.message);
      }
    });
  }
}
