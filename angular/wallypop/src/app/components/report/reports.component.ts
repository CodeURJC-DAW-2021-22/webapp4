import {ReportService} from '../../services/report.service';
import {Component} from '@angular/core';

@Component({
  selector: 'reports',
  templateUrl: './reports.component.html'
})
export class ReportsComponent {

  constructor(public reportService: ReportService) {
    
   }
  
  }