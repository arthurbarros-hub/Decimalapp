package  com.example.calculador.decimalapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculador.decimalapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPeso;
    private EditText editTextAltura;
    private Button buttonCalcular;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPeso = findViewById(R.id.editTextPeso);
        editTextAltura = findViewById(R.id.editTextAltura);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        textResultado = findViewById(R.id.textResultado);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC() {
        String pesoStr = editTextPeso.getText().toString();
        String alturaStr = editTextAltura.getText().toString();

        if (TextUtils.isEmpty(pesoStr) || TextUtils.isEmpty(alturaStr)) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float peso = Float.parseFloat(pesoStr);
            float altura = Float.parseFloat(alturaStr);

            if (peso <= 0 || altura <= 0) {
                Toast.makeText(this, "Valores inválidos.", Toast.LENGTH_SHORT).show();
                return;
            }

            float imc = peso / (altura * altura);
            String classificacao = classificarIMC(imc);

            String resultado = String.format("IMC: %.2f\nClassificação: %s", imc, classificacao);
            textResultado.setText(resultado);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    private String classificarIMC(float imc) {
        if (imc < 18.5f) {
            return "Abaixo do peso";
        } else if (imc < 24.9f) {
            return "Peso normal";
        } else if (imc < 29.9f) {
            return "Sobrepeso";
        } else if (imc < 34.9f) {
            return "Obesidade grau I";
        } else if (imc < 39.9f) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }
}
