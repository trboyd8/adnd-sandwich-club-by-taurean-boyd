package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
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

        final ImageView ingredientsIv = findViewById(R.id.image_iv);
        final ImageView errorImageIv = findViewById(R.id.error_image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv, new Callback() {
                    @Override
                    public void onSuccess() {
                        ingredientsIv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        errorImageIv.setVisibility(View.VISIBLE);
                    }
                });

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.detail_error_message), Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (sandwich.getAlsoKnownAs().size() > 0) {
            LinearLayout alsoKnownAsLL = findViewById(R.id.also_known_ll);
            alsoKnownAsLL.setVisibility(View.VISIBLE);

            TextView alsoKnownAsTV = findViewById(R.id.also_known_tv);
            alsoKnownAsTV.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        }

        if (!sandwich.getDescription().equals("")) {
            LinearLayout alsoKnownAsLL = findViewById(R.id.description_ll);
            alsoKnownAsLL.setVisibility(View.VISIBLE);

            TextView descriptionTV = findViewById(R.id.description_tv);
            descriptionTV.setText(sandwich.getDescription());
        }

        if (sandwich.getIngredients().size() > 0) {
            LinearLayout alsoKnownAsLL = findViewById(R.id.ingredients_ll);
            alsoKnownAsLL.setVisibility(View.VISIBLE);

            TextView ingredientsTV = findViewById(R.id.ingredients_tv);
            ingredientsTV.setText(TextUtils.join("\n", sandwich.getIngredients()));
        }

        if (!sandwich.getPlaceOfOrigin().equals("")) {
            LinearLayout alsoKnownAsLL = findViewById(R.id.origin_ll);
            alsoKnownAsLL.setVisibility(View.VISIBLE);

            TextView originTV = findViewById(R.id.origin_tv);
            originTV.setText(sandwich.getPlaceOfOrigin());
        }
    }
}
