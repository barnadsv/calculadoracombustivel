package br.unisinos.barnadsv.calculadoracombustivel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviaCalculoMelhorCombustivel = (Button) findViewById(R.id.btnEnviaCalculoMelhorCombustivel);
        btnEnviaCalculoMelhorCombustivel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MelhorCombustivelActivity.class);
            startActivity(intent);
            }
        });

        Button btnEnviaCalculoMediaSimples = (Button) findViewById(R.id.btnEnviaCalculoMediaSimples);
        btnEnviaCalculoMediaSimples.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MediaSimplesActivity.class);
            startActivity(intent);
            }
        });

        Button btnEnviaCalculoMediaCompleta = (Button) findViewById(R.id.btnEnviaCalculoMediaCompleta);
        btnEnviaCalculoMediaCompleta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MediaCompletaActivity.class);
            startActivity(intent);
            }
        });

        Button btnEnviaCalculoGastoDinheiro = (Button) findViewById(R.id.btnEnviaCalculoGastoDinheiro);
        btnEnviaCalculoGastoDinheiro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), GastoDinheiroActivity.class);
            startActivity(intent);
            }
        });

    }

}
