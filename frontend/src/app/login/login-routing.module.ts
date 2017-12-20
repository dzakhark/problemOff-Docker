import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuardService } from '../shared/guards/auth-guard.service';
import { AuthService } from '../shared/services/auth.service';
import { LoginComponent } from './login.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: 'login', component: LoginComponent, canActivate: [AuthGuardService] }
    ])
  ],
  exports: [RouterModule],
  providers: [AuthService, AuthGuardService]
})
export class LoginRoutingModule { }
