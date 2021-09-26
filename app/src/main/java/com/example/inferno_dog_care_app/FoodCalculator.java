package com.example.inferno_dog_care_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class FoodCalculator extends AppCompatActivity{
    private EditText txt_food_calories4;
    private EditText txt_food_calories5;
    private EditText txt_food_calories6;
    private EditText txt_food_calories7;
    private EditText txt_food_calories8;
    private EditText txt_food_calories9;
    private Button btn_food_calories;
    private TextView txt_food_calories_colories2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_calories);

        txt_food_calories4 =  findViewById(R.id.txt_food_calories4);
        txt_food_calories5 = findViewById(R.id.txt_food_calories5);
        txt_food_calories6 = findViewById(R.id.txt_food_calories6);
        txt_food_calories7 = findViewById(R.id.txt_food_calories7);
        txt_food_calories8 = findViewById(R.id.txt_food_calories8);
        txt_food_calories9 = findViewById(R.id.txt_food_calories9);
        btn_food_calories =findViewById(R.id.btn_food_calories);
        txt_food_calories_colories2=findViewById(R.id.txt_food_calories_colories2);
        btn_food_calories.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calculateCalorie();
            }
        });
    }
    private void calculateCalorie() {
        float meat = Float.parseFloat(txt_food_calories4.getText().toString().trim());
        float rice = Float.parseFloat(txt_food_calories5.getText().toString().trim());
        float fish = Float.parseFloat(txt_food_calories6.getText().toString().trim());
        float egg = Float.parseFloat(txt_food_calories7.getText().toString().trim());
        float milk = Float.parseFloat(txt_food_calories8.getText().toString().trim());
        float yogurt = Float.parseFloat(txt_food_calories9.getText().toString().trim());
        float calorie = (float) ((meat * 2)+(rice * 1.3)+(fish * 2)+(egg * 155)+(milk * 0.4)+(yogurt * 59));

        DecimalFormat df = new DecimalFormat("0.00");

        txt_food_calories_colories2.setText(""+df.format(calorie));
    }
}
