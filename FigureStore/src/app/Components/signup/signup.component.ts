import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AccountService} from '../../Services/account.service';
import {Address} from '../../ApiModels/address';
import {NewUserInfo} from '../../ApiModels/new-user-info';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  showShippingAddress = false;
  error = false;

  constructor(private builder: FormBuilder, private accountService: AccountService, private router: Router) { }

  ngOnInit(): void {
    this.signupForm = this.builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      verifyPassword: ['', Validators.required],
      billingStreet: ['', Validators.required],
      billingCity: ['', Validators.required],
      billingState: ['', Validators.required],
      shippingStreet: [''],
      shippingCity: [''],
      shippingState: [''],
      sameAsBilling: [!this.showShippingAddress]
    });

    this.signupForm.get('sameAsBilling').valueChanges.subscribe(
      value => {
        this.showShippingAddress = !value;
        this.setShippingAddressValidators(value);
        console.log(value);
        console.log(this.showShippingAddress);
      }
    );
  }

  setShippingAddressValidators(sameAsBilling: boolean): void {
    const shippingStreet = this.signupForm.get('shippingStreet');
    const shippingCity = this.signupForm.get('shippingCity');
    const shippingState = this.signupForm.get('shippingState');

    if (sameAsBilling) {
      shippingStreet.clearValidators();
      shippingCity.clearValidators();
      shippingState.clearValidators();
    } else {
      shippingStreet.setValidators(Validators.required);
      shippingCity.setValidators(Validators.required);
      shippingState.setValidators(Validators.required);
    }
  }

  signup(): void {
    const sameAsBilling = this.signupForm.get('sameAsBilling').value;
    const username = this.signupForm.get('username').value;
    const password = this.signupForm.get('password').value;
    const billingStreet = this.signupForm.get('billingStreet').value;
    const billingCity = this.signupForm.get('billingCity').value;
    const billingState = this.signupForm.get('billingState').value;
    const shippingStreet = this.signupForm.get('shippingStreet').value;
    const shippingCity = this.signupForm.get('shippingCity').value;
    const shippingState = this.signupForm.get('shippingState').value;

    const billingAddress = new Address(billingStreet, billingCity, billingState);

    let shippingAddress: Address = null;

    if (!sameAsBilling) {
      shippingAddress = new Address(shippingStreet, shippingCity, shippingState);
    }
    const newUserInfo = new NewUserInfo(username, password, billingAddress, shippingAddress);

    this.accountService.signup(newUserInfo).subscribe(
      data => {
        console.log('Navigating to Login');
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
        this.error = true;
      });
  }
}
