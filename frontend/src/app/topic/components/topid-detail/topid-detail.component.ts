import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-topid-detail',
  templateUrl: './topid-detail.component.html',
  styleUrls: ['./topid-detail.component.css']
})
export class TopidDetailComponent implements OnInit {
  @Input('topid') topid: number;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
  }

}
