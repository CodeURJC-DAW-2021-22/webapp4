import {Component, VERSION, OnInit, AfterViewInit} from '@angular/core';
import * as Highcharts from 'highcharts';
import {CategoryService} from "../../services/category.service";

declare var require: any;
const More = require('highcharts/highcharts-more');
More(Highcharts);

const Exporting = require('highcharts/modules/exporting');
Exporting(Highcharts);

const ExportData = require('highcharts/modules/export-data');
ExportData(Highcharts);

const Accessibility = require('highcharts/modules/accessibility');
Accessibility(Highcharts);

@Component({
    selector: 'graphic',
    templateUrl: './graphic.component.html'
})
export class GraphicComponent  {

    Highcharts: typeof Highcharts = Highcharts;
    updateFlag = false;


    public content = [];
    constructor(public categoryService: CategoryService) {
        this.getGraphicData();
        this.inited(this.content);
    }

    getGraphicData(): void {
        this.categoryService.getGraphicContent().subscribe(value => {
            value.forEach(d =>{
                this.content.push([d.name.toString(), d.y.valueOf()])
            })
        })
    }

    chartOptions: Highcharts.Options;
    inited(content: any): void {
        this.chartOptions = {
            chart: {
                events: {
                    load: function () {
                        var series = this.series[0],
                            last = series.data[series.data.length - 1];
                        /*setInterval(function() {
                          var p1 = Math.random() * 3;
                          series.addPoint(p1);
                        }, 10)*/
                    },
                },
            },
            series: [
                {
                    type: 'pie',
                    data: content,
                },
            ],
        };
    }

    handleUpdate() {

        this.chartOptions.title = {
            text: 'Artículos por categoría',
        };

        this.chartOptions.series[0] = {
            type: 'pie',
            data: this.content,
        };

        this.updateFlag = true;
    }
}
