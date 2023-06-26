package com.media.escolar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;



import com.media.escolar.databinding.ActivityMainBinding;
import com.media.escolar.databinding.ContentMainBinding;


import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREF = "sharedPreferences";
    private ActivityMainBinding binding;
    private ContentMainBinding contentMainBinding;

    String situacaoAtualPrimeiroBimestre, materia1Bimestre, situacaoAtualSegundoBimestre, materia2Bimestre,
            situacaoAtualTerceiroBimestre, materia3Bimestre, situacaoAtualQuartoBimestre, materia4Bimestre;
    Boolean primeiroBimestre, segundoBimestre, terceiroBimestre, quartoBimestre;
    Double media1Bimestre, media2Bimestre, media3Bimestre, media4Bimestre, mediaFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contentMainBinding = ContentMainBinding.bind(binding.getRoot());


        setSupportActionBar(binding.toolbar);

        lerSharedPreferences();


        contentMainBinding.btnPrimeiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PrimeiroBimestreActivity.class);
                startActivity(i);
            }
        });

        contentMainBinding.btnSegundoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SegundoBimestreActivity.class);
                startActivity(i);
            }
        });

        contentMainBinding.btnTerceiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TerceiroBimestreActivity.class);
                startActivity(i);
            }
        });

        contentMainBinding.btnQuartoBimestre.setOnClickListener(new View.OnClickListener() {
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
                clearSharedPreferences();
            }
        });
        visualizarResultado();
    }

    private void visualizarResultado() {
        String mensagemFinal = "";
        if (primeiroBimestre){
            contentMainBinding.btnPrimeiroBimestre.setText(
                    materia1Bimestre+getString(R.string.primeiro_bimestre)+ situacaoAtualPrimeiroBimestre+
                            getString(R.string.media)+": "+media1Bimestre);
            contentMainBinding.btnPrimeiroBimestre.setEnabled(false);
            contentMainBinding.btnSegundoBimestre.setEnabled(primeiroBimestre);
        }
        if (segundoBimestre){
            contentMainBinding.btnSegundoBimestre.setText(materia2Bimestre+ " 2º Bimestre"+
                    situacaoAtualSegundoBimestre+" Média: "+media2Bimestre);
            contentMainBinding.btnSegundoBimestre.setEnabled(false);
            contentMainBinding.btnTerceiroBimestre.setEnabled(segundoBimestre);
        }
        if (terceiroBimestre){
            contentMainBinding.btnTerceiroBimestre.setText(
                    materia3Bimestre+" 3º Bimestre "+ situacaoAtualTerceiroBimestre+
                            " Média: "+media3Bimestre);
            contentMainBinding.btnTerceiroBimestre.setEnabled(false);
            contentMainBinding.btnQuartoBimestre.setEnabled(terceiroBimestre);
        }
        if (quartoBimestre){
            contentMainBinding.btnQuartoBimestre.setText(materia4Bimestre+ " 4º Bimestre"+
                    situacaoAtualQuartoBimestre+" Média: "+media4Bimestre);
            contentMainBinding.btnQuartoBimestre.setEnabled(false);
            contentMainBinding.btnResultado.setEnabled(quartoBimestre);

            mediaFinal = ((media1Bimestre+media2Bimestre+media3Bimestre+media4Bimestre)/4);

            if ((media1Bimestre>=7) && (media2Bimestre>=7) && (media3Bimestre>=7)&&(media4Bimestre>=7)){
                mensagemFinal = mediaFinal>=7?
                        "Aprovado com Média Final"+ mediaFinal:
                        "Reprovado com Média Final"+ mediaFinal;

            }else {
                mensagemFinal = "Reprovado por matéria com Média Final"+ mediaFinal;
            }
            contentMainBinding.btnResultado.setText(mensagemFinal);
        }
    }



    private void lerSharedPreferences() {

        SharedPreferences mediaEscolarPref = getSharedPreferences(SHARED_PREF, 0);

        situacaoAtualPrimeiroBimestre = mediaEscolarPref.getString("txtSituação1Bimestre", "");
        materia1Bimestre = mediaEscolarPref.getString("matéria1Bimestre", "");
        media1Bimestre = Double.parseDouble(mediaEscolarPref.getString("média1Bimestre", "0.0"));
        primeiroBimestre = mediaEscolarPref.getBoolean("primeiroBimestre", false);

        situacaoAtualSegundoBimestre = mediaEscolarPref.getString("txtSituação2Bimestre", "");
        materia2Bimestre = mediaEscolarPref.getString("matéria2Bimestre", "");
        media2Bimestre = Double.parseDouble(mediaEscolarPref.getString("média2Bimestre", "0.0"));
        segundoBimestre = mediaEscolarPref.getBoolean("segundoBimestre", false);

        situacaoAtualTerceiroBimestre = mediaEscolarPref.getString("txtSituação3Bimestre", "");
        materia3Bimestre = mediaEscolarPref.getString("matéria3Bimestre", "");
        media3Bimestre = Double.parseDouble(mediaEscolarPref.getString("média3Bimestre", "0.0"));
        terceiroBimestre = mediaEscolarPref.getBoolean("terceiroBimestre", false);

        situacaoAtualQuartoBimestre = mediaEscolarPref.getString("txtSituação4Bimestre", "");
        materia4Bimestre = mediaEscolarPref.getString("matéria4Bimestre", "");
        media4Bimestre = Double.parseDouble(mediaEscolarPref.getString("média4Bimestre", "0.0"));
        quartoBimestre = mediaEscolarPref.getBoolean("quartoBimestre", false);

    }
    private void clearSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        clearMenu();

    }

    private void clearMenu() {
        contentMainBinding.btnResultado.setEnabled(false);
        contentMainBinding.btnQuartoBimestre.setEnabled(false);
        contentMainBinding.btnTerceiroBimestre.setEnabled(false);
        contentMainBinding.btnSegundoBimestre.setEnabled(false);
        contentMainBinding.btnPrimeiroBimestre.setEnabled(true);

        contentMainBinding.btnResultado.setText(R.string.resultado_final);
        contentMainBinding.btnPrimeiroBimestre.setText(R.string.primeiro_bimestre);
        contentMainBinding.btnSegundoBimestre.setText(R.string.segundo_bimestre);
        contentMainBinding.btnTerceiroBimestre.setText(R.string.terceiro_bimestre);
        contentMainBinding.btnQuartoBimestre.setText(R.string.quarto_bimestre);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("media escolar", "*****************onResume*****************");
        lerSharedPreferences();
        visualizarResultado();
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