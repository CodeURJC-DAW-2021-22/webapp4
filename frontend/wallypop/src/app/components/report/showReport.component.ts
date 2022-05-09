import {ReportService} from '../../services/report.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Report} from 'src/app/models/report.model';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'showReport',
    templateUrl: './showReport.component.html'
})
export class ShowReportComponent implements OnInit {
    report: Report;

    constructor(private reportService: ReportService, private activatedRoute: ActivatedRoute, private router: Router, public loginService: LoginService) {
    }

    ngOnInit(): void {
        const id = this.activatedRoute.snapshot.params.id;
        this.reportService.getReport(id).subscribe(
            report => this.report = report,
            error => console.error(error)
        );
    }

    rejectReport(): void {
        this.reportService.deleteReport(this.report).subscribe(
            _ => this.router.navigate(['/reports']),
            error => console.error(error)
        );
        this.router.navigate(['reports']);
    }

    aceptReport(): void {
        this.reportService.aceptReport(this.report).subscribe(
            _ => this.router.navigate(['/reports']),
            error => console.error(error)
        );
        this.router.navigate(['reports']);
    }
}
