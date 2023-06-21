package com.media.escolar;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.media.escolar.databinding.ActivityMainBinding;
import com.media.escolar.databinding.ContentMainBinding;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.function.ToDoubleBiFunction;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ContentMainBinding contentMainBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contentMainBinding = ContentMainBinding.bind(binding.getRoot());


        setSupportActionBar(binding.toolbar);



        Button primeiroBimestre = contentMainBinding.btnPrimeiroBimestre;

        primeiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PrimeiroBimestreActivity.class);
                startActivity(i);
            }
        });

        Button segundoBimestre = contentMainBinding.btnSegundoBimestre;
        segundoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SegundoBimestreActivity.class);
                startActivity(i);
            }
        });

        Button terceiroBimestre = contentMainBinding.btnTerceiroBimestre;

        terceiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TerceiroBimestreActivity.class);
                startActivity(i);
            }
        });
        Button quartoBimestre = contentMainBinding.btnQuartoBimestre;
        quartoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QuartoBimestreActivity.class);
                startActivity(i);
            }
        });
        //fab=floating action button
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App Média Escolar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}