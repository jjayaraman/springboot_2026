import { Component } from '@angular/core';
import { CustomerForm } from './components/customer-form/customer-form.component';
import { CustomerList } from './components/customer-list/customer-list.component';

@Component({
  selector: 'app-customer',
  imports: [CustomerForm, CustomerList],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css',
})
export class Customer { }
