# AD1-Proyecto-1
Proyecto 1 de Algoritmos y Estructuras de Datos 1. Juego Dots multijugador usando Java y Sockets
# Dots
  Dots es un juego implemantado en java, consiste en que dos jugadores por medio de sockets, se concetan a un servidor,
haciendo una petición, para después entrar en una cola. Cuando la cola del servidor tiene dos juagadores, empieza el juego. 

  Los clientes tendran que poner puntos en una malla de tamaño variable, cada punto se podra poner en el turno que le 
corresponda a cada jugador. EL objetivo del juego es unir puentos por medio de líneas generadas automáticamente en puntos 
adyacentes y con estas formar figuras geométricas, que, dependiendo el tamaño y la forma, se puntuara a cada jugador, además, 
el área dentro de la figura no se puede utilizar para hacer más figuras.

  Los clientes le pediran información a el servidor cada "n" segundos, la información se enviará en forma de String, en Gson, 
el cual es la única forma de comunicación entre el serivodr y el cliente.
