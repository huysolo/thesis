import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Semester } from '../models/Semester';

@Injectable()
export class CommonService {

  constructor(private http: HttpClient) { }

  private uslListSemester = 'http://localhost:8080/listSemester';

  getListSemester(): Observable<Semester[]> {
    return this.http.get<Semester[]>(this.uslListSemester);
  }
}
