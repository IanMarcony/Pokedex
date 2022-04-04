package com.marcony.pokedex.services.api.models;

import java.util.List;

public class ListPokemon {
    private List<ResultPokemon> results;

    public ListPokemon() {
    }

    public ListPokemon(List<ResultPokemon> results) {
        this.results = results;
    }

    public List<ResultPokemon> getResults() {
        return results;
    }

    public void setResults(List<ResultPokemon> results) {
        this.results = results;
    }
}
