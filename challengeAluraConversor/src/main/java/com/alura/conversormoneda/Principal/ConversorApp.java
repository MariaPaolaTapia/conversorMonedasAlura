package com.alura.conversormoneda.Principal;

import com.alura.conversormoneda.modelos.Moneda;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        var opcion = 0;
        while (opcion != 7) {
            System.out.println("""
                    *********CONVERSOR DE MONEDAS *****
                    1-Dólar => Peso Argentino
                    2-Dólar => Euro
                    3-Dólar => Real
                    4-Euro => Dólar 
                    5-Euro => Peso Argentino
                    6-Euro => Real
                    7- SALIR
                    Elige una opción:""");
            opcion = Integer.parseInt(entrada.nextLine());
            if (opcion != 7) {
                System.out.println("Ingrese el valor a convertir: ");
                var valor = Integer.valueOf(entrada.nextLine());


                String url_str;
                if (opcion == 1 || opcion == 2 || opcion == 3)
                    url_str = "https://v6.exchangerate-api.com/v6/2d6e17d93d2c7298b8c7aea2/latest/usd";
                else
                    url_str = "https://v6.exchangerate-api.com/v6/2d6e17d93d2c7298b8c7aea2/latest/eur";
                HttpClient client = HttpClient.newBuilder()
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url_str))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                Gson gson = new Gson();
                Moneda moneda = gson.fromJson(json, Moneda.class);
                if (opcion == 1) {
                    Double argRate = moneda.getConversion_rates().get("ARS");
                System.out.println("*********RESULTADO: Dólar => Peso Argentino************");
                    System.out.println("1USD = " + argRate + " ARS");
                    System.out.println(valor + "USD = " + valor * argRate + " ARS");
                } else if (opcion == 2) {
                    Double eurRate = moneda.getConversion_rates().get("EUR");
                    System.out.println("*********RESULTADO: Dólar => Euro************");
                    System.out.println("1USD = " + eurRate + " EUR");
                    System.out.println(valor + "USD = " + valor * eurRate + " EUR");
                } else if (opcion == 3) {
                    Double realRate = moneda.getConversion_rates().get("BRL");
                    System.out.println("*********RESULTADO: Dólar => Real************");
                    System.out.println("1USD = " + realRate + " BRL");
                    System.out.println(valor + " USD = " + valor * realRate + " BRL");
                } else if (opcion == 4) {
                    Double realRate = moneda.getConversion_rates().get("USD");
                    System.out.println("*********RESULTADO: Euro => Dolar************");
                    System.out.println("1 EUR = " + realRate + " USD");
                    System.out.println(valor + " EUR = " + valor * realRate + " EUR");
                } else if (opcion == 5) {
                    Double realRate = moneda.getConversion_rates().get("ARS");
                    System.out.println("*********RESULTADO: Euro => Peso Argentino************");
                    System.out.println("1 EUR = " + realRate + " ARS");
                    System.out.println(valor + " EUR = " + valor * realRate + " ARS");
                } else if (opcion == 6) {
                    Double realRate = moneda.getConversion_rates().get("BRL");
                    System.out.println("*********RESULTADO: Euro => Real************");
                    System.out.println("1 EUR = " + realRate + " BRL");
                    System.out.println(valor + " EUR = " + valor * realRate + " BRL");
                } else {
                    System.out.println("Opción inválida.");
                }
            }
        }


    }

}
