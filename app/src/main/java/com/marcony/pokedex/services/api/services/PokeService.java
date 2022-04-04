package com.marcony.pokedex.services.api.services;

import com.marcony.pokedex.services.api.models.ListPokemon;
import com.marcony.pokedex.services.api.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeService {

    @GET("/api/v2/pokemon")
    Call<ListPokemon> listPokemon(@Query("limit") int limit);

    @GET("/api/v2/pokemon/{pokemon}")
    Call<Pokemon> getPokemonDetails(@Path("pokemon") String pokemonName);


}
