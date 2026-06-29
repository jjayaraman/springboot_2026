package com.jai.crudpostgresgraphql;

import com.jai.crudpostgresgraphql.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.graphql.test.autoconfigure.GraphQlTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.jai.crudpostgresgraphql.service.CustomerService;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@GraphQlTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void testCreateCustomerWithInvalidInput() {
        String document = """
            mutation {
                createCustomer(input: {
                    firstName: ""
                    lastName: ""
                    email: "invalid-email"
                }) {
                    id
                    firstName
                    lastName
                    email
                }
            }
        """;

        graphQlTester.document(document)
                .execute()
                .errors()
                .satisfy(errors -> {
                    assertFalse(errors.isEmpty(), "Should have validation errors");
                    var error = errors.get(0);
                    assertEquals("Validation failed", error.getMessage());
                    var extensions = error.getExtensions();
                    assertNotNull(extensions);
                    var valErrors = (Map<String, String>) extensions.get("validationErrors");
                    assertNotNull(valErrors);
                    assertEquals("First name cannot be empty", valErrors.get("firstName"));
                    assertEquals("Last name cannot be empty", valErrors.get("lastName"));
                    assertEquals("Please provide a valid email address", valErrors.get("email"));
                });
    }

    @Test
    void testCreateCustomerWithStrictInvalidInput() {
        String document = """
            mutation {
                createCustomer(input: {
                    firstName: "Jay7"
                    lastName: "J"
                    email: "hello@gmai"
                }) {
                    id
                    firstName
                    lastName
                    email
                }
            }
        """;

        graphQlTester.document(document)
                .execute()
                .errors()
                .satisfy(errors -> {
                    assertFalse(errors.isEmpty(), "Should have validation errors");
                    var error = errors.get(0);
                    assertEquals("Validation failed", error.getMessage());
                    var extensions = error.getExtensions();
                    assertNotNull(extensions);
                    var valErrors = (Map<String, String>) extensions.get("validationErrors");
                    assertNotNull(valErrors);
                    assertEquals("First name must contain only letters", valErrors.get("firstName"));
                    assertEquals("Last name must be between 2 and 50 characters", valErrors.get("lastName"));
                    assertEquals("Please provide a valid email address", valErrors.get("email"));
                });
    }
}
