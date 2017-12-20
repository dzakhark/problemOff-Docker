import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { RegisUser } from '../classes/regisUser';
import { Data } from '../data/data';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class RegistrationService {
  data = new Data();
  private urlReg = this.data.apiLinks.user.registrationUrl;

  constructor(private http: Http) { }

  public registerUser(regisUser: RegisUser) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const options = new RequestOptions({ headers: headers });
    console.log(regisUser);
    return this.http.post(this.urlReg, regisUser, options)
      .catch(this.handleError);
  }

  private handleError(error: any, cought: Observable<any>): any {
    let message = '';

    if (error instanceof Response) {
      const errorData = error.json().error || JSON.stringify(error.json());
      message = `${error.status} - ${error.statusText || ''} ${errorData}`;
    } else {
      message = error.message ? error.message : error.toString();
    }

    console.error(message);

    return Observable.throw(message);
  }
}
