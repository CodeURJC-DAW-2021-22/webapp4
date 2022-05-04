import {ReportService} from '../../services/report.service';
import {Component, ViewChild} from '@angular/core';
import { Report } from 'src/app/models/report.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from 'src/app/services/article.service';
import { Article } from 'src/app/models/article.model';

@Component({
  selector: 'formReport',
  templateUrl: './formReport.component.html'
})
export class FormReportComponent {
  report: Report;
  article:Article;
  @ViewChild("file")
  file: any;

  

  constructor(public reportService: ReportService,private router: Router,private activatedRoute: ActivatedRoute,private articleService:ArticleService) {    
}
id_article:number;
ngOnInit(): void {
  this.id_article = this.activatedRoute.snapshot.params['id'];
  this.articleService.getArticle(this.id_article).subscribe(
      article => this.article = article,
      error => console.error(error)
  );    
    this.report = { article:this.article ,email:'', description: ''};  
}


  save(event:any,email:string,description:string) {
    console.log('report.save');
    this.reportService.addReport(email,description,this.id_article);
    /*this.reportService.addReport(this.report).subscribe(
      (report: Report) => this.uploadImage(report),
      error => alert('Error creating new report: ' + error)
    );*/
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