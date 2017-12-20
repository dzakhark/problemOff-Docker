import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {Category} from '../../../../shared/classes/category';
import {DataForModal} from '../../../../shared/classes/dataForModal';
import {CategoriesService} from '../../../../shared/services/categories.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  @Input()
  modalData: DataForModal;
  nameOfNewCategory = '';

  constructor(private categoriesService: CategoriesService) { }

  ngOnInit() {
  }

  add() {
    if (!this.nameOfNewCategory) {
      console.log('Not field');
    } else {
      let link = '';
      if (this.modalData.data.name === 'Главные') {
        link = 'https://problemoff.herokuapp.com/api/admin/categories';
      } else {
        link = this.modalData.data._links['createSubCategory'].href;
      }
      this.categoriesService.createCategory(link, {name: this.nameOfNewCategory.trim()}).subscribe(
        response => {
          console.log(response['_body']);
          this.categoriesService.isModify = true;
          this.categoriesService.isModifyForBreadcrumb = true;
        },
        error => console.log(error)
      );
    }
  }

  delete() {
    console.log(this.modalData.data._links['self']);
    this.categoriesService.deleteCategory(this.modalData.data._links['self'].href).subscribe(
      response => {
        console.log(response['_body']);
        this.categoriesService.isModify = true;
        this.categoriesService.isModifyForBreadcrumb = true;
      },
      error => console.log(error)
    );
  }

}
