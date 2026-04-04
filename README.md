# TPO 1: Conversor de Moneda - Programación para Dispositivos Móviles

## Descripción de la App
Es una aplicación Android que funciona como un conversor de moneda bidireccional. Permite al usuario ingresar una cantidad y ver su equivalente convirtiendo de dólares a euros, y viceversa. Además, incluye una funcionalidad para que el valor de la tasa de conversión sea dinámico y modificable por el usuario en tiempo real.

## Este proyecto fue desarrollado con el patrón MVVM
para garantizar la separación de responsabilidades y un código limpio:

* **Model (`ConversorMoneda`):** Es una activity encargada de la lógica de negocio y los cálculos matemáticos. Contiene los métodos para multiplicar o dividir según la tasa de cambio y los getters and setters para actualizarla.

* **ViewModel (`MainViewModel`):** Actúa como intermediario entre la vista y el modelo. utiliza **MutableLiveData**.

* **View (`MainActivity` y `activity_main.xml`):** SSe encarga de mostrar graficamente la pantalla, escuchar cuando el usuario hace clic en 'Convertir' y mandarle esa información al ViewModel. Además,con ViewBinding se asegura de conectar los elementos del XML de forma segura sin usar findViewById()..
