# TPO 1: Conversor de Moneda - Programación para Dispositivos Móviles

## Descripción de la App
Es una aplicación Android que funciona como un conversor de moneda bidireccional. Permite al usuario ingresar una cantidad y ver su equivalente convirtiendo de dólares a euros, y viceversa. Además, incluye una funcionalidad para que el valor de la tasa de conversión sea dinámico y modificable por el usuario en tiempo real.

## Implementación MVVM

Este proyecto fue desarrollado con el patrón MVVM para garantizar la separación de responsabilidades y un código limpio:

- **Model (`ConversorMoneda`):** Encargado de la lógica de negocio y los cálculos matemáticos. Contiene los métodos para convertir según la tasa de cambio y getters/setters para actualizarla.

- **ViewModel (`MainViewModel`):** Actúa como intermediario entre la vista y el modelo. Utiliza `MutableLiveData` para exponer los datos de forma observable, sin referencias directas a la Activity.

- **View (`MainActivity` + `activity_main.xml`):** Se encarga exclusivamente de mostrar la interfaz y escuchar las interacciones del usuario. Delega toda la lógica al ViewModel. Usa ViewBinding para conectar los elementos del XML de forma segura sin `findViewById()`.

## Diseño de la interfaz

La UI fue diseñada con un enfoque visual moderno inspirado en aplicaciones financieras de referencia. Se priorizó la claridad, la jerarquía visual y la consistencia entre componentes:

- Fondo oscuro (`#000000`) con cards en gris oscuro (`#1E1E1E`) para generar contraste y profundidad.
- Tipografía blanca de gran tamaño para los campos de entrada, facilitando la lectura del monto ingresado.
- Chips de moneda (USD / EUR) con fondo blanco y bordes redondeados, alineados visualmente con los campos de conversión.
- RadioButtons estilizados sin el círculo nativo, reemplazado por un fondo con forma de pill: verde (`#00EFC5`) cuando está seleccionado, gris oscuro cuando no lo está.
- Botón principal "Convertir" en blanco con texto negro y esquinas redondeadas, ubicado fijo en la parte inferior de la pantalla.
- Botón "Guardar" en verde dentro de la card de tasa de cambio, manteniendo coherencia cromática con el resto de los elementos de acción.
- Layout centrado verticalmente entre el contenido y el botón inferior, adaptable a distintos tamaños de pantalla.