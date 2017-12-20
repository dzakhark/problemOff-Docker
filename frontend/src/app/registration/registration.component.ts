import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import { RegisUser } from '../shared/classes/regisUser';
import { RegistrationService } from '../shared/services/registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public mask = ['(', /\d/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  isInvalidForm = false;
  isRegistrationError = false;
  regisForm: FormGroup;
  formErrors = {
    'firstName': '',
    'lastName': '',
    'email': '',
    'password': '',
    'phone': ''
  };
  validationMessages = {
    'firstName': {
      'required': '* Обязательное поле.',
    },
    'lastName': {
      'required': '* Обязательное поле.',
    },
    'email': {
      'required': '* Обязательное поле.',
      'pattern': '* Не правильный формат email адреса.'
    },
    'password': {
      'required': '* Обязательное поле.',
      'minlength': '* minlength password 8'
    },
    'phone': {
      'required': '* Обязательное поле.',
      'pattern': '* Не правильный формат'
    }
  };
  constructor(private fb: FormBuilder,
              private service: RegistrationService,
              private router: Router) { }

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.regisForm = this.fb.group({
      'firstName': ['', [Validators.required]],
      'lastName': ['', [Validators.required]],
      'email': ['', [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}')
      ]],
      'password': ['', [
        Validators.required,
        Validators.minLength(8)
      ]],
      'phone': ['', [
        Validators.required
      ]]
    });

    this.regisForm.valueChanges.subscribe(data => this.onValueChange(data));
  }

  onValueChange(data?: any) {
    if (!this.regisForm) return;
    let form = this.regisForm;

    for (let field in this.formErrors) {
      this.isInvalidForm = false;
      this.formErrors[field] = '';
      let control = form.get(field);
      if (control && control.dirty && !control.valid ) {
        let message = this.validationMessages[field];
        for (let key in control.errors) {
          this.formErrors[field] += message[key] + ' ';
        }
      }
    }
  }

  onRegistration(event) {
    if (this.regisForm.invalid) {
      this.isInvalidForm = true;
      event.preventDefault();
    } else {
      const newUser = new RegisUser(this.regisForm.value.firstName,
                                    this.regisForm.value.lastName,
                                    this.regisForm.value.email,
                                    this.regisForm.value.password,
                                    this.regisForm.value.phone);
      this.service.registerUser(newUser).subscribe(
        () => {
          this.isRegistrationError = false;
          this.router.navigate(['/login']);
        },
        (error) => {
          this.isRegistrationError = true;
          console.log(error);
        }
      );
      console.log('submitted');
    }
  }
}
