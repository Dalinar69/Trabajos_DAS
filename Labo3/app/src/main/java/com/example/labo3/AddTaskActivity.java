package com.example.labo3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);

    }

    public void onClick(View v)
    {
        EditText et = findViewById(R.id.editText);
        String texto = et.getText().toString();
        if (texto.isEmpty())
        {
            int tiempo = Toast.LENGTH_SHORT;
            Toast aviso = Toast.makeText(AddTaskActivity.this, "You must add a valid description", tiempo);
            aviso.show();
        }
        else {
            Intent i = new Intent();

            i.putExtra("texto", texto);
            setResult(RESULT_OK, i);
            finish();
        }
    }
}