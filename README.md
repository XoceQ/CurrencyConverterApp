# Currency Converter

## Descripción

El proyecto **Currency Converter** es una aplicación que permite a los usuarios convertir entre diferentes monedas utilizando tasas de cambio actualizadas desde una API. El programa filtra las monedas disponibles y guarda los resultados en un archivo JSON, proporcionando un formato amigable y legible.

## Características

- Obtiene tasas de cambio desde la API de [ExchangeRate-API](https://v6.exchangerate-api.com).
- Filtra y muestra monedas disponibles para la conversión.
- Permite al usuario ingresar múltiples monedas en un formato fácil de usar.
- Guarda los resultados de la conversión en un archivo JSON con un formato bien estructurado.
- Maneja errores para asegurar que todas las monedas ingresadas son válidas.

## Tecnologías Utilizadas
- Java
- Maven
- Gson (para manejar JSON)
- HttpResponse, HttpClient y Request (para realizar solicitudes HTTP)
- API de ExchangeRate
