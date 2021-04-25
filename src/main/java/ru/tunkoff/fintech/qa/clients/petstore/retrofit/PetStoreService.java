package ru.tunkoff.fintech.qa.clients.petstore.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class PetStoreService {
    private static PetStore petStore;

    private PetStoreService() {
    }

    public static PetStore getPetStore() {
        if (petStore == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://petstore.swagger.io/")
                    .addConverterFactory(JacksonConverterFactory.create()).build();
            petStore = retrofit.create(PetStore.class);
        }
        return petStore;
    }


}
