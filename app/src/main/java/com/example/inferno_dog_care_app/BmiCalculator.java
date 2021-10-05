package com.example.inferno_dog_care_app;

//IT20175498
//BMI Calculator for calculate dogs BMI value

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class BmiCalculator extends AppCompatActivity{

    //views

    private EditText bmi_edit1;
    private EditText bmi_edit3;
    private EditText bmi_edit4;
    private Button bmi_btn1;
    private TextView bmi_ans;
    private TextView display_name_txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator);


        //Initialize views with its xml

        bmi_edit3 =  findViewById(R.id.bmi_edit3);
        bmi_edit4 = findViewById(R.id.bmi_edit4);
        bmi_btn1 =findViewById(R.id.bmi_btn1);
        bmi_ans=findViewById(R.id.bmi_ans);
        bmi_edit1 = findViewById(R.id.bmi_edit1);
        display_name_txt=findViewById(R.id.display_name_txt);
        bmi_btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(bmi_edit3.getText().toString().trim()) && !TextUtils.isEmpty(bmi_edit4.getText().toString().trim())){
                    calculateBmi();
                }else{
                    Toast.makeText(getApplicationContext(),"Enter weight and height.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

        //Calculation
        private void calculateBmi() {

            float height = Float.parseFloat(bmi_edit4.getText().toString().trim());
            float weight = Float.parseFloat(bmi_edit3.getText().toString().trim());

            //calculation part
            float bmi = (float) ((weight * 2.205 * 703)/((height/2.54)*(height/2.54)));

            DecimalFormat df = new DecimalFormat("0.00");

            Editable name = (bmi_edit1.getText());
            bmi_ans.setText(""+df.format(bmi));
            display_name_txt.setText(name+"'s BMI value is :");
    }
}

