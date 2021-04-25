package ru.tunkoff.fintech.qa.retrofit.tests;

import org.junit.jupiter.api.BeforeAll;
import ru.tunkoff.fintech.qa.clients.petstore.retrofit.PetStore;
import ru.tunkoff.fintech.qa.clients.petstore.retrofit.PetStoreService;
import ru.tunkoff.fintech.qa.models.pet.Category;
import ru.tunkoff.fintech.qa.models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Post {

    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = PetStoreService.getPetStore();
    }

    @Test
    @DisplayName("200")
    public void post_200() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000111);
        pet.setName("Trixy2000111");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        petStore.deletePetById(id).execute();
        petStore.createPet(pet).execute();
        Pet postedPet = petStore.getPetById(id).execute().body();
        Assertions.assertEquals(pet, postedPet);
    }
}
