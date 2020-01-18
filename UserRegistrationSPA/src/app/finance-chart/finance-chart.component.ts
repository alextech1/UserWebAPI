import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-finance-chart',
  templateUrl: './finance-chart.component.html',
  styleUrls: ['./finance-chart.component.css']
})
export class FinanceChartComponent implements OnInit {
  title: string;
  description: string;
  role: Number;
  public data:Object[];
  public xAxis: Object;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router)
    
    
    {   
    this.role = 1;
    this.data = [
      {month: 'Jan', sales: 35 }, {month: 'Feb', sales: 28 },
      {month: 'Mar', sales: 34 }, {month: 'Apr', sales: 32 },
      {month: 'May', sales: 40 }, {month: 'Jun', sales: 32 },
      {month: 'Jul', sales: 35 }, {month: 'Aug', sales: 55 },
      {month: 'Sep', sales: 38 }, {month: 'Oct', sales: 30 },
      {month: 'Nov', sales: 25 }, {month: 'Dec', sales: 32 }
    ];
    this.xAxis = {
      valueType: 'Category'
    };

    


  }

  ngOnInit() {
  }

}
