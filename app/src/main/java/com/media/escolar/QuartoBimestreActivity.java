package com.media.escolar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.snackbar.Snackbar;
import com.media.escolar.databinding.ActivityQuartoBimestreBinding;
import com.media.escolar.databinding.ActivityTerceiroBimestreBinding;
import com.media.escolar.databinding.ContentQuartoBimestreBinding;
import com.media.escolar.databinding.ContentTerceiroBimestreBinding;

public class QuartoBimestreActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityQuartoBimestreBinding binding;
    private ContentQuartoBimestreBinding contentBimestreBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQuartoBimestreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        contentBimestreBinding = ContentQuartoBimestreBinding.bind(binding.getRoot());


        contentBimestreBinding.btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    double notaProva = 0, notaTrabalho=0, media;
                    boolean dadosOk = true;
                    try {
                        if (contentBimestreBinding.editNotaProva.getText().toString().length() > 0){
                            notaProva = Double.parseDouble(contentBimestreBinding.editNotaProva.getText().toString());
                        }else{
                            contentBimestreBinding.editNotaProva.setError("*");
                            contentBimestreBinding.editNotaProva.requestFocus();
                            dadosOk=false;
                        }

                        if (contentBimestreBinding.editNotaTrabalho.getText().toString().length() > 0){
                            notaTrabalho = Double.parseDouble(contentBimestreBinding.editNotaTrabalho.getText().toString());
                        }else{
                            contentBimestreBinding.editNotaTrabalho.setError("*");
                            contentBimestreBinding.editNotaTrabalho.requestFocus();
                            dadosOk=false;
                        }

                        if(dadosOk){media = (notaProva + notaTrabalho) / 2;
                            contentBimestreBinding.txtMedia.setText(String.valueOf(media));

                            if (media >= 7) contentBimestreBinding.txtSituacao.setText("Aprovado");
                            else contentBimestreBinding.txtSituacao.setText("Reprovado");

                        }else{

                        }


                    } catch (Exception e) {
                        Toast.makeText(QuartoBimestreActivity.this, "Digite os dados solicitados", Toast.LENGTH_SHORT).show();
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
}