import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer';
import { CustomerService } from '../../../customer.service';
import { inject } from '@angular/core';

@Component({
  selector: 'app-customer-list',
  imports: [],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css',
})
export class CustomerList implements OnInit {

  private customerService = inject(CustomerService);
  customers = this.customerService.customers;

  ngOnInit(): void {
    this.customerService.getCustomers();
  }

}
