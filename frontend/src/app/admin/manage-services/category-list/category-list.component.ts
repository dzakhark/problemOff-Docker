import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Category} from '../../../shared/classes/category';
import {DataForModal} from '../../../shared/classes/dataForModal';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  @Input()
  inputCategory: Category;
  @Input()
  inputCategories: Category[];
  @Output()
  selectSubCategory = new EventEmitter();
  @Output()
  refreshCategory = new EventEmitter();
  dataForModal: DataForModal = new DataForModal('', new Category('', [], [], null));

  constructor() { }

  ngOnInit() {
  }

  selected(category) {
    this.selectSubCategory.emit(category);
  }

  edit(category: Category) {
    this.dataForModal.data = category;
    this.dataForModal.action = 'edit';
  }

  add(category: Category) {
    this.dataForModal.data = category;
    this.dataForModal.action = 'add';
  }

  delete(category: Category) {
    this.dataForModal.data = category;
    this.dataForModal.action = 'delete';
  }
}
