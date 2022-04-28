import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable , throwError} from "rxjs";
import { catchError } from "rxjs/operators";
import { Report } from "../models/report.model";

const BASE_URL = '/api/reports/';

@Injectable({ providedIn: 'root' })
export class ReportService {
    constructor(private http: HttpClient) {}

    newReport(email: string, description: string): void {
    }

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

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}

