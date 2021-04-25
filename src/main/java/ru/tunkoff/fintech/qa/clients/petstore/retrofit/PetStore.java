package ru.tunkoff.fintech.qa.clients.petstore.retrofit;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.tunkoff.fintech.qa.models.pet.MyResponse;
import ru.tunkoff.fintech.qa.models.pet.Pet;
import retrofit2.Call;

public interface PetStore {
    @POST("/v2/pet")
    Call<Pet> createPet(@Body Pet pet);

    @GET("/v2/pet/{id}")
    Call<Pet> getPetById(@Path("id") int id);

    @PUT("/v2/pet")
    Call<Pet> updatePet(@Body Pet pet);

    @DELETE("/v2/pet/{id}")
    Call<MyResponse> deletePetById(@Path("id") int id);

    @PUT("/v2/wrong")
    Call<Pet> updatePetWithWrongUrl(@Body Pet pet);


}
