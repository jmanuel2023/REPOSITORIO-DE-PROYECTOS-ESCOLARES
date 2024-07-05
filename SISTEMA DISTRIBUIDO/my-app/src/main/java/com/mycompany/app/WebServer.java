/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.mycompany.app;
import com.mycompany.app.Aggregator;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import com.mycompany.app.Libro;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.io.InputStream;  
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;

import com.fasterxml.jackson.databind.DeserializationFeature;   
import com.fasterxml.jackson.databind.ObjectMapper;             
import com.fasterxml.jackson.databind.PropertyNamingStrategy;


public class WebServer {
   
    private static final String STATUS_ENDPOINT = "/status";
    private static final String HOME_PAGE_ENDPOINT = "/";
    private static final String HOME_PAGE_UI_ASSETS_BASE_DIR = "/ui_assets/";
    private static final String ENDPOINT_PROCESS = "/procesar_datos";
    private static final String GRAFICAS_ENDPOINT = "/datos";
    private static final String SERVIDOR_UNO = "http://34.125.143.71:80/servidor1";
    private static final String SERVIDOR_DOS = "http://34.125.113.233:80/servidor2";
    private static final String SERVIDOR_TRES = "http://34.16.186.103:80/servidor3";

    
    private List<Double> historialMemoria = new ArrayList<>();
    private List<Double> historialCPU = new ArrayList<>();


    private final int port; 
    private HttpServer server; 
    private final ObjectMapper objectMapper;

    public WebServer(int port) {
        this.port = port;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT); 
        HttpContext taskContext = server.createContext(ENDPOINT_PROCESS);
        HttpContext homePageContext = server.createContext(HOME_PAGE_ENDPOINT);
        HttpContext graficasContext = server.createContext(GRAFICAS_ENDPOINT);
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        homePageContext.setHandler(this::handleRequestForAsset);
        graficasContext.setHandler(this::handleRequestGraphics);

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

    private void handleRequestForAsset(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        byte[] response;

        String asset = exchange.getRequestURI().getPath(); 

        if (asset.equals(HOME_PAGE_ENDPOINT)) { 
            response = readUiAsset(HOME_PAGE_UI_ASSETS_BASE_DIR + "index.html");
        }   else {
            response = readUiAsset(asset); 
        }
        addContentType(asset, exchange);
        sendResponse(response, exchange);
    }

    private byte[] readUiAsset(String asset) throws IOException {
        InputStream assetStream = getClass().getResourceAsStream(asset);

        if (assetStream == null) {
            return new byte[]{};
        }
        return assetStream.readAllBytes(); 
    }

    private static void addContentType(String asset, HttpExchange exchange) {

        String contentType = "text/html";  
        if (asset.endsWith("js")) {
            contentType = "text/javascript";
        } else if (asset.endsWith("css")) {
            contentType = "text/css";
        }
        exchange.getResponseHeaders().add("Content-Type", contentType);
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) { 
            exchange.close();
            return;
        }

        try {
            List<Libro> libreria = new ArrayList<>();
            Aggregator ag = new Aggregator();
            FrontendSearchRequest frontendSearchRequest = objectMapper.readValue(exchange.getRequestBody().readAllBytes(), FrontendSearchRequest.class); 
            String frase = frontendSearchRequest.getSearchQuery();
            StringBuilder tareaServer1 = new StringBuilder();
            StringBuilder tareaServer2 = new StringBuilder();
            StringBuilder tareaServer3 = new StringBuilder();
            String [] palabra = frase.split(" ");
            for(String termino : palabra){
                tareaServer1.append(termino);
                tareaServer1.append(',');
                tareaServer2.append(termino);
                tareaServer2.append(',');
                tareaServer3.append(termino);
                tareaServer3.append(',');
            }
            File carpeta = new File("/home/hanz/proyecto_final/my-app/src/main/java/com/mycompany/app/LIBROS_TXT"); 
            File[] lista = carpeta.listFiles();    
            int cuenta=0;
            List<String> archivos = new ArrayList<>();
            for (int i = 0; i < lista.length; i++) {
                if (lista[i].isFile()){
                    archivos.add(lista[i].getName());
                    cuenta++;
                }
            }
            tareaServer1.deleteCharAt(tareaServer1.length() - 1);
            tareaServer2.deleteCharAt(tareaServer2.length() - 1);
            tareaServer3.deleteCharAt(tareaServer3.length() - 1);
            tareaServer1.append(':');
            tareaServer2.append(':');
            tareaServer3.append(':');

            int division = cuenta /3;
            int resto = cuenta % 3;

            List<String> conjunto1 = new ArrayList<>();
            List<String> conjunto2 = new ArrayList<>();
            List<String> conjunto3 = new ArrayList<>();
            
            for(int i=0; i< cuenta; i++){
                if(i<division || (resto > 0 && i<division+1)){
                    conjunto1.add(archivos.get(i));
                    tareaServer1.append(archivos.get(i));
                    tareaServer1.append("=");
                }
                else if(i<division * 2 || (resto > 1 && i < division*2 +1)){
                    conjunto2.add(archivos.get(i));
                    tareaServer2.append(archivos.get(i));
                    tareaServer2.append("=");
                }
                else{
                    conjunto3.add(archivos.get(i));
                    tareaServer3.append(archivos.get(i));
                    tareaServer3.append("=");
                }
            }
            tareaServer1.deleteCharAt(tareaServer1.length() - 1);
            tareaServer2.deleteCharAt(tareaServer2.length() - 1);
            tareaServer3.deleteCharAt(tareaServer3.length() - 1);

            System.out.println("Tarea 1: " + tareaServer1);
            String tarea1= tareaServer1.toString();
            System.out.println("Tarea 2: " + tareaServer2);
            String tarea2= tareaServer2.toString();
            System.out.println("Tarea 3: " + tareaServer3);
            String tarea3= tareaServer3.toString();
            List<String> results = ag.sendTasksToWorkers(Arrays.asList(SERVIDOR_UNO,SERVIDOR_DOS,SERVIDOR_TRES), Arrays.asList(tarea1,tarea2,tarea3));
            for (String result : results){
                System.out.println(result);
                String [] resultados = result.split("=");
                for (String valores : resultados){
                    System.out.println(valores);
                    String [] numeros = valores.split(":");
                    libreria.add(new Libro(numeros[0].replaceFirst("\\.txt$", ""), Double.parseDouble(numeros[1])));
                }
                }

            Collections.sort(libreria, Comparator.comparing(Libro::getNumero).reversed());
            
            String [] librosMostrar = new String[3];
            for(int p=0;p<Math.min(3, libreria.size()); p++){
                Libro libro = libreria.get(p);
                librosMostrar[p] = libro.getNombre();
            }
            
            //StringTokenizer st = new StringTokenizer(frase);
            FrontendSearchResponse frontendSearchResponse = new FrontendSearchResponse(frase, librosMostrar);
            byte[] responseBytes = objectMapper.writeValueAsBytes(frontendSearchResponse);
            sendResponse(responseBytes, exchange);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }
}


