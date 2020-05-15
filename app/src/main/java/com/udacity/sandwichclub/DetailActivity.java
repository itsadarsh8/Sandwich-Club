package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            Log.e("Detail Activity","intent null");
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            Log.e("Detail Activity","position error");
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;

        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            Log.e("Detail Activity","sandwichObject error");
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    private void populateUI(Sandwich sandwich) {

        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView placeOfOriginTv = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTitle = findViewById(R.id.alsoKnownTitle);
        TextView ingredientsTitle = findViewById(R.id.ingredientTitle);

        placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        descriptionTv.setText(sandwich.getDescription());
        String ingredients=sandwich.getIngredients().toString();
        String alsoKnownas=sandwich.getAlsoKnownAs().toString();
        ingredients=ingredients.replace("[","");
        ingredients=ingredients.replace("]","");

        Log.i("ingredients",ingredients);

        alsoKnownas=alsoKnownas.replace("[","");
        alsoKnownas=alsoKnownas.replace("]","");

        Log.i("alsoKnownAs",alsoKnownas);

        if(ingredients==""){
            ingredientsTitle.setVisibility(View.GONE);
        }
        else {
            ingredientsTv.setText(ingredients);
        }

        if(alsoKnownas==""){
            alsoKnownAsTitle.setVisibility(View.GONE);
        }
        else {
            alsoKnownAsTv.setText(alsoKnownas);
        }



    }
}
