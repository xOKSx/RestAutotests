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

public class Delete {

    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = PetStoreService.getPetStore();
    }

    @Test
    @DisplayName("200")
    public void delete_200() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000411);
        pet.setName("Trixy2000411");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        petStore.createPet(pet).execute();
        petStore.deletePetById(id).execute();
        int responseCode = petStore.getPetById(id).execute().code();
        Assertions.assertEquals(404, responseCode);
    }

    @Test
    @DisplayName("404")
    public void delete_404() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000421);
        pet.setName("Trixy2000421");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        petStore.createPet(pet).execute();
        petStore.deletePetById(id).execute();
        int responseCode = petStore.deletePetById(id).execute().code();
        Assertions.assertEquals(404, responseCode);
    }
}
