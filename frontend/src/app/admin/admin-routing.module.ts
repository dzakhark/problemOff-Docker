import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ManageServicesComponent } from './manage-services/manage-services.component';
import { AdminRoleGuardService } from '../shared/guards/admin-role-guard.service';
import { ManageUsersComponent } from './manage-users/manage-users.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'admin',
        component: AdminHomeComponent,
        canActivate: [AdminRoleGuardService],
        children: [
          {
            path: '',
            children: [
              { path: 'services', component: ManageServicesComponent },
              { path: 'users', component: ManageUsersComponent }
            ]
          }
        ]
      }
    ])
  ],
  exports: [
    RouterModule
  ],
  providers: [AdminRoleGuardService]
})
export class AdminRoutingModule { }
