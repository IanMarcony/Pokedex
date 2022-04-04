package com.marcony.pokedex.services.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeAPI {
    private static Retrofit service;
    private static final String URL_API="https://pokeapi.co";

    public static synchronized Retrofit getInstance(){
        if(service==null){
            service = new Retrofit.Builder()
                    .baseUrl(URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return service;
    }
}
