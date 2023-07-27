package com.example.foodrecipeapp;

import android.content.Context;

import com.example.foodrecipeapp.Listener.InstructionResponseListner;
import com.example.foodrecipeapp.Listener.RandomRecipeResponseListener;
import com.example.foodrecipeapp.Listener.RecipeDetailsListener;
import com.example.foodrecipeapp.Listener.SimilarRecipesListener;
import com.example.foodrecipeapp.Models.InstructionResponse;
import com.example.foodrecipeapp.Models.RamdomRecipeApiResponse;
import com.example.foodrecipeapp.Models.RecipeDetailsResponse;
import com.example.foodrecipeapp.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManger {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManger(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener,List<String>tags){
         Randomrecipecall randomrecipecall = retrofit.create(Randomrecipecall.class);
         Call<RamdomRecipeApiResponse> call = randomrecipecall.callRandomrecipe(context.getString(R.string.api_key),"10",tags);
         call.enqueue(new Callback<RamdomRecipeApiResponse>() {
             @Override
             public void onResponse(Call<RamdomRecipeApiResponse> call, Response<RamdomRecipeApiResponse> response) {
                 if(!response.isSuccessful()){
                     listener.didError(response.message());
                     return;
                 }
                 listener.didFatch(response.body(), response.message());
             }

             @Override
             public void onFailure(Call<RamdomRecipeApiResponse> call, Throwable t) {
                 listener.didError(t.getMessage());

             }
         });
    }

      public void getRecipeDetails(RecipeDetailsListener listener,int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFatch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage()  );
            }
        });
      }
      public void getSimilarRecipe(SimilarRecipesListener listener,int id){
        callSimilarRecie callSimilarRecie = retrofit.create(RequestManger.callSimilarRecie.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecie.callSimilarRecipe(id,"4",context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                  listener.didFatch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
      }

      public void getInstruction(InstructionResponseListner listner,int id){
         callInstruction callInstruction = retrofit.create(callInstruction.class);
         Call<List<InstructionResponse>> call = callInstruction.callinstruction(id,context.getString(R.string.api_key));
         call.enqueue(new Callback<List<InstructionResponse>>() {
             @Override
             public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                 if(!response.isSuccessful()){
                     listner.didError(response.message());
                     return;
                 }
                 listner.didFatch(response.body(),response.message());
             }

             @Override
             public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
                   listner.didError(t.getMessage());
             }
         });
      }

    private interface Randomrecipecall{
        @GET("recipes/random")
        Call<RamdomRecipeApiResponse> callRandomrecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface callSimilarRecie{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }
    private interface callInstruction{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionResponse>> callinstruction(
               @Path("id")   int id,
               @Query("apiKey") String apiKey
        );
    }

}
