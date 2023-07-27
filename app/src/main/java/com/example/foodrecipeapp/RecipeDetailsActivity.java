package com.example.foodrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipeapp.Adapters.InstructionAdapter;
import com.example.foodrecipeapp.Adapters.SimilarRecipeAdapter;
import com.example.foodrecipeapp.Adapters.ingredientAdapter;
import com.example.foodrecipeapp.Listener.InstructionResponseListner;
import com.example.foodrecipeapp.Listener.RecipeClickedListener;
import com.example.foodrecipeapp.Listener.RecipeDetailsListener;
import com.example.foodrecipeapp.Listener.SimilarRecipesListener;
import com.example.foodrecipeapp.Models.InstructionResponse;
import com.example.foodrecipeapp.Models.RecipeDetailsResponse;
import com.example.foodrecipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textview_meal_name,textview_meal_sourse,textview_meal_summary;
    RecyclerView recyclear_meal,recylcer_meal_similar,recycler_meal_instruction;
    ImageView imageview_meal_image;
    ProgressDialog dialog;
    RequestManger manger;
    ingredientAdapter  ingredientAdapter;

    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionAdapter instructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manger = new RequestManger(this);
        manger.getRecipeDetails(recipeDetailsListener,id);
        manger.getSimilarRecipe(similarRecipesListener,id);
        manger.getInstruction(instructionResponseListner,id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();

    }

    private void findViews() {
        recycler_meal_instruction = findViewById(R.id.recycler_meal_instruction);
        recylcer_meal_similar= findViewById(R.id.recylcer_meal_similar);
        textview_meal_name = findViewById(R.id.textview_meal_name);
        textview_meal_sourse = findViewById(R.id.textview_meal_sourse);
        textview_meal_summary = findViewById(R.id.textview_meal_summary);
        imageview_meal_image = findViewById(R.id.imageview_meal_image);
        recyclear_meal = findViewById(R.id.recyclear_meal);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFatch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textview_meal_name.setText(response.title);
            textview_meal_sourse.setText(response.sourceName);
            textview_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageview_meal_image);

            recyclear_meal.setHasFixedSize(true);
            recyclear_meal.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));

            ingredientAdapter = new ingredientAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recyclear_meal.setAdapter(ingredientAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFatch(List<SimilarRecipeResponse> response, String message) {
           recylcer_meal_similar.setHasFixedSize(true);
           recylcer_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
           similarRecipeAdapter = new SimilarRecipeAdapter((Context) RecipeDetailsActivity.this, (List<SimilarRecipeResponse>) response,recipeClickedListener);
           recylcer_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
           Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_LONG).show();
        }
    };

    private final RecipeClickedListener recipeClickedListener = new RecipeClickedListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailsActivity.this,RecipeDetailsActivity.class)
                    .putExtra("id",id));
        }
    };
    private final InstructionResponseListner instructionResponseListner = new InstructionResponseListner() {
        @Override
        public void didFatch(List<InstructionResponse> response, String message) {
              recycler_meal_instruction.setHasFixedSize(true);
              recycler_meal_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
              instructionAdapter = new InstructionAdapter(RecipeDetailsActivity.this,response);
              recycler_meal_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}