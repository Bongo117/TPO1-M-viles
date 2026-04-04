package view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.eskere.tp1.databinding.ActivityMainBinding;

import viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mv; // declarado el viewmodel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv=new ViewModelProvider(this).get(MainViewModel.class); // inicializamos el viewmodel

        // ver la tasa de cambio
        mv.getTasaCambio().observe(this, tasa -> {
            binding.etResultado.setText(String.valueOf(tasa));
        });

        // para ver resultados de la conversion
        mv.getResultadoEuros().observe(this, res -> {
            binding.eteuros.setText(String.format("%.2f", res));
        });
        mv.getResultadoDolares().observe(this, res -> {
            binding.etdolares.setText(String.format("%.2f", res));
        });

        // toast para observar errores
        mv.getMensajeError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        });

        // logica de los RadioButtons segun el campo que se toque
        binding.etdolares.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.rbeuros.setChecked(true);
            }
        });

        binding.eteuros.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.rbdolares.setChecked(true);
            }
        });

        // Al tocar un RadioButton, el otro se deselecciona
        binding.rbeuros.setOnClickListener(v -> binding.etdolares.requestFocus());
        binding.rbdolares.setOnClickListener(v -> binding.eteuros.requestFocus());

        // boton CONVERTIR
        binding.btConvertir.setOnClickListener(v -> {
            boolean aEuros = binding.rbeuros.isChecked();
            String input = aEuros ? binding.etdolares.getText().toString()
                    : binding.eteuros.getText().toString();

            if (!input.isEmpty()) {
                try {
                    double monto = Double.parseDouble(input);
                    mv.calcularConversion(monto, aEuros);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Valor numérico inválido", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Ingrese un monto para convertir", Toast.LENGTH_LONG).show();
            }
        });

        // boton cambiar valor
        binding.btCambiarValor.setOnClickListener(v -> {
            String nuevaTasaStr = binding.etResultado.getText().toString();
            if (!nuevaTasaStr.isEmpty()) {
                try {
                    double nuevaTasa = Double.parseDouble(nuevaTasaStr);
                    mv.actualizarTasa(nuevaTasa);
                    Toast.makeText(this, "Tasa actualizada", Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Tasa inválida", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
