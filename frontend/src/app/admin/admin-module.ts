import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ManageServicesComponent } from './manage-services/manage-services.component';
import { ManageUsersComponent} from './manage-users/manage-users.component';
import { AdminHeaderComponent } from './admin-header/admin-header.component';
import { CategoryListComponent } from './manage-services/category-list/category-list.component';
import { CategoryItemComponent } from './manage-services/category-item/category-item.component';
import { CategoryBreadcrumbComponent } from './manage-services/category-breadcrumb/category-breadcrumb.component';
import {CategoriesService} from '../shared/services/categories.service';
import {ModalComponent} from './manage-services/category-list/modal/modal.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ],
  declarations: [
    AdminHomeComponent,
    ManageServicesComponent,
    ManageUsersComponent,
    AdminHeaderComponent,
    CategoryBreadcrumbComponent,
    CategoryListComponent,
    CategoryItemComponent,
    ModalComponent
  ],
  providers: [CategoriesService]
})
export class AdminModule { }
