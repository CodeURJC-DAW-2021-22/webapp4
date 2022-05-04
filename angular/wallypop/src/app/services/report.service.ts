import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable , throwError} from "rxjs";
import { catchError } from "rxjs/operators";
import { Article } from "../models/article.model";
import { Report } from "../models/report.model";


const BASE_URL = '/api/';

@Injectable({ providedIn: 'root' })
export class ReportService {
    constructor(private httpClient: HttpClient, private router: Router) {}


	getReports(): Observable<Report[]> {
		return this.httpClient.get(BASE_URL+"admin/reports").pipe(
			catchError(error => this.handleError(error))
		) as Observable<Report[]>;
	}

	getReport(id: number | string): Observable<Report> {
		return this.httpClient.get(BASE_URL +"admin/reports/"+ id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Report>;
	}

	deleteReport(report: Report) {
		this.httpClient.delete(BASE_URL +"admin/reports/"+report.id_REPORT+"/rejectReport").pipe(
			catchError(error => this.handleError(error))
		);
		this.router.navigate(['reports']);
	}

	aceptReport(report: Report) {
		this.httpClient.delete(BASE_URL +"admin/reports/"+report.id_REPORT+"/aceptReport").pipe(
			catchError(error => this.handleError(error))
		);
		this.router.navigate(['reports']);
	}

	proof(report: Report) {
		return this.httpClient.get(BASE_URL +"admin/reports/"+report.id_REPORT+"/proof").pipe(
			catchError(error => this.handleError(error))
		);
	}


	addReport(email:string,description:string,id:number) {        
			this.httpClient.post(BASE_URL + "reports/"+id, {email,description})
            .subscribe(
                (response) => this.router.navigate(['post/'+id]),
                (error) => alert('Ha ocurrido un error en el reporte')
            );
		
	}
	
	createForm(article:Article){
			return this.httpClient.post(BASE_URL + "reports/" +article.id_ARTICLE,article);	
	}
    
	setReportProof(report: Report, formData: FormData) {
		return this.httpClient.post(BASE_URL +'reports/'+ report.id_REPORT + '/proof', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}	

	private handleError(error: any): Observable<never> {
        console.log('ERROR:');
        console.error(error);
        return throwError('Server error (' + error.status + '): ' + error.text());
    }
}

