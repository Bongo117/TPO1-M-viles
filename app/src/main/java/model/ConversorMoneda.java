package model;

public class ConversorMoneda {
    private double cambioUsdAEur = 0.87 ;

    //multiplico los dolares por el cambio a euros
    public double convertirAEur(double dolares){
        return dolares* cambioUsdAEur;
    }
    //divido los euros por el cambio a dolares
    public double convertirADolares(double euros){
        return euros/ cambioUsdAEur;
    }

    public double getCambioUsdAEur() {
        return cambioUsdAEur;
    }

    public void setCambioUsdAEur(double cambioUsdAEur) {
        this.cambioUsdAEur = cambioUsdAEur;
    }
}
