def turno_cliente():
        imprimir()
        turno = (TCPClientSocket.recv(1)).decode(encoding="ascii", errors="ignore")
        print("Turno del jugador %d. Esperen los demas jugadores, no introduzcan nada")
             

        pedir_primera_coordenada()
        return  

def turno_clienteA():
        imprimir2()
        pedir_primera_coordenadaA()
        return

def imprimir():
    contador=0
    print("- 0 1 2 3 \n")
    for i in range(4):
        print(contador,"", end = '')
        contador+=1
        for j in range(4):
            print((matriz[i][j]),"", end='')
        print("\t")
def imprimir2():
    contador=0
    print("- 0       1       2       3       4      5 \n") 
    for i in range(6):
        print(contador,"", end = '')
        contador+=1
        for j in range(6):
            print((matriza[i][j]),"\t", end='')
        print("\t")

def pedir_primera_coordenada():
    x = int(input("Ingresa la fila donde quieras tirar"))
    while x > 3:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        x = int(input ("Ingresa la fila donde quieras tirar"))
    y = int(input("Ingresa la columna donde quieras tirar"))
    while y > 3:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        y = int(input("Ingresa la columna donde quieras tirar"))
    a = str(x)
    b = str(y)
    TCPClientSocket.send(a.encode(encoding="ascii", errors="ignore"))#6 SENN    
    TCPClientSocket.send(b.encode(encoding="ascii", errors="ignore"))#6 SEND
    v1 =(TCPClientSocket.recv(1)).decode(encoding="ascii", errors="ignore")
    while v1 != '2':
        matriz[x][y] = v1
        imprimir()
        pedir_segunda_coordenada(x,y)
        return
    while v1 == '2':
         print("LA COORDENADA QUE HA PUESTO, YA ESTA OCUPADA\n")
         pedir_primera_coordenada()
    return

def pedir_primera_coordenadaA():
    x = int(input("Ingresa la fila donde quieras tirar"))
    while x > 5:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        x = int(input ("Ingresa la fila donde quieras tirar"))
    y = int(input("Ingresa la columna donde quieras tirar"))
    while y > 5:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        y = int(input("Ingresa la columna donde quieras tirar"))
    a1 = str(x)
    b1= str(y)
    TCPClientSocket.send(a1.encode(encoding="ascii", errors="ignore"))#6 SENN    
    TCPClientSocket.send(b1.encode(encoding="ascii", errors="ignore"))#6 SEND
    v1 =(TCPClientSocket.recv(1)).decode(encoding="ascii", errors="ignore")
    while v1 != '2':
        matriza[x][y] = v1
        imprimir2()
        pedir_segunda_coordenadaA(x,y)
        return
    while v1 == '2':
         print("LA COORDENADA QUE HA PUESTO, YA ESTA OCUPADA\n")
         pedir_primera_coordenadaA()

def pedir_segunda_coordenada(x,y):    
    x2 = int(input ("Ingresa la fila donde quieras tirar"))
    while x2 > 3:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        x2 = int(input ("Ingresa la fila donde quieras tirar"))
    y2 = int(input("Ingresa la columna donde quieras tirar"))
    while y2 > 3:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        y2 = int(input("Ingresa la columna donde quieras tirar"))
    a2 = str(x2)
    b2 = str(y2)
    TCPClientSocket.send(a2.encode(encoding="ascii", errors="ignore"))#7 SEND
    TCPClientSocket.send(b2.encode(encoding="ascii", errors="ignore"))#7 SEND
    v2 =(TCPClientSocket.recv(1)).decode(encoding="ascii", errors="ignore")
    while v2 != '3':
        matriz[x2][y2] = v2
        imprimir()
        validar(x,y,x2,y2)
        return
    while v2 == '3':
        print("LA COORDENADA QUE HA PUESTO, YA ESTA OCUPADA\n")
        pedir_segunda_coordenada(x,y)
    return    

def pedir_segunda_coordenadaA(x,y):    
    x2 = int(input ("Ingresa la fila donde quieras tirar"))
    while x2 > 5:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        x2 = int(input ("Ingresa la fila donde quieras tirar"))
    y2 = int(input("Ingresa la columna donde quieras tirar"))
    while y2 > 5:
        print("ERROR COORDENADA INVALIDA. Ingresa bien tu coordenada")
        y2 = int(input("Ingresa la columna donde quieras tirar"))
    a3 = str(x2)
    b3 = str(y2)
    TCPClientSocket.send(a3.encode(encoding="ascii", errors="ignore"))#7 SEND
    TCPClientSocket.send(b3.encode(encoding="ascii", errors="ignore"))#7 SEND
    v2 =(TCPClientSocket.recv(1)).decode(encoding="ascii", errors="ignore")
    while v2 != '3':
        matriza[x2][y2] = v2
        imprimir2()
        validar2(x,y,x2,y2)
        return
    while v2 == '3':
        print("LA COORDENADA QUE HA PUESTO, YA ESTA OCUPADA\n")
        pedir_segunda_coordenadaA(x,y)        
    

def validar(x,y,x2,y2):
        while matriz[x][y] != matriz[x2][y2]:
            matriz[x2][y2] = "-"
            matriz[x][y] = "-"
            #imprimir()
        respuesta=TCPClientSocket.recv(1).decode(encoding="ascii", errors="ignore")
        while respuesta == '0':
                print("No le atinaste jugador", (dato2+1), "vuelvelo a intentar\n")
                turno_cliente()
        while respuesta == '1':
                global pares
                print("Le has atinado jugador", (dato2+1), "Sigue asi\n")
                pares = pares+1
                print(pares)
                salida = TCPClientSocket.recv(1).decode(encoding="ascii", errors="ignore")
                if salida == '9':
                     print("Se acabo el juego. Jugador",(dato2+1),"\n")
                     print("Obtuviste", pares, "pares\n")
                     return
                if salida == '8':
                    turno_cliente()
                return  
        return       

def validar2(x,y,x2,y2):
        while matriza[x][y] != matriza[x2][y2]:
            matriza[x2][y2] = "-"
            matriza[x][y] = "-"
            #imprimir()
        respuesta=TCPClientSocket.recv(1).decode(encoding="ascii", errors="ignore")
        while respuesta == '0':
                print("No le atinaste judador", (dato2+1), "vuelvelo a intentar\n")
                turno_clienteA()
        while respuesta == '1':
                global pares
                print("Le has atinado jugador", (dato2+1), "Sigue asi\n")
                pares = pares+1
                print(pares)
                salida = TCPClientSocket.recv(1).decode(encoding="ascii", errors="ignore")
                if salida == '9':
                     print("Se acabo el juego. Jugador",(dato2+1),"\n")
                     print("Obtuviste", pares, "pares\n")
                     return
                if salida == '8':
                    turno_clienteA() 

import socket

#HOST = "127.0.0.1"   
global pares
pares=0 
buffer_size = 1024
HOST=input("Ingrese la IP del servidor\n") # Le pedimos al cliente el hostname o  dirección IP del servidor
PORT = int(input("Ingrese el puerto\n"))#Le pedimos al cliente que ingrese el puerto
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as TCPClientSocket:
    TCPClientSocket.connect((HOST, PORT))
    print ("Se inicia comunicacion en Cliente")
    while True: 
        dato1=(TCPClientSocket.recv(1).decode(encoding="ascii", errors="ignore"))
        dato2=int(TCPClientSocket.recv(1).decode(encoding="ascii", errors="ignore"))
        print("Eres el jugador",(dato2+1)) 
        if dato1 == "P":
            print("El servidor ha puesto la dificultad Principiante\n")
            matriz = [['-','-','-','-'],['-','-','-','-'],['-','-','-','-'],['-','-','-','-']]
            turno_cliente()
            break
        if dato1 == "A":
            print("El servidor ha puesto la dificultad Avanzado\n")
            matriza = [['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-'],['-','-','-','-','-','-']]
            turno_clienteA()
            break
        break