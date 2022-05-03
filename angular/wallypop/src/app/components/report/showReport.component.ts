import {ReportService} from '../../services/report.service';
import {Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Report } from 'src/app/models/report.model';

@Component({
  selector: 'showReport',
  templateUrl: './showReport.component.html'
})
export class ShowReportComponent implements OnInit{
  report:Report;

  constructor(private reportService: ReportService,private activatedRoute: ActivatedRoute) {
   }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.reportService.getReport(id).subscribe(
        report => this.report = report,
        error => console.error(error)
    );    
  console.log(this.report);
  }


  }