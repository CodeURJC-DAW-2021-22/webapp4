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
    this.reportService.addReport(email,description,this.id_article).subscribe(
      (report: Report) => this.uploadImage(report),
      error => alert('Error creating new report: ' + error)
    );
    /*this.reportService.addReport(this.report).subscribe(
      (report: Report) => this.uploadImage(report),
      error => alert('Error creating new report: ' + error)
    );*/
  }

  
  uploadImage(reportImage: Report): void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let form = new FormData();     
      form.append("imageFile", image);
      this.reportService.setReportProof(reportImage, form).subscribe(
        _ => this.afterUploadImage(reportImage),
        error => alert('Error uploading category image: ' + error)
      );
    } else {
      this.afterUploadImage(reportImage);
    }
  }

  private afterUploadImage(afterReportProof: Report){
    this.router.navigate(['/post/'+afterReportProof.article.id_ARTICLE]);
  }
  
  }