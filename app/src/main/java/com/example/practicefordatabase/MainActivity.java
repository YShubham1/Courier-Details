package com.example.practicefordatabase;

/*Design an app for courier service, which take the following inputs: customer name, mobile number. From a drop-down box the customer can choose the city where the courier need to be sent. Allow the customer to select the courier is letter or parcel using RadioButton. On Click of submit button, save all the details in DB. Help the courier service manager to fetch the following details from DB and display it in next activity named "Result Activity".

i. Find out the customer details who are delivering the couriers to the given city.

ii. Find out the customer details who sent only PARCEL
Android code for this*/

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText n,ph;
    Spinner ct;
    RadioGroup rg;
    RadioButton rb;
    Button b1,city,letter;
    String[] cities={"Azamgarh","Mau","Balia","Ghazipur","Ghorakhpur","Varanasi","Lucknow","Faizabad"};
    String selectedCity,typeofC;
    CourierDetails db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        n=findViewById(R.id.name);
        ph=findViewById(R.id.ph);
        ct=findViewById(R.id.ct);
        rg=findViewById(R.id.rg);
        b1=findViewById(R.id.bt1);
        city=findViewById(R.id.city);
        letter=findViewById(R.id.letter);
        ArrayAdapter ad= new ArrayAdapter( MainActivity.this, android.R.layout.simple_spinner_dropdown_item,cities);
        db=new CourierDetails(this,"Courier",null,1);
        ct.setAdapter(ad);
        ct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na=n.getText().toString();
                String pho=ph.getText().toString();
                rb=findViewById(rg.getCheckedRadioButtonId());
                typeofC=rb.getText().toString();
                db.insertRecord(na,pho,selectedCity,typeofC);
                Toast.makeText(MainActivity.this, "Parcel Accepted", Toast.LENGTH_SHORT).show();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this, ResultActivity.class);
                String cityDetails=db.DisplayCity(selectedCity);
                intent1.putExtra("Details",cityDetails);
                startActivity(intent1);
            }
        });
        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this, ResultActivity.class);
                String letterDetails=db.DisplayLetter(typeofC);
                intent2.putExtra("Details",letterDetails);
                startActivity(intent2);
            }
        });

    }
}