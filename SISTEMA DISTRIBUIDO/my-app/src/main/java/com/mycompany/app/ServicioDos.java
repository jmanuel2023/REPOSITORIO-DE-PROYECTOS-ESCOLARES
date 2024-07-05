package com.mycompany.app; 

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
public class ServicioDos {
    private static final String SERVICIODOS_ENDPOINT = "/servidor2";
    private static final String ENDPOINT_GRAFICAS = "/datosServidor2";
    
    private List<Double> historialMemoria = new ArrayList<>();
    private List<Double> historialCPU = new ArrayList<>();
    private int port;
    private HttpServer server;
    public static void main (String [] args){
        int serverPort = 8082;
        if (args.length == 1){
            serverPort = Integer.parseInt(args[0]);
        }
        ServicioDos serDos = new ServicioDos(serverPort);
        serDos.startServer();
        System.out.println("Servidor en el puerto: "+serverPort);
    }
    public ServicioDos(int port){
        this.port = port;
    }    
    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        HttpContext serviciodosContext = server.createContext(SERVICIODOS_ENDPOINT);
        HttpContext graficaContext = server.createContext(ENDPOINT_GRAFICAS);
        serviciodosContext.setHandler(this::handleServicioDosRequest);
        graficaContext.setHandler(this::handleRequestGraphics);
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    
    private void handleRequestGraphics(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) { 
            exchange.close();
            return;
        }
        

        try{
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        //Variable de memoria
        Double memoria = formatMemoryUsage(heapMemoryUsage);
        //Variable de CPU
        double cpuUsage = operatingSystemMXBean.getSystemLoadAverage() * 100;
        
        historialMemoria.add(memoria);
        historialCPU.add(cpuUsage);
        
        
        String jsonData = "{ \"labels\": [\"CPU\", \"Memoria\"], \"data\":["+
        historialMemoria.toString()+","+ historialCPU.toString()+"]}";
        System.out.println(jsonData);

        sendResponse(jsonData.getBytes(), exchange);

        } catch (IOException e){
            e.printStackTrace();
            return;
        }


    }

    private static Double formatMemoryUsage(MemoryUsage memoryUsage) {
        return memoryUsage.getUsed() / (1024.0 * 1024.0);
    }

    private void handleServicioDosRequest (HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")){
            exchange.close();
            return;
        }


        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        System.out.println("*****Segundo Servidor******\n");
        StringBuilder respuestaFinal = new StringBuilder();
        String prueba = (String) SerializationUtils.deserialize(requestBytes);
        String[] params = prueba.split(":");
        String[] palabras = params[0].split(",");
        String[] rutas = params[1].split("=");
        BigDecimal [][] tfPalabra = new BigDecimal[rutas.length][palabras.length];
        int [] totalPalabras = new int[rutas.length];
        int [][] resultados = new int[rutas.length][palabras.length];
        //Ciclo for para el conteo total de palabras
        for(int i=0; i<rutas.length;i++){
            int conteoTotal = conteoTotalPalabras(rutas[i]);
            totalPalabras[i] = conteoTotal;
            System.out.println("--------------------------------------------------------------------------------------------\n");
            System.out.println("El libro "+rutas[i]+" tiene "+conteoTotal+" palabras\n");
            //Ciclo for para el conteo de coincidencia de las palabras dadas.
            for(int j=0;j<palabras.length;j++){
                int conteo = conteoPalabras(rutas[i], palabras[j]);
                resultados[i][j] = conteo;
                BigDecimal frecuenciaTermino= BigDecimal.valueOf(resultados[i][j]).divide(BigDecimal.valueOf(totalPalabras[i]), 10, BigDecimal.ROUND_HALF_UP);
                tfPalabra[i][j] = frecuenciaTermino;

                System.out.println("La palabra  "+palabras[j]+" se repite "+ resultados[i][j]+" en el libro "+rutas[i]+"\n");
                System.out.println("La palabra "+palabras[j]+" tiene una frecuencia de termino de: "+tfPalabra[i][j]+"\n");
            }
        }
        BigDecimal[][] sumaTf = new BigDecimal[rutas.length][1];
        
        // Inicializar sumaTf con ceros
        for (int i = 0; i < sumaTf.length; i++) {
            sumaTf[i][0] = BigDecimal.ZERO;
        }

        // Sumar las frecuencias de término a sumaTf
        for (int i = 0; i < rutas.length; i++) {
            respuestaFinal.append(rutas[i]);
            respuestaFinal.append(":");
            for (int j = 0; j < tfPalabra[i].length; j++) {
                sumaTf[i][0] = sumaTf[i][0].add(tfPalabra[i][j]);
            }
            respuestaFinal.append(sumaTf[i][0]);
            respuestaFinal.append("=");
        }

        // Imprimir la suma de frecuencias de término
        /*for (int i = 0; i < rutas.length; i++) {
            System.out.println("La suma de frecuencias de término para " + rutas[i] + " es: " + sumaTf[i][0]);
        }*/
        respuestaFinal.deleteCharAt(respuestaFinal.length() - 1);
        String respFinal = respuestaFinal.toString();
       
        byte[] responseBytes = SerializationUtils.serialize(respFinal);
        sendResponse(responseBytes, exchange);
    }

    public static int conteoPalabras(String archivo, String palabraBuscada) throws IOException {
        palabraBuscada = palabraBuscada.toLowerCase();
        String ruta = "/home/hanz/proyecto_final/my-app/src/main/java/com/mycompany/app/LIBROS_TXT/"+archivo;
        int conteo = 0;

        try(BufferedReader br =new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] palabras = linea.split("\\s+");
                for(String palabra : palabras){
                    palabra = palabra.replaceAll("[^a-zA-Z]+$","");
                    palabra = palabra.toLowerCase();
                    if(palabra.equals(palabraBuscada)){
                        conteo++;
                    }
                }
            }
        }
        return conteo;
    }

    public static int conteoTotalPalabras (String archivo) throws IOException{
        String ruta = "/home/hanz/proyecto_final/my-app/src/main/java/com/mycompany/app/LIBROS_TXT/"+archivo;
        int conteo = 0;

        try(BufferedReader br =new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] palabras = linea.split("\\s+");
                for(String palabra : palabras){
                    palabra = palabra.replaceAll("[^a-zA-Z0-9]+$","");
                    if (!palabra.isEmpty()) {
                        conteo++;
                    }
                }
            }
        }
        return conteo;
    }

    
    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}