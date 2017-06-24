package br.unisinos.barnadsv.calculadoracombustivel;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MelhorCombustivelActivity extends AppCompatActivity {

    private EditText edtPrecoGasolina;
    private EditText edtPrecoAlcool;
    private EditText edtConsumoGasolina;
    private EditText edtConsumoAlcool;
    private TextView tvResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melhor_combustivel);

        this.edtPrecoGasolina = (EditText) findViewById(R.id.edtPrecoGasolina);
        this.edtPrecoAlcool = (EditText) findViewById(R.id.edtPrecoAlcool);
        this.edtConsumoGasolina = (EditText) findViewById(R.id.edtConsumoGasolina);
        this.edtConsumoAlcool = (EditText) findViewById(R.id.edtConsumoAlcool);
        this.tvResultado = (TextView) findViewById(R.id.tvResultado);

        Button btnRetornar = (Button) findViewById(R.id.btnRetornar);
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            }
        });
    }

    /**
     * Calcula qual o melhor combustível entre a gasolina e o álcool do ponto de vista financeiro.
     * Utiliza os preços e o consumo em km/l de cada combustível para encontrar qual é o melhor.
     * Caso o usuário não coloque o consumo de cada combustível, o cálculo é feito apenas com os
     * preços dos combustíveis, levando em consideração a razão de 70% entre os preços do álcool
     * e da gasolina.
     *
     * @param view view é o componente que chama este método. Neste caso, o botão btnCalcular.
     */
    public void calcularMelhorCombustivel(View view) {

        if (isCamposMelhorCombustivelValidos()) {

            double precoGasolina = Double.parseDouble(this.edtPrecoGasolina.getText().toString());
            double precoAlcool = Double.parseDouble(this.edtPrecoAlcool.getText().toString());
            Editable consumoGasolinaText = this.edtConsumoGasolina.getText();
            Editable consumoAlcoolText = this.edtConsumoAlcool.getText();

            String mensagem;
            Resources res = getResources();
            /* Se os valores de consumo foram preenchidos, calcula considerando estes valores... */
            if (!consumoGasolinaText.toString().trim().equals("") && !consumoAlcoolText.toString().trim().equals("")) {
                double consumoGasolina = Double.parseDouble(consumoGasolinaText.toString());
                double consumoAlcool = Double.parseDouble(consumoAlcoolText.toString());
                double custoGasolina = precoGasolina / consumoGasolina;
                double custoAlcool = precoAlcool / consumoAlcool;
                BigDecimal custoGasolinaBd = new BigDecimal(custoGasolina);
                BigDecimal custoAlcoolBd = new BigDecimal(custoAlcool);
                custoGasolinaBd = custoGasolinaBd.setScale(3, RoundingMode.HALF_UP);
                custoAlcoolBd = custoAlcoolBd.setScale(3, RoundingMode.HALF_UP);
                if (custoAlcoolBd.compareTo(custoGasolinaBd) < 0) {
                    mensagem = "Vale a pena Alcool";
                } else if (custoAlcoolBd.compareTo(custoGasolinaBd) > 0) {
                    mensagem = "Vale a pena Gasolina";
                } else {
                    mensagem = "Tanto faz";
                }
            /* Se os valores de consumo não foram preenchidos, calcula considerando apenas os preços... */
            } else {
                double razao = precoAlcool / precoGasolina;
                BigDecimal razaoBd = new BigDecimal(razao);
                BigDecimal razao07 = new BigDecimal(0.7);
                razaoBd = razaoBd.setScale(3, RoundingMode.HALF_UP);
                razao07 = razao07.setScale(3, RoundingMode.HALF_UP);
                if (razaoBd.compareTo(razao07) < 0) {
                    mensagem = "Vale a pena Alcool";
                } else if (razaoBd.compareTo(razao07) > 0) {
                    mensagem = "Vale a pena Gasolina";
                } else {
                    mensagem = "Tanto faz";
                }
            }
            Toast.makeText(this, res.getString(R.string.resultadoMelhorCombustivel, mensagem), Toast.LENGTH_LONG).show();
            this.tvResultado.setText(res.getString(R.string.resultadoMelhorCombustivel, mensagem));
        }
    }

    /**
     * Verifica se há algum campo inválido impedindo que o cálculo de melhor combustível possa ser feito.
     *
     * @return boolean
     */
    private boolean isCamposMelhorCombustivelValidos() {

        String mensagem;
        Resources res = getResources();
        if (this.edtPrecoGasolina.getText().toString().trim().equals("")) {
            mensagem = "Preço da gasolina em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultado.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if (this.edtPrecoAlcool.getText().toString().trim().equals("")) {
            mensagem = "Preço do álcool em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultado.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }

        if ((this.edtConsumoGasolina.getText().toString().trim().equals("") && !this.edtConsumoAlcool.getText().toString().trim().equals("")) ||
                (!this.edtConsumoGasolina.getText().toString().trim().equals("") && this.edtConsumoAlcool.getText().toString().trim().equals(""))) {
            mensagem = "Consumo de gasolina e álcool devem estar ou ambos preenchidos ou ambos em branco";
            Toast.makeText(this, res.getString(R.string.atencao, mensagem), Toast.LENGTH_SHORT).show();
            this.tvResultado.setText(res.getString(R.string.atencao, mensagem));
            return false;
        }
        return true;
    }
}

