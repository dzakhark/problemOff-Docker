import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserRoleGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.authService.isLoggedIn || (this.authService.isLoggedIn && this.authService.checkRole('USER_ROLE'))) {
      return true;
    } else if (this.authService.isAdminRole) {
      this.router.navigate(['/admin']);
      return false;
    } else {
      this.router.navigate(['/error']);
      return false;
    }
  }
}
