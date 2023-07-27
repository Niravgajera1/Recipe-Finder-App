package com.example.foodrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodrecipeapp.Adapters.RandomRecipesAdapter;
import com.example.foodrecipeapp.Listener.RandomRecipeResponseListener;
import com.example.foodrecipeapp.Listener.RecipeClickedListener;
import com.example.foodrecipeapp.Models.RamdomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManger manger;
    RandomRecipesAdapter randomRecipesAdapter;
    RecyclerView recyclerView;
    Spinner spinner;

    List<String> tags = new ArrayList<>();
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

         searchView=findViewById(R.id.searchview_home);
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 tags.clear();
                 tags.add(query);
                 manger.getRandomRecipes(randomRecipeResponseListener,tags);
                 return true;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }
         });
        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(
               this,
               R.array.tags,
               R.layout.spinner_text
        );

        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(SpinnerSelectedlistener);

          manger = new RequestManger(this);
//        manger.getRandomRecipes(randomRecipeResponseListener);
//        dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFatch(RamdomRecipeApiResponse response, String message) {
            dialog.dismiss();   
           recyclerView=findViewById(R.id.recycler_random);
           recyclerView.setHasFixedSize(true);
           recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
           randomRecipesAdapter=new RandomRecipesAdapter(MainActivity.this,response.recipes,recipeClickedListener);
           recyclerView.setAdapter(randomRecipesAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
        }
    };

    private  final AdapterView.OnItemSelectedListener SpinnerSelectedlistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              tags.clear();
              tags.add(parent.getSelectedItem().toString());
              manger.getRandomRecipes(randomRecipeResponseListener,tags);
              dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private final RecipeClickedListener recipeClickedListener = new RecipeClickedListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(MainActivity.this,RecipeDetailsActivity.class)
                    .putExtra("id",id));
        }
    };
}