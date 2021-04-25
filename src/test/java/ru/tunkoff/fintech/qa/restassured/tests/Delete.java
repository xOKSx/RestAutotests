package ru.tunkoff.fintech.qa.restassured.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import ru.tunkoff.fintech.qa.models.pet.Category;
import ru.tunkoff.fintech.qa.models.pet.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Delete {

    @Test
    @DisplayName("200")
    public void delete_200() {
        Pet pet = new Pet();
        pet.setId(1000411);
        pet.setName("Trixy1000411");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .post("https://petstore.swagger.io/v2/pet").then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .pathParam("id", id)
                .delete("https://petstore.swagger.io/v2/pet/{id}").then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get("https://petstore.swagger.io/v2/pet/{id}").then()
                .statusCode(404);
    }

    @Test
    @DisplayName("404")
    public void delete_404() {
        Pet pet = new Pet();
        pet.setId(1000421);
        pet.setName("Trixy1000421");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .post("https://petstore.swagger.io/v2/pet").then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .delete("https://petstore.swagger.io/v2/pet/{id}").then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .pathParam("id", id)
                .delete("https://petstore.swagger.io/v2/pet/{id}").then()
                .statusCode(404);
    }
}
