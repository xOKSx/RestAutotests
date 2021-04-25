package ru.tunkoff.fintech.qa.restassured.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import ru.tunkoff.fintech.qa.models.pet.Category;
import ru.tunkoff.fintech.qa.models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Post {

    @Test
    @DisplayName("200")
    public void post_200() {
        Pet pet = new Pet();
        pet.setId(1000111);
        pet.setName("Trixy1000111");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .delete("https://petstore.swagger.io/v2/pet/{id}");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(pet)
                .post("https://petstore.swagger.io/v2/pet").then()
                .statusCode(200);

        Pet postedPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get("https://petstore.swagger.io/v2/pet/{id}").then()
                .statusCode(200)
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postedPet);
    }
}
