import {ReportService} from '../../services/report.service';
import {Component} from '@angular/core';

@Component({
  selector: 'formReport',
  templateUrl: './formReport.component.html'
})
export class formReportComponent {

  constructor(public reportService: ReportService) { }

    NewFormReport(event: any, email: string, description: string): void {
  
      event.preventDefault();
  
      this.reportService.newReport(email, description);
    }
  
  }