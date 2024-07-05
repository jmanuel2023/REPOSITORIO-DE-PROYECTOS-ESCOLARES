import socket
import sys
import threading
import random
import logging
import time
global contador_pares
turno_actual=0
contador_pares=0
global matriz_guionP,matriz_guionA 
matriz_guionP = [['-','-','-','-'],['-','-','-','-'],['-','-','-','-'],['-','-','-','-']]
matriz_guionA=[['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-']]

def servirPorSiempre(socketTcp, listaconexiones,barrier, matrizG, condicionturno,numjugador):
    try:
        while True:
            client_conn, client_addr = socketTcp.accept()
            logging.debug("Conectado a%s", client_addr)
            listaconexiones.append(client_conn)
            logging.debug('%s\tConectado!!!. Esperando a que entren los demas jugadores',threading.current_thread().name)
            worker_id = barrier.wait()
            turnos.append(worker_id)
            print('%s\tListo!!!, el servidor se ha llenado. Que empiece el juego.!!!!', threading.current_thread().name)
            print(turnos)
            thread_read = threading.Thread(target=recibir_datos, args=[client_conn, client_addr, worker_id, matrizG,condicionturno,numjugador])
            thread_read.start()
            #gestion_conexiones(listaConexiones)
    except Exception as e:
        print(e)

def gestion_conexiones(listaconexiones):
    for conn in listaconexiones:
        if conn.fileno() == -1:
            listaconexiones.remove(conn)
    logging.debug("hilos activos:%s", threading.active_count())
    logging.debug("enum:%s", threading.enumerate())
    logging.debug("conexiones:%d ", len(listaconexiones))
    logging.debug("%s", listaconexiones)

def Puente(matrizG, conn, listaConexiones, condicionturno, numjugador):
    global turno_actual
    while True:
        condicionturno.acquire()
        if turno_actual == numjugador:
            logging.debug("Es turno del jugador%d", (turno_actual+1))
            conn.send(str(turno_actual).encode(encoding="ascii", errors="ignore"))
            llenado_primera_matriz_cliente(matrizG, conn, listaConexiones, condicionturno,numjugador)
            print("Ya me sali y liberare el candado\n")
            turno_actual=(turno_actual+1) % NUM_THREADS
            condicionturno.release()
            print("Libere el candado\n")
            print(turno_actual)
        else:
            condicionturno.release()

def recibir_datos(conn, addr,ide, matrizG, condicionturno, numjugador):
    try:
        cur_thread = threading.current_thread()
        print("Recibiendo datos del cliente {} en el {}".format(addr, cur_thread.name))
        while True:
            if dificultad=='P':
                conn.send(dificultad.encode(encoding="ascii", errors="ignore"))
                conn.send(str(ide).encode(encoding="ascii", errors="ignore"))
                if(ide==0):
                    logging.debug("Se ha elegido la dificultad Principiante\n")
                    imprimir_matrizP(matrizG)
                    Puente(matrizG, conn, listaConexiones, condicionturno, numjugador)
                else:
                    Puente(matrizG, conn, listaConexiones, condicionturno, numjugador)
                return
            elif dificultad=='A':
                conn.send(dificultad.encode(encoding="ascii", errors="ignore"))
                conn.send(str(ide).encode(encoding="ascii", errors="ignore"))
                if(ide==0):
                    logging.debug("Se ha elegido la dificultad Avanzado\n")
                    imprimir_matrizA(matrizG)
                    llenado_primera_matriz_clienteA(matrizG, conn, listaConexiones)
                    return
                else:
                    llenado_primera_matriz_clienteA(matrizG, conn, listaConexiones)
                    break
            return
    except Exception as e:
        logging.debug(e)
    finally:
        conn.close()

def imprimir_matrizP(matriz):
    contador=0
    print("- 0 1 2 3\n")
    for i in range(4):
        print(contador,"", end = '')
        contador+=1
        for j in range(4):
            print((matriz[i][j]),"", end='')
        print("\t")

def imprimir_matrizA(matriz):
    contador=0
    print("- 0      1       2       3       4       5\n")
    for i in range(6):
        print(contador,"", end = '')
        contador+=1
        for j in range(6):
            print((matriz[i][j]),"\t", end='')
        print("\t")

def revolvermatrizP(variable, matriz2):
    while variable==3:
        random.shuffle(matriz2)
        for i in matriz2:
            random.shuffle(i)
        return matriz2

def revolvermatrizA(variable, matriz4):
    while variable==5:
        random.shuffle(matriz4)
        for i in matriz4:
            random.shuffle(i)
        return matriz4

def llenado_primera_matriz_cliente(matrizrv, Client_conn, lista,t,n):
    x = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#6 RECV
    y = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#6 RECV
    es_letra= matriz_guionP[x][y].isalpha()
    if es_letra == False:
        logging.debug("El jugador ha elegido la celda (%d,%d), CELDA NO OCUPADA", x,y)
        matriz_guionP[x][y]=matrizrv[x][y]
        valor = matriz_guionP[x][y]
        v1 = str(valor)
        Client_conn.send(v1.encode(encoding="ascii", errors="ignore"))
        imprimir_matrizP(matriz_guionP)
        llenado_segunda_matriz_cliente(matrizrv, Client_conn, lista, x,y,t,n)
        print("Ya me sali\n")
        return
    elif es_letra == True:
        Client_conn.send(b'2')
        logging.debug("La primera coordenada digitada por el usuario es una casilla que esta ocupada\n")
        llenado_primera_matriz_cliente(matrizrv,Client_conn,lista,t,n)
    return

def llenado_primera_matriz_clienteA(matrizrv, Client_conn, lista):
    imprimir_matrizA(matriz_guionA)
    x = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#6 RECV
    y = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#6 RECV
    es_letraA= matriz_guionA[x][y].isalpha()
    if es_letraA == False:
        logging.debug("El jugador ha elegido la celda (%d,%d), CELDA NO OCUPADA", x,y)
        matriz_guionA[x][y]=matrizrv[x][y]
        valor = matriz_guionA[x][y]
        v1 = str(valor)
        Client_conn.send(v1.encode(encoding="ascii", errors="ignore"))
        imprimir_matrizA(matriz_guionA)
        llenado_segunda_matriz_clienteA(matrizrv, Client_conn, lista, x,y)
        return
    elif es_letraA == True:
        Client_conn.send(b'2')
        logging.debug("La primera coordenada digitada por el usuario es una casilla que esta ocupada\n")
        llenado_primera_matriz_clienteA(matrizrv,Client_conn,lista)
    return

def llenado_segunda_matriz_cliente(matrizrv, Client_conn, lista,x,y,t,n):
    x1 = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#7 RECV
    y1 = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#7 RECV
    es_letra=matriz_guionP[x1][y1].isalpha()
    if es_letra==False:
        logging.debug("El jugador ha elegido la celda (%d,%d), CELDA NO OCUPADA", x1,y1)
        matriz_guionP[x1][y1]=matrizrv[x1][y1]
        valor2 = matriz_guionP[x1][y1]
        v2 = str(valor2)
        Client_conn.send(v2.encode(encoding="ascii", errors="ignore"))
        imprimir_matrizP(matriz_guionP)
        condiciones(x,y,x1,y1, matrizrv, Client_conn, lista,t,n)
        print("Ya me sali 1\n")
        return
    elif es_letra == True:
        Client_conn.send(b'3')
        logging.debug("La segunda coordenada digitada por el usuario es una casilla que esta ocupada\n")
        llenado_segunda_matriz_cliente(matrizrv,Client_conn,lista,x,y,t,n)
    return

def llenado_segunda_matriz_clienteA(matrizrv, Client_conn, lista,x,y):
    x1 = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#7 RECV
    y1 = int((Client_conn.recv(buffer_size)).decode(encoding="ascii", errors="ignore"))#7 RECV
    es_letraA=matriz_guionA[x1][y1].isalpha()
    if es_letraA==False:
        logging.debug("El jugador ha elegido la celda (%d,%d), CELDA NO OCUPADA", x1,y1)
        matriz_guionA[x1][y1]=matrizrv[x1][y1]
        valor2 = matriz_guionA[x1][y1]
        v2 = str(valor2)
        Client_conn.send(v2.encode(encoding="ascii", errors="ignore"))
        imprimir_matrizA(matriz_guionA)
        condicionesA(x,y,x1,y1, matrizrv, Client_conn, lista)
        return
    elif es_letraA== True:
        Client_conn.send(b'3')
        logging.debug("La segunda coordenada digitada por el usuario es una casilla que esta ocupada\n")
        llenado_segunda_matriz_clienteA(matrizrv,Client_conn,lista,x,y)
    return

def condiciones(x,y,x2,y2, matrizrv, Client_conn, lista,t,n):
    global contador_pares, matriz_guionP
    f=1
    while f!=0:
        while matriz_guionP[x][y] != matriz_guionP[x2][y2]:
            matriz_guionP[x2][y2] = "-"
            matriz_guionP[x][y] = "-"
            logging.debug("El jugador no le ha atinado. Volvera a intentarlo\n")
            Client_conn.send(b'0')
            imprimir_matrizP(matriz_guionP)
            f=0
            return
        while matriz_guionP[x][y] == matriz_guionP[x2][y2]:
            Client_conn.send(b'1')
            logging.debug("El jugador le ha atinado. Volvera a intentarlo\n")
            contador_pares=contador_pares+1
            imprimir_matrizP(matriz_guionP)
            if contador_pares==8:
                logging.debug("Se acabo el juego\n")
                Client_conn.send(b'9')
                return
            elif contador_pares!=8:
                logging.debug("Sigue el juego\n")
                Client_conn.send(b'8')
                llenado_primera_matriz_cliente(matrizrv, Client_conn,lista,t,n)
            return
        return
    return

def condicionesA(x,y,x2,y2, matrizrv, Client_conn, lista):
    global contador_pares
    f=1
    while f!=0:
        while matriz_guionA[x][y] != matriz_guionA[x2][y2]:
            matriz_guionA[x2][y2] = "-"
            matriz_guionA[x][y] = "-"
            logging.debug("El jugador no le ha atinado. Volvera a intentarlo\n")
            Client_conn.send(b'0')
            imprimir_matrizA(matriz_guionA)
            llenado_primera_matriz_clienteA(matrizrv, Client_conn,lista)
        while matriz_guionA[x][y] == matriz_guionA[x2][y2]:
            Client_conn.send(b'1')
            logging.debug("El jugador le ha atinado. Volvera a intentarlo\n")
            contador_pares=contador_pares+1
            imprimir_matrizA(matriz_guionA)
            if contador_pares==8:
                logging.debug("Se acabo el juego\n")
                Client_conn.send(b'9')
                return
            elif contador_pares!=8:
                logging.debug("Sigue el juego\n")
                Client_conn.send(b'8')
                llenado_primera_matriz_clienteA(matrizrv, Client_conn,lista)
            return
        return
    return

logging.basicConfig(level=logging.DEBUG, format='%(message)s',)

listaConexiones = []
host, port, numConn, dificultad = sys.argv[1:5]
buffer_size=1024

if len(sys.argv) != 5:
    print("usage:", sys.argv[0], "<host> <port> <num_connections> <dificultad>")
    sys.exit(1)

serveraddr = (host, int(port))
matriz_memoP=[['A','B','C','D'],['E','F','G','H'],['A','B','C','D'],['E','F','G','H']]
matriz_memoA=[['A','B','C','D','E','F'],['G','H','I','J','K','L'],['M','N','O','P','Q','R'],['A','B','C','D','E','F'],['G','H','I','J','K','L'],['M','N','O','P','Q','R']]
turnos=[]

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as TCPServerSocket:
    TCPServerSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    TCPServerSocket.bind(serveraddr)
    TCPServerSocket.listen(int(numConn))
    logging.debug("El servidor TCP está disponible y en espera de solicitudes")
    NUM_THREADS = int(numConn)
    condicionTurno=threading.Lock()
    barrier = threading.Barrier(NUM_THREADS)
    if(dificultad=='P'):
        matriz_rvP=revolvermatrizP(3,matriz_memoP)
        threads = [threading.Thread(name='Jugador-%s' % (i+1), target=servirPorSiempre, args=(TCPServerSocket,listaConexiones,barrier,matriz_rvP, condicionTurno,i), )
           for i in range(NUM_THREADS)]
    elif(dificultad=='A'):
        lockA=threading.Lock()
        matriz_rvA=revolvermatrizA(5,matriz_memoA)
        threads = [threading.Thread(name='Jugador-%s' % (i+1), target=servirPorSiempre, args=(TCPServerSocket,listaConexiones,barrier,matriz_rvA, condicionTurno), )
           for i in range(NUM_THREADS)]

    for t in threads:
        logging.debug('%s\tEn espera...', t.name)
        t.start()
        time.sleep(0.1)

    for t in threads:
        t.join()
        #servirPorSiempre(TCPServerSocket, listaConexiones)