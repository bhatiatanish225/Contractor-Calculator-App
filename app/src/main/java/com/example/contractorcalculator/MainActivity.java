package com.example.contractorcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLabor;
    private EditText editTextMaterial;
    private TextView textViewSubTotalValue;
    private TextView textViewTaxValue;
    private TextView textViewTotalValue;

    private static final BigDecimal TAX_RATE = new BigDecimal("0.05");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLabor = findViewById(R.id.editTextLabor);
        editTextMaterial = findViewById(R.id.editTextMaterial);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewSubTotalValue = findViewById(R.id.textViewSubTotalValue);
        textViewTaxValue = findViewById(R.id.textViewTaxValue);
        textViewTotalValue = findViewById(R.id.textViewTotalValue);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCosts();
            }
        });
    }

    private void calculateCosts() {
        String laborCostStr = editTextLabor.getText().toString();
        String materialCostStr = editTextMaterial.getText().toString();

        if (!laborCostStr.isEmpty() && !materialCostStr.isEmpty()) {
            BigDecimal laborCost = new BigDecimal(laborCostStr);
            BigDecimal materialCost = new BigDecimal(materialCostStr);
            BigDecimal subTotal = laborCost.add(materialCost);
            BigDecimal tax = subTotal.multiply(TAX_RATE);
            BigDecimal total = subTotal.add(tax);

            textViewSubTotalValue.setText(subTotal.toString());
            textViewTaxValue.setText(tax.toString());
            textViewTotalValue.setText(total.toString());
        }
    }
}
