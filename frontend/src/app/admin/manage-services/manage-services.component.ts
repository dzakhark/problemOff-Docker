import { AfterViewChecked, Component, OnInit } from '@angular/core';
import { CategoriesService } from '../../shared/services/categories.service';
import { Category } from '../../shared/classes/category';
import { Data } from '../../shared/data/data';

@Component({
  selector: 'app-manage-services',
  templateUrl: './manage-services.component.html',
  styleUrls: ['./manage-services.component.css']
})
export class ManageServicesComponent implements OnInit, AfterViewChecked {

  data = new Data();
  mainCategories: Category[];
  mainLink = this.data.apiLinks.admin.getMainCategories;
  mainCategory: Category;
  selectCategory: Category;

  constructor(private categoriesService: CategoriesService) { }

  ngOnInit() {
    this.getCategory();
  }

  ngAfterViewChecked(): void {
    if (this.categoriesService.isModify) {
      this.getCategory();
      this.categoriesService.isModify = false;
    }
  }

  public getCategory() {
    this.categoriesService.getCategories(this.data.apiLinks.admin.getMainCategories).subscribe(
      categories => {
        this.mainCategories = categories;
        this.mainCategory = new Category('Главные', [], this.mainCategories, { self: { href: this.mainLink } });
        this.selectCategory = this.mainCategory;
      },
      error => console.log(error)
    );
  }

  goToSubCategory(category) {
    this.selectCategory = category;
  }
}
