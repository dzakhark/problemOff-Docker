import {
  AfterContentChecked,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output
} from '@angular/core';
import {Category} from '../../../shared/classes/category';
import {CategoriesService} from '../../../shared/services/categories.service';


@Component({
  selector: 'app-category-breadcrumb',
  templateUrl: './category-breadcrumb.component.html',
  styleUrls: ['./category-breadcrumb.component.css']
})
export class CategoryBreadcrumbComponent implements OnInit, OnChanges, AfterContentChecked {

  @Input()
  inputCategory: Category;
  @Output()
  selectInBreadcrumb = new EventEmitter();
  breadcrumbList: Category[] = [];

  constructor(private categoriesService: CategoriesService) { }

  ngOnInit() {
  }

  selectCategoryName(category: Category) {
    console.log(category);
    const index = this.breadcrumbList.indexOf(category);
    if (index !== -1) {
      if (index === this.breadcrumbList.length - 1) {
        return ;
      } else {
        this.breadcrumbList.splice(index);
        this.selectInBreadcrumb.emit(category);
      }
    }
  }

  ngOnChanges() {
    if (this.inputCategory) {
      this.breadcrumbList.push(this.inputCategory);
    }
  }

  ngAfterContentChecked() {
    if (this.categoriesService.isModifyForBreadcrumb) {
      this.breadcrumbList.splice(0);
      this.categoriesService.isModifyForBreadcrumb = false;
    }
  }
}
