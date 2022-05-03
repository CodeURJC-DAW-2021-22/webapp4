import {ReportService} from '../../services/report.service';
import {Component, OnInit} from '@angular/core';
import { Report } from 'src/app/models/report.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'reports',
  templateUrl: './reports.component.html'
})
export class ReportsComponent implements OnInit {
  reports: Report[]
  constructor(public reportService: ReportService, public loginService: LoginService) {
    
   }
   ngOnInit() {
    this.reportService.getReports().subscribe(
      reports => this.reports = reports,
      error => console.log(error)
    );
  }
  }