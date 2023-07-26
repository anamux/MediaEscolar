package com.media.escolar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.Snackbar;
import com.media.escolar.databinding.ActivityPrimeiroBimestreBinding;
import com.media.escolar.databinding.ContentPrimeiroBimestreBinding;

public class PrimeiroBimestreActivity extends AppCompatActivity {

    private ActivityPrimeiroBimestreBinding binding;
    private ContentPrimeiroBimestreBinding contentPrimeiroBimestreBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrimeiroBimestreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        contentPrimeiroBimestreBinding = ContentPrimeiroBimestreBinding.bind(binding.getRoot());



        contentPrimeiroBimestreBinding.btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    double notaProva = 0, notaTrabalho=0, media;
                    boolean dadosOk = true;
                    try {
                        if (contentPrimeiroBimestreBinding.editNotaProva.getText().toString().length() > 0){
                            notaProva = Double.parseDouble(contentPrimeiroBimestreBinding.editNotaProva.getText().toString());
                        }else{
                            contentPrimeiroBimestreBinding.editNotaProva.setError("*");
                            contentPrimeiroBimestreBinding.editNotaProva.requestFocus();
                            dadosOk=false;
                        }

                        if (contentPrimeiroBimestreBinding.editNotaTrabalho.getText().toString().length() > 0){
                            notaTrabalho = Double.parseDouble(contentPrimeiroBimestreBinding.editNotaTrabalho.getText().toString());
                        }else{
                            contentPrimeiroBimestreBinding.editNotaTrabalho.setError("*");
                            contentPrimeiroBimestreBinding.editNotaTrabalho.requestFocus();
                            dadosOk=false;
                        }

                        if(dadosOk){media = (notaProva + notaTrabalho) / 2;
                            contentPrimeiroBimestreBinding.txtMedia.setText(String.valueOf(media));

                            if (media >= 7) contentPrimeiroBimestreBinding.txtSituacao.setText("Aprovado");
                            else contentPrimeiroBimestreBinding.txtSituacao.setText("Reprovado");

                            salvarSharedPreferences();
                        }else{

                        }


                    } catch (Exception e) {
                        Toast.makeText(PrimeiroBimestreActivity.this, "Digite os dados solicitados", Toast.LENGTH_SHORT).show();
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

    private void salvarSharedPreferences(){
        SharedPreferences mediaEscolarPref = getSharedPreferences(
                MainActivity.SHARED_PREF, 0);

        SharedPreferences.Editor mediaEscolar = mediaEscolarPref.edit();

        mediaEscolar.putString("txtSituação1Bimestre", contentPrimeiroBimestreBinding.txtSituacao.getText().toString());
        mediaEscolar.putString("matéria1Bimestre", contentPrimeiroBimestreBinding.editMateria.getText().toString());
        mediaEscolar.putString("notaProva", contentPrimeiroBimestreBinding.editNotaProva.getText().toString());
        mediaEscolar.putString("notaTrabalho", contentPrimeiroBimestreBinding.editNotaTrabalho.getText().toString());
        mediaEscolar.putString("média1Bimestre", contentPrimeiroBimestreBinding.txtMedia.getText().toString());
        mediaEscolar.putBoolean("primeiroBimestre", true);
        mediaEscolar.commit();

    }
}