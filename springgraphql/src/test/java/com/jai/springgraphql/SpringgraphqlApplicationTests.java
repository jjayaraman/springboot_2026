package com.jai.springgraphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.graphql.test.autoconfigure.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
class SpringgraphqlApplicationTests {

	@Autowired
	private GraphQlTester graphQlTester;

	@Test
	void contextLoads() {
	}

	@Test
	void testEmployeesWithDepartment() {
		String document = """
			query {
				employees {
					id
					firstName
					lastName
					email
					department {
						id
						name
					}
				}
			}
		""";

		graphQlTester.document(document)
				.execute()
				.path("employees")
				.entityList(Object.class)
				.hasSize(2);
	}

}
