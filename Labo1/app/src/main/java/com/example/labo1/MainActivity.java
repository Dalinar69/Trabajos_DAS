package com.example.labo1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Variables de estado
    private int contador = 0;
    private int contador2 = 0;

    // Referencias a los layouts para acceso global
    private LinearLayout ellayout;
    private GridLayout ellayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inicialización del Layout Principal (Vertical)
        ellayout = new LinearLayout(this);
        ellayout.setOrientation(LinearLayout.VERTICAL);
        ellayout.setPadding(50, 50, 50, 50);
        ellayout.setBackgroundColor(Color.parseColor("#E0F7FA"));

        // Inicialización del Layout Secundario (Grid)
        ellayout2 = new GridLayout(this);
        ellayout2.setColumnCount(2);
        ellayout2.setBackgroundColor(Color.parseColor("#E0F7FA"));
        ellayout2.setPadding(50, 50, 50, 50);

        // --- Creación de Vistas ---

        // Campo de texto para nombre de usuario
        EditText cajaNombre = new EditText(this);
        cajaNombre.setHint("Escribe tu nombre aquí");

        // TextView para el mensaje de bienvenida en el segundo layout
        TextView textoSaludo = new TextView(this);
        textoSaludo.setTextSize(22);
        textoSaludo.setTextColor(Color.parseColor("#00695C"));
        textoSaludo.setGravity(Gravity.CENTER);

        // Configuración del saludo para ocupar 2 columnas en el Grid
        GridLayout.LayoutParams paramsSaludo = new GridLayout.LayoutParams();
        paramsSaludo.columnSpec = GridLayout.spec(0, 2);
        paramsSaludo.width = GridLayout.LayoutParams.MATCH_PARENT;
        paramsSaludo.setMargins(0, 0, 0, 30);
        textoSaludo.setLayoutParams(paramsSaludo);

        // Título estático para el modo Grid
        TextView textoTituloGrid = new TextView(this);
        textoTituloGrid.setText("MODO GRID ACTIVADO");
        textoTituloGrid.setTextSize(20);
        textoTituloGrid.setGravity(Gravity.CENTER);
        textoTituloGrid.setPadding(0, 0, 0, 40);

        GridLayout.LayoutParams paramsTitulo = new GridLayout.LayoutParams();
        paramsTitulo.columnSpec = GridLayout.spec(0, 2);
        paramsTitulo.width = GridLayout.LayoutParams.MATCH_PARENT;
        textoTituloGrid.setLayoutParams(paramsTitulo);

        // Añadimos el título al Grid permanentemente
        ellayout2.addView(textoTituloGrid);

        // Elementos comunes (Contadores y Botones)
        TextView texto2 = new TextView(this);
        texto2.setText("Contador:");
        texto2.setTextSize(18);

        TextView texto = new TextView(this);
        texto.setText("0");
        texto.setTextSize(30);
        texto.setPadding(0, 0, 0, 20);

        Button boton = new Button(this);
        boton.setText("Aumentar");

        Button boton2 = new Button(this);
        boton2.setText("Ocultar/Ver");

        Button boton3 = new Button(this);
        boton3.setText("Cambiar Vista");
        boton3.setBackgroundColor(Color.BLACK);
        boton3.setTextColor(Color.WHITE);

        // --- Lógica de los Listeners ---

        // Botón 1: Incrementa contador y cambia el color de fondo dinámicamente
        boton.setOnClickListener(v -> {
            contador++;
            texto.setText(String.valueOf(contador));

            int colorFondo;
            if (contador % 2 == 0) {
                colorFondo = Color.parseColor("#FFCDD2"); // Rojo suave
            } else {
                colorFondo = Color.parseColor("#BBDEFB"); // Azul suave
            }

            // Aplicar color al layout activo
            if (boton.getParent() == ellayout) {
                ellayout.setBackgroundColor(colorFondo);
            } else {
                ellayout2.setBackgroundColor(colorFondo);
            }
        });

        // Botón 2: Alterna la visibilidad del contador secundario
        boton2.setOnClickListener(v -> {
            contador2++;
            if (contador2 % 2 != 0) {
                texto.setVisibility(View.VISIBLE);
            } else {
                texto.setVisibility(View.INVISIBLE);
            }
        });

        // Botón 3: Gestión del cambio entre Layouts (Linear <-> Grid)
        boton3.setOnClickListener(v -> {
            if (boton3.getParent() == ellayout) {
                // Transición a Grid Layout

                // Obtener nombre del usuario
                String nombreUsuario = cajaNombre.getText().toString();
                if (nombreUsuario.isEmpty()) {
                    nombreUsuario = "Usuario";
                }
                textoSaludo.setText("¡Hola, " + nombreUsuario + "!");

                // Limpiar vistas del layout actual
                ellayout.removeView(cajaNombre);
                ellayout.removeView(boton);
                ellayout.removeView(boton2);
                ellayout.removeView(texto);
                ellayout.removeView(texto2);
                ellayout.removeView(boton3);

                // Configurar parámetros para Grid
                setGridParams(boton);
                setGridParams(texto2);
                setGridParams(texto);
                setGridParams(boton2);

                // Configurar botón de cambio para ocupar todo el ancho
                GridLayout.LayoutParams paramsFull = new GridLayout.LayoutParams();
                paramsFull.columnSpec = GridLayout.spec(0, 2);
                paramsFull.width = GridLayout.LayoutParams.MATCH_PARENT;
                paramsFull.setMargins(0, 50, 0, 0);
                boton3.setLayoutParams(paramsFull);

                // Añadir vistas al nuevo layout
                ellayout2.addView(textoSaludo);
                ellayout2.addView(boton);
                ellayout2.addView(texto2);
                ellayout2.addView(texto);
                ellayout2.addView(boton2);
                ellayout2.addView(boton3);

                setContentView(ellayout2);

            } else {
                // Transición a Linear Layout (Retorno)

                // Limpiar vistas del Grid
                ellayout2.removeView(textoSaludo);
                ellayout2.removeView(boton);
                ellayout2.removeView(boton2);
                ellayout2.removeView(texto);
                ellayout2.removeView(texto2);
                ellayout2.removeView(boton3);

                // Restaurar parámetros para LinearLayout
                setLinearParams(boton);
                setLinearParams(texto2);
                setLinearParams(texto);
                setLinearParams(boton2);
                setLinearParams(boton3);
                setLinearParams(cajaNombre);

                // Reconstruir layout principal
                ellayout.addView(cajaNombre);
                ellayout.addView(boton);
                ellayout.addView(texto2);
                ellayout.addView(texto);
                ellayout.addView(boton2);
                ellayout.addView(boton3);

                setContentView(ellayout);
            }
        });

        // Configuración inicial de la vista
        setLinearParams(cajaNombre);
        ellayout.addView(cajaNombre);
        ellayout.addView(boton);
        ellayout.addView(texto2);
        ellayout.addView(texto);
        ellayout.addView(boton2);
        ellayout.addView(boton3);

        setContentView(ellayout);

        // Gestión de Insets (márgenes del sistema) para evitar solapamientos
        ViewCompat.setOnApplyWindowInsetsListener(ellayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewCompat.setOnApplyWindowInsetsListener(ellayout2, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Ajuste adicional de padding para el modo Grid
            v.setPadding(50 + systemBars.left, systemBars.top, 50 + systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Configura los parámetros de visualización para elementos dentro de un GridLayout.
     * Establece un peso de 1 para distribución equitativa.
     */
    private void setGridParams(View v) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.width = 0;
        params.setMargins(10, 10, 10, 10);
        v.setLayoutParams(params);
    }

    /**
     * Restaura los parámetros de visualización para elementos dentro de un LinearLayout.
     */
    private void setLinearParams(View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        v.setLayoutParams(params);
    }
}