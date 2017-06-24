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

public class MediaCompletaActivity extends AppCompatActivity {

    private EditText edtKmInicial;
    private EditText edtKmFinal;
    private EditText edtQtdConsumida;
    private TextView tvResultadoMediaCompleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_completa);

        this.edtKmInicial = (EditText) findViewById(R.id.edtKmInicial);
        this.edtKmFinal = (EditText) findViewById(R.id.edtKmFinal);
        this.edtQtdConsumida = (EditText) findViewById(R.id.edtQtdConsumida);
        this.tvResultadoMediaCompleta = (TextView) findViewById(R.id.tvResultadoMediaCompleta);

        Button btnRetornar = (Button) findViewById(R.id.btnRetornar);
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            }
        });
    }

    /**
     * Calcula a média completa do consumo dados a quilometragem inicial e a quilometragem final em Km e
     * a quantidade de combustível consumida em litros.
     *
     * @param view view é o componente que chama este método. Neste caso, o botão btnCalcularMediaCompleta.
     */
    public void calcularMediaCompleta(View view) {

        if (isCamposMediaCompletaValidos()) {

            double kmInicial = Double.parseDouble(this.edtKmInicial.getText().toString());
            double kmFinal = Double.parseDouble(this.edtKmFinal.getText().toString());
            double qtdLitrosCompleta = Double.parseDouble(this.edtQtdConsumida.getText().toString());

            double mediaCompleta = (kmFinal - kmInicial) / qtdLitrosCompleta;

            Resources res = getResources();
            String resultadoMediaCompleta = String.format(Locale.getDefault(), "%1$,.1f", mediaCompleta);
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.resultadoMedia, resultadoMediaCompleta));
            Toast.makeText(this, res.getString(R.string.resultadoMedia, resultadoMediaCompleta), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Verifica se há algum campo inválido impedindo que o cálculo da média completa do consumo possa ser feito.
     *
     * @return boolean
     */
    private boolean isCamposMediaCompletaValidos() {

        String mensagem;
        Resources res = getResources();
        if (this.edtKmInicial.getText().toString().trim().equals("")) {
            mensagem = "KM inicial em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (this.edtKmFinal.getText().toString().trim().equals("")) {
            mensagem = "KM final em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (this.edtQtdConsumida.getText().toString().trim().equals("")) {
            mensagem = "Qtd litros em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        return true;
    }

    /**
     * Verifica se há algum campo inválido impedindo que o cálculo da média completa do consumo possa ser feito.
     *
     * @return boolean
     */
    private boolean isCamposMediaCompletaValidos(EditText edtKmInicial, EditText edtKmFinal, EditText edtQtdConsumida) {

        String mensagem;
        Resources res = getResources();
        if (edtKmInicial.getText().toString().trim().equals("")) {
            mensagem = "KM inicial em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (edtKmFinal.getText().toString().trim().equals("")) {
            mensagem = "KM final em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (edtQtdConsumida.getText().toString().trim().equals("")) {
            mensagem = "Qtd litros em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultadoMediaCompleta.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        return true;
    }


}
