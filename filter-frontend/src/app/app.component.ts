import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FilterComponent } from './components/filter/filter.component';
import { DataTableComponent } from './components/data-table/data-table.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FilterComponent, DataTableComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'filter-frontend';
  isModalShown: boolean = false;

  @ViewChild(DataTableComponent)
  dataTableComponent!: DataTableComponent;

  onFilterClosed(mode: string) {
    if (mode === 'modal') {
      this.isModalShown = false;
    }
    this.dataTableComponent.loadData(); 
  }
  
  showFilterDialog(){
    this.isModalShown = true;
  }
}
