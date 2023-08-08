package com.media.escolar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.media.escolar.databinding.ActivityMainBinding;
import com.media.escolar.databinding.FragmentPrimeiroBimestreBinding;
import com.media.escolar.fragments.FragmentPrimeiroBimestre;
import com.media.escolar.fragments.FragmentQuartoBimestre;
import com.media.escolar.fragments.FragmentResultado;
import com.media.escolar.fragments.FragmentSegundoBimestre;
import com.media.escolar.fragments.FragmentTerceiroBimestre;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SHARED_PREF = "sharedPreferences";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FragmentPrimeiroBimestreBinding fragmentPrimeiroBimestreBinding;


    String situacaoAtualPrimeiroBimestre, materia1Bimestre, situacaoAtualSegundoBimestre, materia2Bimestre,
            situacaoAtualTerceiroBimestre, materia3Bimestre, situacaoAtualQuartoBimestre, materia4Bimestre;
    Boolean primeiroBimestre, segundoBimestre, terceiroBimestre, quartoBimestre;
    Double media1Bimestre, media2Bimestre, media3Bimestre, media4Bimestre, mediaFinal;
    FragmentManager fragmentManager;
    public static final String MAIN_ACTIVITY = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(MAIN_ACTIVITY, "onCreate: ...");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        fragmentPrimeiroBimestreBinding = FragmentPrimeiroBimestreBinding.bind(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // lerSharedPreferences();
        fragmentManager = getSupportFragmentManager();
        //Alterando conteúdo do content main
        fragmentManager.beginTransaction().replace(R.id.contentFragment, new FragmentPrimeiroBimestre()).commit();

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_bimestre_a, R.id.nav_bimestre_b, R.id.nav_bimestre_c,
                R.id.nav_bimestre_d, R.id.nav_resultado_final)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


/*

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

    }*/

    /*private void clearMenu() {
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
    }*/

    @Override
    public void onResume() {
        super.onResume();
        Log.d("media escolar", "*****************onResume*****************");
      //  lerSharedPreferences();
      //  visualizarResultado();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i(MAIN_ACTIVITY, "onNavigationItemSelected: Aqui entrou? ");
        int id = item.getItemId();
        if(id ==R.id.nav_bimestre_a){
            setTitle("Notas Primeiro Bimestre");
            fragmentManager.beginTransaction().replace(R.id.contentFragment, new FragmentPrimeiroBimestre()).commit();
        }else if(id == R.id.nav_bimestre_b){
            setTitle("Notas Segundo Bimestre");
            fragmentManager.beginTransaction().replace(R.id.contentFragment, new FragmentSegundoBimestre()).commit();
        } else if (id == R.id.nav_bimestre_c) {
            setTitle("Notas Terceiro Bimestre");
            fragmentManager.beginTransaction().replace(R.id.contentFragment, new FragmentTerceiroBimestre()).commit();
        } else if (id == R.id.nav_bimestre_d) {
            setTitle("Notas Quarto Bimestre");
            fragmentManager.beginTransaction().replace(R.id.contentFragment, new FragmentQuartoBimestre()).commit();
        }else if (id == R.id.nav_resultado_final) {
            setTitle("Resultado Final");
            fragmentManager.beginTransaction().replace(R.id.contentFragment, new FragmentResultado()).commit();

        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}