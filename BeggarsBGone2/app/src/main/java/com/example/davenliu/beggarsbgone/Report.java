package com.example.davenliu.beggarsbgone;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.DatabaseMetaData;

public class Report extends AppCompatActivity {

    /*public void submit(View view){
        new CountDownTimer(3000,1000){
            /*ImageView thank = (ImageView) findViewById(R.id.Thanks);
            thank.animate().alpha(1f).setDuration(1);
            public void onTick(long millisUntilFinished){
            }

            public void onFinish(){
                Intent intent = new Intent(Report.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }*/

    InfoData peopleDB;

    EditText stName, stAddress;
    int stClothes, stWater, stToiletries, stShoes, stFood;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        peopleDB = new InfoData(this);
        stName = (EditText) findViewById(R.id.name);
        stAddress = (EditText) findViewById(R.id.address);

        checkBox = (CheckBox)findViewById(R.id.clothes);
        if(checkBox.isChecked()){
            stClothes = 1;
        }else{
            stClothes = 0;
        }
        checkBox = (CheckBox)findViewById(R.id.water);
        if(checkBox.isChecked()){
            stWater = 1;
        }else{
            stWater = 0;
        }

        checkBox = (CheckBox)findViewById(R.id.toiletries);
        if(checkBox.isChecked()){
            stToiletries = 1;
        }else{
            stToiletries = 0;
        }

        checkBox = (CheckBox)findViewById(R.id.shoes);
        if(checkBox.isChecked()){
            stShoes = 1;
        }else{
            stShoes = 0;
        }

        checkBox = (CheckBox)findViewById(R.id.food);
        if(checkBox.isChecked()){
            stFood = 1;
        }else{
            stFood = 0;
        }
    }

    public void AddData(View view){
        String etName = stName.getText().toString();
        String etAddress = stAddress.getText().toString();
        boolean insertData = peopleDB.addData(etName, etAddress, stClothes, stWater, stToiletries, stShoes, stFood);

        if(insertData == true){
            Toast.makeText(Report.this, "Data Successfully Inserted! ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Report.this, "Data insert fail ??????????" , Toast.LENGTH_LONG).show();
        }
        new CountDownTimer(1000,1000){
            public void onTick(long millisUntilFinished){
            }

            public void onFinish(){
                Intent intent = new Intent(Report.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    public void ViewData(View view){
        Cursor data = peopleDB.showData();
        if(data.getCount() != 0){
            StringBuffer buffer= new StringBuffer();
            while(data.moveToNext()){
                buffer.append("Name: " + data.getString(0) + "\n");
                buffer.append("Address: " + data.getString(1) + "\n");
                buffer.append("Clothes: " + data.getInt(3)+ "\n");
            }
            display("data: ", buffer.toString());
        }else{
            display("lol", "no data");
        }
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
