import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Semester } from '../models/Semester';

import { ProfInfo } from '../models/ProfInfo';

@Injectable()
export class CommonService {

  constructor(private http: HttpClient) { }

  private urlListSemester = 'http://localhost:8080/listSemester';
  private profListlUrl = 'http://localhost:8080/listProf';

  getListSemester(): Observable<Semester[]> {
    return this.http.get<Semester[]>(this.urlListSemester);
  }

  /**
   * getListProf
   */
  public getListProf(): Observable<ProfInfo[]> {
    return this.http.get<ProfInfo[]>(this.profListlUrl);
  }
}
