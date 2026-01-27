package com.example.t2_e1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button miButton = (Button) findViewById(R.id.elboton);
        TextView miText = (TextView) findViewById(R.id.textView2);
        EditText miEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);


        miButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miText.setText(miEmail.getText().toString());
                miText.setRotation(45);
            }
        });
    }
}