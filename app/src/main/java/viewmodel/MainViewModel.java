package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import model.ConversorMoneda;

public class MainViewModel extends ViewModel {
    private ConversorMoneda modelo = new ConversorMoneda();
    private MutableLiveData<Double> resultadoDolares = new MutableLiveData<>();
    private MutableLiveData<Double> resultadoEuros = new MutableLiveData<>();


    // LiveData para la tasa de cambio actual
    private MutableLiveData<Double> tasaCambio = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public MainViewModel() {
        // inicializamos con el valor por defecto del modelo
        tasaCambio.setValue(modelo.getCambioUsdAEur());
    }

    public LiveData<Double> getResultadoDolares() { return resultadoDolares; }
    public LiveData<Double> getResultadoEuros() { return resultadoEuros; }
    public LiveData<Double> getTasaCambio() { return tasaCambio; }
    public LiveData<String> getMensajeError() { return mensajeError; }

    public void calcularConversion(double monto, boolean aEuros) {
        if (aEuros) {
            resultadoEuros.setValue(modelo.convertirAEur(monto));
        } else {
            resultadoDolares.setValue(modelo.convertirADolares(monto));
        }
    }

    public void actualizarTasa(double nuevaTasa) {
        if (nuevaTasa > 0) {
            modelo.setCambioUsdAEur(nuevaTasa);
            tasaCambio.setValue(nuevaTasa);
        } else {
            mensajeError.setValue("La tasa debe ser mayor a 0");
        }
    }
}