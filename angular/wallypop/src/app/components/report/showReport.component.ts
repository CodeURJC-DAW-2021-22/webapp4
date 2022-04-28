import {ReportService} from '../../services/report.service';
import {Component} from '@angular/core';

@Component({
  selector: 'showReport',
  templateUrl: './showReport.component.html'
})
export class ShowReportComponent {

  constructor(public reportService: ReportService) {

   }

  }