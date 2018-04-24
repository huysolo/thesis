import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UploadFileService {

  constructor(private http: HttpClient) {}

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('id', '1');

    const req = new HttpRequest('POST', 'http://localhost:8080/post', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<string[]> {
    return this.http.get<string[]>('/getallfiles');
  }
}
