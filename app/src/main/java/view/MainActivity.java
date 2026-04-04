package view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.eskere.tp1.databinding.ActivityMainBinding;

import java.util.Locale;

import viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mv; // declarado el viewmodel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflamos el layout con viewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // inicializamos el viewmodel
        mv = new ViewModelProvider(this).get(MainViewModel.class);

        // observar la tasa de cambio y mostrarla
        mv.getTasaCambio().observe(this, tasa ->
                binding.etResultado.setText(String.valueOf(tasa))
        );

        // observar resultado en euros
        mv.getResultadoEuros().observe(this, res ->
                binding.eteuros.setText(String.format(Locale.US, "%.2f", res))
        );

        // observar resultado en dolares
        mv.getResultadoDolares().observe(this, res ->
                binding.etdolares.setText(String.format(Locale.US, "%.2f", res))
        );

        // mostrar errores con toast
        mv.getMensajeError().observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        );

        // cambiar el estilo visual de los botones al seleccionar
        binding.rgOpciones.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == binding.rbeuros.getId()) {

                // estilo seleccionado para euros
                binding.rbeuros.setBackgroundResource(
                        getResources().getIdentifier("bg_radio_selected", "drawable", getPackageName())
                );
                binding.rbeuros.setTextColor(getResources().getColor(android.R.color.black));

                // estilo no seleccionado para dolares
                binding.rbdolares.setBackgroundResource(
                        getResources().getIdentifier("bg_radio_unselected", "drawable", getPackageName())
                );
                binding.rbdolares.setTextColor(getResources().getColor(android.R.color.white));

            } else {

                // estilo seleccionado para dolares
                binding.rbdolares.setBackgroundResource(
                        getResources().getIdentifier("bg_radio_selected", "drawable", getPackageName())
                );
                binding.rbdolares.setTextColor(getResources().getColor(android.R.color.black));

                // estilo no seleccionado para euros
                binding.rbeuros.setBackgroundResource(
                        getResources().getIdentifier("bg_radio_unselected", "drawable", getPackageName())
                );
                binding.rbeuros.setTextColor(getResources().getColor(android.R.color.white));
            }
        });

        // cuando haces foco en dolares, selecciona convertir a euros
        binding.etdolares.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.rbeuros.setChecked(true);
            }
        });

        // cuando haces foco en euros, selecciona convertir a dolares
        binding.eteuros.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.rbdolares.setChecked(true);
            }
        });

        // al tocar los botones, mueve el foco al input correspondiente
        binding.rbeuros.setOnClickListener(v -> binding.etdolares.requestFocus());
        binding.rbdolares.setOnClickListener(v -> binding.eteuros.requestFocus());

        // boton convertir
        binding.btConvertir.setOnClickListener(v -> {

            // verificar direccion de conversion
            boolean aEuros = binding.rbeuros.isChecked();

            // obtener input segun conversion
            String input = aEuros
                    ? binding.etdolares.getText().toString()
                    : binding.eteuros.getText().toString();

            // validar input
            if (!input.isEmpty()) {
                try {
                    double monto = Double.parseDouble(input);

                    // llamar al viewmodel para convertir
                    mv.calcularConversion(monto, aEuros);

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Valor numérico inválido", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Ingrese un monto para convertir", Toast.LENGTH_LONG).show();
            }
        });

        // boton cambiar tasa de conversion
        binding.btCambiarValor.setOnClickListener(v -> {

            String nuevaTasaStr = binding.etResultado.getText().toString();

            if (!nuevaTasaStr.isEmpty()) {
                try {
                    double nuevaTasa = Double.parseDouble(nuevaTasaStr);

                    // actualizar tasa en el viewmodel
                    mv.actualizarTasa(nuevaTasa);

                    Toast.makeText(this, "Tasa actualizada", Toast.LENGTH_LONG).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Tasa inválida", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}