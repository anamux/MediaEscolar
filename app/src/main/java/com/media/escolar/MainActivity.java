package com.media.escolar;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.media.escolar.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        binding.contentMain.btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    double notaProva = 0, notaTrabalho=0, media;
                    boolean dadosOk = true;
                    try {
                        if (binding.contentMain.editNotaProva.getText().toString().length() > 0){
                            notaProva = Double.parseDouble(binding.contentMain.editNotaProva.getText().toString());
                        }else{
                            binding.contentMain.editNotaProva.setError("*");
                            binding.contentMain.editNotaProva.requestFocus();
                            dadosOk=false;
                        }

                        if (binding.contentMain.editNotaTrabalho.getText().toString().length() > 0){
                            notaTrabalho = Double.parseDouble(binding.contentMain.editNotaTrabalho.getText().toString());
                        }else{
                            binding.contentMain.editNotaTrabalho.setError("*");
                            binding.contentMain.editNotaTrabalho.requestFocus();
                            dadosOk=false;
                        }

                        if(dadosOk){media = (notaProva + notaTrabalho) / 2;
                            binding.contentMain.txtMedia.setText(String.valueOf(media));

                            if (media >= 7) binding.contentMain.txtSituacao.setText("Aprovado");
                            else binding.contentMain.txtSituacao.setText("Reprovado");

                        }else{

                        }


                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Digite os dados solicitados", Toast.LENGTH_SHORT).show();
                    }
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}