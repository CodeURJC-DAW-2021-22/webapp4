import {ReportService} from '../../services/report.service';
import {Component, ViewChild} from '@angular/core';
import { Report } from 'src/app/models/report.model';
import { Router } from '@angular/router';

@Component({
  selector: 'formReport',
  templateUrl: './formReport.component.html'
})
export class FormReportComponent {


  constructor(public reportService: ReportService,private router: Router,) {}
  report: Report;
  @ViewChild("file")
  file: any;
  
  save() {

    this.reportService.addReport(this.report).subscribe(
      (report: Report) => this.uploadImage(report),
      error => alert('Error creating new report: ' + error)
    );
  }

  uploadImage(report: Report): void {

    const proof = this.file.nativeElement.files[0];
    if (proof) {
      let formData = new FormData();
      formData.append("imageFile", proof);
      this.reportService.setReportProof(proof, formData).subscribe(
        _ => this.afterUploadProof(proof),
        error => alert('Error uploading report proof: ' + error)
      );
    } 
     else {
      this.afterUploadProof(report);
    }
  }

  private afterUploadProof(report: Report){
    this.router.navigate(['./commercial']);
  }
  
  }