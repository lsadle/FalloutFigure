import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AccountService} from '../../Services/account.service';
import {Address} from '../../ApiModels/address';
import {NewUserInfo} from '../../ApiModels/new-user-info';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;
  error = false;

  constructor(private builder: FormBuilder, private accountService: AccountService) { }

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
      sameAsBilling: [true]
    });

    this.signupForm.get('sameAsBilling').valueChanges.subscribe(
      value => this.setShippingAddressValidators(value)
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

    try {
      this.accountService.signup(newUserInfo);
    } catch (e) {
      console.log(e);
      this.error = true;
    }
  }
}
