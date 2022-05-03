import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable , throwError} from "rxjs";
import { catchError } from "rxjs/operators";
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
		return this.httpClient.delete(BASE_URL + report.id_REPORT).pipe(
			catchError(error => this.handleError(error))
		);
	}


	addReport(report: Report) {
    //Deberia ser (id_article:number) y abajo BASE_URL+"reports/"+id_article   
        if (!report.id_REPORT) {
			return this.httpClient.post(BASE_URL + "reports/3", report)
				.pipe(
					catchError(error => this.handleError({ error }))
				);
		} else {
			return this.httpClient.put(BASE_URL + "reports" + report.id_REPORT, report).pipe(
				catchError(error => this.handleError({ error }))
			);
		}
		
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

