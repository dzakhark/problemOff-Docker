import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AuthGuardService} from '../shared/guards/auth-guard.service';
import { RegistrationComponent } from './registration.component';
import {RegistrationService} from '../shared/services/registration.service';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: 'registration', component: RegistrationComponent, canActivate: [AuthGuardService] }
    ])
  ],
  exports: [RouterModule],
  providers: [AuthGuardService, RegistrationService]
})
export class RegistrationRoutingModule { }
