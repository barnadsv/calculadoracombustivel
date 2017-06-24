package br.unisinos.barnadsv.calculadoracombustivel;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GastoDinheiroActivity extends AppCompatActivity {

    private EditText edtDistanciaPercorrida;
    private EditText edtConsumoCombustivel;
    private EditText edtPrecoCombustivel;
    private TextView tvResultadoGastoDinheiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_dinheiro);

        this.edtDistanciaPercorrida = (EditText) findViewById(R.id.edtDistanciaPercorrida);
        this.edtConsumoCombustivel = (EditText) findViewById(R.id.edtConsumoCombustivel);
        this.edtPrecoCombustivel = (EditText) findViewById(R.id.edtPrecoCombustivel);
        this.tvResultadoGastoDinheiro = (TextView) findViewById(R.id.tvResultadoGastoDinheiro);

        Button btnRetornar = (Button) findViewById(R.id.btnRetornar);
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            }
        });
    }

    /**
     * Calcula o gasto em reais dados o preço do combustível, o consumo do combustível em km/l
     * e a distância percorrida em Km.
     *
     * @param view view é o componente que chama este método. Neste caso, o botão btnCalcularGastoDinheiro.
     */
    public void calcularGastoDinheiro(View view) {

        if (isCamposGastoDinheiroValidos()) {

            double precoCombustivel = Double.parseDouble(this.edtPrecoCombustivel.getText().toString());
            double consumoCombustivel = Double.parseDouble(this.edtConsumoCombustivel.getText().toString());
            double distanciaKm = Double.parseDouble(this.edtDistanciaPercorrida.getText().toString());

            double gastoDinheiro = precoCombustivel * (distanciaKm / consumoCombustivel);

            Resources res = getResources();
            String resultadoGastoDinheiro = String.format(Locale.getDefault(), "%1$,.2f", gastoDinheiro);
            this.tvResultadoGastoDinheiro.setText(res.getString(R.string.resultadoGastoDinheiro, resultadoGastoDinheiro));
            Toast.makeText(this, res.getString(R.string.resultadoGastoDinheiro, resultadoGastoDinheiro), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Verifica se há algum campo inválido impedindo que o cálculo do gasto em dinheiro possa ser feito.
     *
     * @return boolean
     */
    private boolean isCamposGastoDinheiroValidos() {

        String mensagem;
        Resources res = getResources();
        if (this.edtDistanciaPercorrida.getText().toString().trim().equals("")) {
            mensagem = "Distância percorrida em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoGastoDinheiro.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (this.edtConsumoCombustivel.getText().toString().trim().equals("")) {
            mensagem = "Consumo de combustível em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoGastoDinheiro.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (this.edtPrecoCombustivel.getText().toString().trim().equals("")) {
            mensagem = "Preço do combustível em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoGastoDinheiro.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        return true;
    }
}
