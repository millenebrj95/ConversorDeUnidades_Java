package com.example.conversordeunidades;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerTo;
    private EditText editValue;
    private Button buttonConvert;
    private TextView textResult;

    private final String[] units = {"Centímetros", "Metros", "Quilômetros", "Milhas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editValue = findViewById(R.id.editValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textResult = findViewById(R.id.textResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        buttonConvert.setOnClickListener(v -> {
            String input = editValue.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
                return;
            }

            double value = Double.parseDouble(input);
            String from = spinnerFrom.getSelectedItem().toString();
            String to = spinnerTo.getSelectedItem().toString();

            double result = convert(value, from, to);
            textResult.setText("Resultado: " + result + " " + to);
        });
    }

    private double convert(double value, String from, String to) {
        double valueInMeters;
        switch (from) {
            case "Centímetros":
                valueInMeters = value / 100;
                break;
            case "Metros":
                valueInMeters = value;
                break;
            case "Quilômetros":
                valueInMeters = value * 1000;
                break;
            case "Milhas":
                valueInMeters = value * 1609.34;
                break;
            default:
                valueInMeters = value;
        }

        switch (to) {
            case "Centímetros":
                return valueInMeters * 100;
            case "Metros":
                return valueInMeters;
            case "Quilômetros":
                return valueInMeters / 1000;
            case "Milhas":
                return valueInMeters / 1609.34;
            default:
                return valueInMeters;
        }
    }
}
