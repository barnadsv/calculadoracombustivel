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

public class MediaSimplesActivity extends AppCompatActivity {

    private EditText edtDistanciaPercorrida;
    private EditText edtQtdConsumida;
    private TextView tvResultadoMediaSimples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_simples);

        this.edtDistanciaPercorrida = (EditText) findViewById(R.id.edtDistanciaPercorrida);
        this.edtQtdConsumida = (EditText) findViewById(R.id.edtQtdConsumida);
        this.tvResultadoMediaSimples = (TextView) findViewById(R.id.tvResultadoMediaSimples);

        Button btnRetornar = (Button) findViewById(R.id.btnRetornar);
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            }
        });
    }

    /**
     * Calcula a média simples do consumo dados a distância percorrida em Km e
     * a quantidade de combustível consumida em litros.
     *
     * @param view view é o componente que chama este método. Neste caso, o botão btnCalcularMediaSimples.
     */
    public void calcularMediaSimples(View view) {

        if (isCamposMediaSimplesValidos()) {

            double kmRodados = Double.parseDouble(this.edtDistanciaPercorrida.getText().toString());
            double qtdLitros = Double.parseDouble(this.edtQtdConsumida.getText().toString());

            double mediaSimples = kmRodados / qtdLitros;

            Resources res = getResources();
            String resultadoMediaSimples = String.format(Locale.getDefault(), "%1$,.1f", mediaSimples);
            this.tvResultadoMediaSimples.setText(res.getString(R.string.resultadoMedia, resultadoMediaSimples));
            Toast.makeText(this, res.getString(R.string.resultadoMedia, resultadoMediaSimples), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Verifica se há algum campo inválido impedindo que o cálculo da média simples do consumo possa ser feito.
     *
     * @return boolean
     */
    private boolean isCamposMediaSimplesValidos() {

        String mensagem;
        Resources res = getResources();
        if (this.edtDistanciaPercorrida.getText().toString().trim().equals("")) {
            mensagem = "Distância percorrida em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaSimples.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (this.edtQtdConsumida.getText().toString().trim().equals("")) {
            mensagem = "Qtd consumida em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaSimples.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        return true;
    }
}
