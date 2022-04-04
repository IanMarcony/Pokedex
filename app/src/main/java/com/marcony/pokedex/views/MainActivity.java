package com.marcony.pokedex.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.marcony.pokedex.R;
import com.marcony.pokedex.adapter.MyRecycleViewListAdapter;
import com.marcony.pokedex.services.api.PokeAPI;
import com.marcony.pokedex.services.api.models.ListPokemon;
import com.marcony.pokedex.services.api.services.PokeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView listPokemon;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPokemon = findViewById(R.id.listPokemon);
        layoutManager= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,
                false);
        listPokemon.setLayoutManager(layoutManager);

        getPokemon();
    }

    private void getPokemon(){
        PokeService pokeService = PokeAPI.getInstance().create(PokeService.class);

        pokeService.listPokemon(151).enqueue(new Callback<ListPokemon>() {
            @Override
            public void onResponse(Call<ListPokemon> call, Response<ListPokemon> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Ocorreu erro ao se comunicar com a PokeAPI",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: "+response);
                    return;
                }
                ListPokemon listPokemonAPI = response.body();
                MyRecycleViewListAdapter  adapter = new MyRecycleViewListAdapter(listPokemonAPI);
                listPokemon.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListPokemon> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}