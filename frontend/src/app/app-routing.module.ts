import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { ErrorComponent } from './error/error.component';
import { UserRoleGuardService } from './shared/guards/user-role-guard.service';

// В данном примере настройки маршрутизации выделены в отдельный модуль.

@NgModule({
  imports: [RouterModule.forRoot([
    { path: '', component: HomeComponent, pathMatch: 'full', canActivate: [UserRoleGuardService]},
    { path: '**', component: ErrorComponent }
  ])],
  providers: [UserRoleGuardService],
  exports: [RouterModule], // делаем re-export модуля для использования директив при маршрутизации
})
export class AppRoutingModule { }
