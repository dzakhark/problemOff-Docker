import { Injectable } from '@angular/core';
import {Http, Response, URLSearchParams, Headers, RequestOptions} from '@angular/http';
import {LoginUser} from '../classes/loginUser';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {CookieService} from 'ngx-cookie-service';
import {JwtHelper} from 'angular2-jwt';
import {Data} from '../data/data';

@Injectable()
export class AuthService {
  data = new Data();
  private urlLogIn = this.data.apiLinks.user.loginUrl;
  roles: string[] = this.setRoles();
  // roles: string[] = ['USER_ROLE', 'ADMIN_ROLE'];
  isUserRole: string = this.checkRole('USER_ROLE');
  isAdminRole: string = this.checkRole('ADMIN_ROLE');
  isLoggedIn: boolean = this.checkLogin();

  constructor(private http: Http,
              private cookieService: CookieService) { }

  loggedIn(user: LoginUser) {
    const params = new URLSearchParams();
    params.set('email', user.login);
    params.set('password', user.password);
    return this.http.post(this.urlLogIn, params)
      .catch(this.handleError);
  }

  // getUser() {
  //   const token = localStorage.getItem('currentUser');
  //   console.log(JSON.parse(token).token);
  //   const headers = new Headers();
  //   headers.set('X-Auth-Token', JSON.parse(token).token);
  //   const options = new RequestOptions({ headers: headers});
  //   const user = this.http.get(this.urlUsers, options)
  //     .map(this.extractUser)
  //     .catch(this.handleError);
  //   return user;
  // }

  // private extractUser(response: Response) {
  //   console.log(response.json());
  //   return response.json();
  // }

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

  checkLogin() {
    if (this.cookieService.check('_curUser')) {
      return true;
    }
    return false;
  }

  logout() {
    this.cookieService.delete('_curUser');
    this.cookieService.delete('_opt');
    this.isLoggedIn = this.checkLogin();
  }

  setRoles() {
    const options = this.cookieService.get('_opt');
    if (options) {
      const roles = this.decodeOptions(this.cookieService.get('_opt'));
      if (roles) {
        const result = [];
        for (const key in roles) {
          result.push(roles[key]);
        }
        console.log(result);
        console.log(result[2]);
        return result[2];
      }
    }
    return [];
  }

  decodeOptions(opt: string) {
    const jwtHelper: JwtHelper = new JwtHelper();
    return jwtHelper.decodeToken(opt);
  }

  checkRole(role: string) {
    return this.roles.find((r) => r === role);
  }
}
