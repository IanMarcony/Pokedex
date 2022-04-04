package com.marcony.pokedex.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.marcony.pokedex.R;
import com.marcony.pokedex.services.api.PokeAPI;
import com.marcony.pokedex.services.api.models.ListPokemon;
import com.marcony.pokedex.services.api.models.Pokemon;
import com.marcony.pokedex.services.api.services.PokeService;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyRecycleViewListAdapter extends RecyclerView.Adapter<MyRecycleViewListAdapter.ViewHolder> {
    private static final String TAG = "MyRVListAdapter" ;
    private ListPokemon listPokemons;
    private static final String URL_IMAGE="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/";
    private static final String EXTENSION_IMAGE=".png";

    public MyRecycleViewListAdapter(ListPokemon listPokemons) {
        this.listPokemons = listPokemons;
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pokemonNameView;
        private ImageView pokemonSpriteView;
        private CardView pokemonCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonNameView = itemView.findViewById(R.id.namePokemon);
            pokemonSpriteView = itemView.findViewById(R.id.spritePokemon);
            pokemonCardView = itemView.findViewById(R.id.cardPokemon);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pokemonNameView.setText(listPokemons.getResults().get(position).getName().toUpperCase());
        int idPokemon = position+1;
        Picasso.get()
                .load(URL_IMAGE+idPokemon+EXTENSION_IMAGE)
                .into(holder.pokemonSpriteView);
        holder.pokemonCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PokeService pokeService = PokeAPI.getInstance().create(PokeService.class);

                pokeService.getPokemonDetails(listPokemons.getResults().get(idPokemon-1).getName()).enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        if(!response.isSuccessful()){
                            Log.d(TAG, "onResponse: "+response);
                            return;
                        }

                        Log.d(TAG, "onResponse: "+response.body().toString());

                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPokemons.getResults().size();
    }
}
