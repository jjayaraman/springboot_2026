package com.jai.crudpostgresgraphql.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.jai.crudpostgresgraphql.entity.Customer}
 */
public record CustomerOutputDto(String id, String firstName, String lastName, String email, String phoneNumber,
                                String addressLine1, String addressLine2, String city, String state, String country,
                                String postCode, Date createdDate, Date updatedDate) implements Serializable {
}
