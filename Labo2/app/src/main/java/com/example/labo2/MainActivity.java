package com.example.labo2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int i = 0;
    ArrayList<Integer> listaEstados = new ArrayList<>();
    String idiomaActual = "es";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ---------------------------------------------------------
        // 1. RECUPERAR DATOS
        // ---------------------------------------------------------

        // PRIORIDAD 1:
        if (savedInstanceState != null) {
            idiomaActual = savedInstanceState.getString("idioma_actual");
            i = savedInstanceState.getInt("valor_i");
            listaEstados = savedInstanceState.getIntegerArrayList("lista_estados");
            if (listaEstados == null) listaEstados = new ArrayList<>();


            i++;
        }
        // PRIORIDAD 2:
        else if (getIntent() != null && getIntent().hasExtra("idioma_seleccionado")) {
            idiomaActual = getIntent().getStringExtra("idioma_seleccionado");
            i = getIntent().getIntExtra("valor_i", 0);
            listaEstados = getIntent().getIntegerArrayListExtra("lista_estados");
            if (listaEstados == null) listaEstados = new ArrayList<>();

        }

        // ---------------------------------------------------------
        // 2. APLICAR IDIOMA
        // ---------------------------------------------------------
        aplicarConfiguracionIdioma(idiomaActual);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ---------------------------------------------------------
        // 3. UI Y LISTENERS
        // ---------------------------------------------------------

        Button btnEn = findViewById(R.id.button);
        if (btnEn != null) btnEn.setOnClickListener(v -> cambiarIdioma("en"));

        Button btnEs = findViewById(R.id.button2);
        if (btnEs != null) btnEs.setOnClickListener(v -> cambiarIdioma("es"));

        Button btnJa = findViewById(R.id.button3);
        if (btnJa != null) btnJa.setOnClickListener(v -> cambiarIdioma("ja"));

        // Contador
        TextView contador = findViewById(R.id.textView);
        if (contador != null) {
            contador.setText(String.valueOf(i));
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Registrar paso por onCreate
        anadirLog(R.string.estado_create);
    }

    // ---------------------------------------------------------
    // RESTO DEL CÓDIGO IDÉNTICO
    // ---------------------------------------------------------

    private void aplicarConfiguracionIdioma(String codigo) {
        if (codigo == null) return;
        Locale nuevaloc = new Locale(codigo);
        Locale.setDefault(nuevaloc);
        Configuration config = new Configuration();
        config.setLocale(nuevaloc);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void cambiarIdioma(String nuevoIdioma) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("idioma_seleccionado", nuevoIdioma);
        intent.putExtra("valor_i", i);
        intent.putIntegerArrayListExtra("lista_estados", listaEstados);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        anadirLog(R.string.estado_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        anadirLog(R.string.estado_resume);
    }

    @Override
    protected void onPause() {
        super.onPause();
        anadirLog(R.string.estado_pause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        anadirLog(R.string.estado_stop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        anadirLog(R.string.estado_destroy);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        anadirLog(R.string.estado_restart);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("valor_i", i);
        outState.putString("idioma_actual", idiomaActual);
        outState.putIntegerArrayList("lista_estados", listaEstados);
    }

    private void anadirLog(int idRecursoString) {
        listaEstados.add(idRecursoString);
        TextView tvLog = findViewById(R.id.textView2);
        if (tvLog != null) {
            StringBuilder textoCompleto = new StringBuilder();
            for (Integer id : listaEstados) {
                textoCompleto.append(getString(id)).append("\n");
            }
            tvLog.setText(textoCompleto.toString());
        }
    }
}