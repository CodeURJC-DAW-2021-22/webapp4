import {ReportService} from '../../services/report.service';
import {Component, ViewChild} from '@angular/core';
import { Report } from 'src/app/models/report.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'formReport',
  templateUrl: './formReport.component.html'
})
export class FormReportComponent {
  report: Report;
  @ViewChild("file")
  file: any;
  

  constructor(public reportService: ReportService,private router: Router,activatedRoute: ActivatedRoute) {    
    this.report = { article:null,email:'', description: ''};
}
  


  save() {
    this.reportService.addReport(this.report).subscribe(
      (report: Report) => this.uploadImage(report),
      error => alert('Error creating new report: ' + error)
    );
  }

  uploadImage(reportProof: Report): void {

    const proof = this.file.nativeElement.files[0];
    if (proof) {
      let formData = new FormData();
      formData.append("imageFile", proof);
      this.reportService.setReportProof(reportProof, formData).subscribe(
        _ => this.afterUploadProof(reportProof),
        error => alert('Error uploading report proof: ' + error)
      );
    } 
     else {
      this.afterUploadProof(reportProof);
    }
  }


  private afterUploadProof(afterReportProof: Report){
    this.router.navigate(['./commercial',afterReportProof.id_REPORT]);
  }
  
  }