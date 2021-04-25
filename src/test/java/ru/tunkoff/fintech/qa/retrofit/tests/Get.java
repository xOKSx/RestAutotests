package ru.tunkoff.fintech.qa.retrofit.tests;

import org.junit.jupiter.api.*;
import ru.tunkoff.fintech.qa.clients.petstore.retrofit.PetStore;
import ru.tunkoff.fintech.qa.clients.petstore.retrofit.PetStoreService;
import ru.tunkoff.fintech.qa.models.pet.Category;
import ru.tunkoff.fintech.qa.models.pet.Pet;

import java.io.IOException;

public class Get {

    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = PetStoreService.getPetStore();
    }

    @Test
    @DisplayName("200")
    public void get_200() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000211);
        pet.setName("Trixy");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        petStore.deletePetById(id).execute();
        petStore.createPet(pet).execute();
        Pet receivedPet = petStore.getPetById(id).execute().body();
        Assertions.assertEquals(pet, receivedPet);
    }

    @Test
    @DisplayName("404")
    public void get_404() throws IOException {
        int id = 2000221;

        petStore.deletePetById(id).execute();
        int responseCode = petStore.getPetById(id).execute().code();
        Assertions.assertEquals(404, responseCode);
    }
}
