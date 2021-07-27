import { AuthService } from '../../_services/auth.service';
import { Component, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { UsernameValidator } from './username.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  form = this.fb.group({
    username: new FormControl('', [
      Validators.required,
      UsernameValidator.cannotContainSpace,
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(10),
    ]),
    confirmPassword: new FormControl('', Validators.required),
    firstname: new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    email: new FormControl(null, [Validators.required, Validators.email]),
    phone: new FormControl(null, [
      Validators.required,
      Validators.pattern(
        '^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$'
      ),
    ]),
    roles: new FormArray([]),
  });
  isSuccessful = false;
  isSignupFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private fb: FormBuilder) {}

  ngOnInit(): void {}

  public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { whitespace: true };
  }

  onChange(event: any) {
    console.log(event);
    console.log(this.form);
    const roleFormArray = <FormArray>this.form.controls.roles;
    if (event.target.checked) {
      roleFormArray.push(this.fb.control(event.target.defaultValue));
    } else {
      let index = roleFormArray.controls.findIndex(
        (x) => x.value == event.target.defaultValue
      );
      roleFormArray.removeAt(index);
    }
  }

  get username() {
    return this.form.get('username');
  }
  get password() {
    return this.form.get('password');
  }

  get confirmPassword() {
    return this.form.get('confirmPassword');
  }
  get firstname() {
    return this.form.get('firstname');
  }
  get lastname() {
    return this.form.get('lastname');
  }

  get email() {
    return this.form.get('email');
  }
  get phone() {
    return this.form.get('phone');
  }

  get roles() {
    return this.form.get('roles');
  }

  onSubmit(): void {
    console.log(this.form.value);
    this.authService.userRegister(this.form.value).subscribe(
      (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignupFailed = false;
      },
      (err) => {
        this.errorMessage = err.error.message;
        console.log(this.errorMessage);

        this.isSignupFailed = true;
      }
    );
  }
}