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

public class Put {
    private static PetStore petStore;

    @BeforeAll
    public static void beforeAll() {
        petStore = PetStoreService.getPetStore();
    }

    @Test
    @DisplayName("New_200")
    public void putNew_200() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000311);
        pet.setName("Trixy2000311");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("sold");
        int id = pet.getId();

        petStore.deletePetById(id).execute();
        petStore.updatePet(pet).execute();
        Pet updatedPet = petStore.getPetById(id).execute().body();
        Assertions.assertEquals(pet, updatedPet);
    }

    @Test
    @DisplayName("Existing_200")
    public void putExisting_200() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000321);
        pet.setName("Trixy2000321");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");
        int id = pet.getId();

        petStore.deletePetById(id).execute();
        petStore.createPet(pet).execute();

        pet.setStatus("sold");

        petStore.updatePet(pet).execute();
        Pet updatedPet = petStore.getPetById(id).execute().body();
        Assertions.assertEquals(pet, updatedPet);
    }

    @Test
    @DisplayName("404")
    public void put_404() throws IOException {
        Pet pet = new Pet();
        pet.setId(2000331);
        pet.setName("Trixy2000331");
        Category category = new Category();
        category.setName("Cat");
        pet.setCategory(category);
        pet.setStatus("available");

        int responseCode = petStore.updatePetWithWrongUrl(pet).execute().code();
        Assertions.assertEquals(404, responseCode);
    }
}
