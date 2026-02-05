package com.example.labo3;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            // Si venimos de un giro, recuperamos la lista guardada
            tasks = savedInstanceState.getStringArrayList("mis_tareas_guardadas");
        } else {
            // Si es la primera vez, creamos la lista nueva
            tasks = new ArrayList<String>();
        }


        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);

        listView.setAdapter(adapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tareaBorrada = tasks.get(i);
                tasks.remove(i);
                adapter.notifyDataSetChanged();

                int tiempo = Toast.LENGTH_SHORT;
                Toast aviso = Toast.makeText(MainActivity.this, "Eliminando tarea "+ tareaBorrada, tiempo);
                aviso.show();
                return true;
            }
        });

    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    //  Método para guardar datos al girar ---
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guardamos el ArrayList dentro del 'paquete' antes de que se destruya la actividad
        outState.putStringArrayList("mis_tareas_guardadas", tasks);
    }


    public void onClick(View v)
    {
        Intent i = new Intent(this, AddTaskActivity.class);
        startActivityIntent.launch(i);
    }
    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Add same code that you want to add in onActivityResult method
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String text = data.getStringExtra("texto");
                            tasks.add(text);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });


}