$( document ).ready(function() {
    console.log( "ready!" );

    var button = $("#submit_button");   
    var searchBox = $("#search_text"); 
    var resultsTable = $("#results table tbody"); 
    var resultsWrapper = $("#results"); 

    button.on("click", function(){

        $.ajax({
          method : "POST",
          contentType: "application/json",
          data: createRequest(),
          url: "procesar_datos",
          dataType: "json",
          success: onHttpResponse
          });
      });

    

    function createRequest() {
        var searchQueryTmp = searchBox.val();

        var frontEndRequest = {
            searchQuery: searchQueryTmp,
        };
        
        return JSON.stringify(frontEndRequest);
    }

    function onHttpResponse(data, status) {
        if (status === "success" ) {
            console.log(data);
            addResults(data);
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }

    function addResults(data) {
        resultsTable.empty();
        var cadena = data.cadena;
        let libro = data.libros;
        resultsWrapper.show();
        resultsTable.append("<thead><tr><th>Datos recibidos   </th><th> Libro 1  </th><th> Libro 2  </th><th> Libro 3  </th></tr></thead><tr><td>" + cadena + "</td>"+
                "<td><img src=\"ui_assets/img/"+libro[0]+".png\" width=100px height=150px alt="+libro[0]+"></td>"+
                "<td><img src=\"ui_assets/img/"+libro[1]+".png\" width=100px height=150px alt="+libro[1]+"></td>"+
                "<td><img src=\"ui_assets/img/"+libro[2]+".png\" width=100px height=150px alt="+libro[2]+"></td></tr>");
    }
});

$(document).ready(function () {
    graficaWebServer();
    setInterval(graficaWebServer,10000);

    var historialMemoria = [];
    var historialCPU = [];

    function graficaWebServer() {
        $.ajax({
            method : "GET",
            contentType: "application/json",
            url: "datos",
            dataType: "json",
            success: onHttpResponse
            });
    }
    function onHttpResponse(data, status) {
        if (status === "success" ) {
            console.log(data);
            historialCPU = data.data[0];
            historialMemoria = data.data[1];
            mostrarGrafica();
            mostrarGrafica2();
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }
    function mostrarGrafica(){
        var chartContainer = $("#myChartContainer");
        var ctx = document.getElementById('myChart').getContext('2d');  

        if (window.myChart instanceof Chart) {
            window.myChart.destroy();
        }
        window.myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: historialCPU.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de CPU',
                    data: historialCPU,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'WebServer'
                    }
                }
            }
        });
    }

    function mostrarGrafica2(){
        var chartContainer = $("#myChartContainer");
        var ctx2 = document.getElementById('myChart2').getContext('2d');  

        if (window.myChart2 instanceof Chart) {
            window.myChart2.destroy();
        }
        window.myChart2 = new Chart(ctx2, {
            type: 'line',
            data: {
                labels: historialMemoria.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de Memoria',
                    data: historialMemoria,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'WebServer'
                    }
                }
            }
        });
    }

});

$(document).ready(function () {
    graficaServidor1();
    setInterval(graficaServidor1,10000);

    var historialMemoria2 = [];
    var historialCPU2 = [];

    function graficaServidor1() {
        $.ajax({
            method : "GET",
            contentType: "application/json",
            url: "datosServidor1",
            dataType: "json",
            success: onHttpResponse
            });
    }
    function onHttpResponse(data, status) {
        if (status === "success" ) {
            console.log(data);
            historialCPU2 = data.data[0];
            historialMemoria2 = data.data[1];
            mostrarGrafica();
            mostrarGrafica2();
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }
    function mostrarGrafica(){
        var chartContainer = $("#myChartContainer");
        var ctx3 = document.getElementById('myChart3').getContext('2d');  

        if (window.myChart3 instanceof Chart) {
            window.myChart3.destroy();
        }
        window.myChart3 = new Chart(ctx3, {
            type: 'line',
            data: {
                labels: historialCPU2.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de CPU',
                    data: historialCPU2,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Servidor 1'
                    }
                }
            }
        });
    }

    function mostrarGrafica2(){
        var chartContainer = $("#myChartContainer");
        var ctx4 = document.getElementById('myChart4').getContext('2d');  

        if (window.myChart4 instanceof Chart) {
            window.myChart4.destroy();
        }
        window.myChart4 = new Chart(ctx4, {
            type: 'line',
            data: {
                labels: historialMemoria2.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de Memoria',
                    data: historialMemoria2,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Servidor 1'
                    }
                }
            }
        });
    }

});

$(document).ready(function () {
    graficaServidor2();
    setInterval(graficaServidor2,10000);

    var historialMemoria3 = [];
    var historialCPU3 = [];

    function graficaServidor2() {
        $.ajax({
            method : "GET",
            contentType: "application/json",
            url: "datosServidor2",
            dataType: "json",
            success: onHttpResponse
            });
    }
    function onHttpResponse(data, status) {
        if (status === "success" ) {
            console.log(data);
            historialCPU3 = data.data[0];
            historialMemoria3 = data.data[1];
            mostrarGrafica();
            mostrarGrafica2();
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }
    function mostrarGrafica(){
        var chartContainer = $("#myChartContainer");
        var ctx5 = document.getElementById('myChart5').getContext('2d');  

        if (window.myChart5 instanceof Chart) {
            window.myChart5.destroy();
        }
        window.myChart5 = new Chart(ctx5, {
            type: 'line',
            data: {
                labels: historialCPU3.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de CPU',
                    data: historialCPU3,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Servidor 2'
                    }
                }
            }
        });
    }

    function mostrarGrafica2(){
        var chartContainer = $("#myChartContainer");
        var ctx6 = document.getElementById('myChart6').getContext('2d');  

        if (window.myChart6 instanceof Chart) {
            window.myChart6.destroy();
        }
        window.myChart6 = new Chart(ctx6, {
            type: 'line',
            data: {
                labels: historialMemoria3.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de Memoria',
                    data: historialMemoria3,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Servidor 2'
                    }
                }
            }
        });
    }

});

$(document).ready(function () {
    graficaServidor3();
    setInterval(graficaServidor3,10000);

    var historialMemoria4 = [];
    var historialCPU4 = [];

    function graficaServidor3() {
        $.ajax({
            method : "GET",
            contentType: "application/json",
            url: "datosServidor3",
            dataType: "json",
            success: onHttpResponse
            });
    }
    function onHttpResponse(data, status) {
        if (status === "success" ) {
            console.log(data);
            historialCPU4 = data.data[0];
            historialMemoria4 = data.data[1];
            mostrarGrafica();
            mostrarGrafica2();
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }
    function mostrarGrafica(){
        var chartContainer = $("#myChartContainer");
        var ctx7 = document.getElementById('myChart7').getContext('2d');  

        if (window.myChart7 instanceof Chart) {
            window.myChart7.destroy();
        }
        window.myChart7 = new Chart(ctx7, {
            type: 'line',
            data: {
                labels: historialCPU4.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de CPU',
                    data: historialCPU4,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Servidor 3'
                    }
                }
            }
        });
    }

    function mostrarGrafica2(){
        var chartContainer = $("#myChartContainer");
        var ctx8 = document.getElementById('myChart8').getContext('2d');  

        if (window.myChart8 instanceof Chart) {
            window.myChart8.destroy();
        }
        window.myChart8 = new Chart(ctx8, {
            type: 'line',
            data: {
                labels: historialMemoria4.map((_, index) => index +1),
                datasets: [{
                    label: 'Historial de uso de Memoria',
                    data: historialMemoria4,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    yAxisID: 'y-axis-0'
                }]
            },
            options: {
                scales: {
                    y: {
                            type: 'linear',
                            position: 'left',
                            beginAtZero: true,
                            id: 'y-axis-0'
                        }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Servidor 3'
                    }
                }
            }
        });
    }

});

$(document).ready(function() {
    // Oculta el div de resultados al principio
    $('#resultados').hide();

    // Centra el div de búsqueda al principio
    //$('#busqueda').css('margin', '20px');

    // Agrega un evento de clic al botón
    $('#submit_button').on('click', function() {
        // Muestra el div de resultados al hacer clic
        $('#resultados').show();

        // Ajusta la posición de los divs (puedes ajustar según sea necesario)
        $('#busqueda').css('margin', '0 20px 0 0');
        $('#resultados').css('margin', '0 0 0 20px');
    });
});



