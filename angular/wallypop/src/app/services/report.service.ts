import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable , throwError} from "rxjs";
import { catchError } from "rxjs/operators";
import { Report } from "../models/report.model";

const BASE_URL = '/api/reports/';

@Injectable({ providedIn: 'root' })
export class ReportService {
    constructor(private http: HttpClient) {}

	getReports(): Observable<Report[]> {
		return this.http.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Report[]>;
	}

	getReport(id: number | string): Observable<Report> {
		return this.http.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Report>;
	}

	deleteReport(report: Report) {
		return this.http.delete(BASE_URL + report.id_REPORT).pipe(
			catchError(error => this.handleError(error))
		);
	}


	addReport(report: Report) {

		if (!report.id_REPORT) {
			return this.http.post(BASE_URL, report)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.http.put(BASE_URL + +report.id_REPORT, report).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

	setReportProof(report: Report, formData: FormData) {
		return this.http.post(BASE_URL + report.id_REPORT + '/proof', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}

