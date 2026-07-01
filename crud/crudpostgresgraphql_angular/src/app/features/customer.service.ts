import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from './customer/model/customer';

@Injectable({ providedIn: 'root' })
export class CustomerService {

    private httpClient: HttpClient = inject(HttpClient);
    private API_URL: string = 'http://localhost:8080/graphql';

    customers = signal<Customer[]>([]);

    getCustomers() {
        this.httpClient.post<any>(this.API_URL, {
            query: `
            query {
                getCustomers {
                    id
                    firstName
                    lastName
                    email
                    phoneNumber
                }
            }
            `
        }).subscribe((response) => {
            this.customers.set(response.data.getCustomers);
        });
    }
}
