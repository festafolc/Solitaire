package org.cfm.solitaire;

import org.cfm.solitaire.enums.Palos;
import org.cfm.solitaire.models.Baraja;
import org.cfm.solitaire.models.Carta;
import org.cfm.solitaire.models.Jugador;
import org.cfm.solitaire.models.Mesa;

import java.util.Scanner;
import java.util.Stack;

public class Program {
    public static void main(String[] args) {

        Carta cartaClass = new Carta();
        Baraja barajaClass = new Baraja();
        Mesa mesaClass = new Mesa();
        Jugador jugadorClass = new Jugador();
        Scanner sc = new Scanner(System.in);

        mesaClass.crearMesa(barajaClass);

        System.out.println("     _______.  ______    __       __  .___________.    ___      .______       __    ______   \n" +
                "    /       | /  __  \\  |  |     |  | |           |   /   \\     |   _  \\     |  |  /  __  \\  \n" +
                "   |   (----`|  |  |  | |  |     |  | `---|  |----`  /  ^  \\    |  |_)  |    |  | |  |  |  | \n" +
                "    \\   \\    |  |  |  | |  |     |  |     |  |      /  /_\\  \\   |      /     |  | |  |  |  | \n" +
                ".----)   |   |  `--'  | |  `----.|  |     |  |     /  _____  \\  |  |\\  \\----.|  | |  `--'  | \n" +
                "|_______/     \\______/  |_______||__|     |__|    /__/     \\__\\ | _| `._____||__|  \\______/  \n" +
                "                                                                                             ");

        while (jugadorClass.movimientoPosible(mesaClass)) {
            mesaClass.mostrarMesa();
            System.out.print("Seleccione la primera carta: ");
            String[] carta = sc.next().split(",");
            int fila = Integer.parseInt(carta[0]);
            int columna = Integer.parseInt(carta[1]);
            Stack<Carta> monto1 = cartaClass.seleccionarMonton(mesaClass, fila, columna);
            System.out.print("Seleccione la segunda carta: ");
            carta = sc.next().split(",");
            fila = Integer.parseInt(carta[0]);
            columna = Integer.parseInt(carta[1]);
            Stack<Carta> monto2 = cartaClass.seleccionarMonton(mesaClass, fila, columna);
            if (jugadorClass.comprobarMovimiento(monto1, monto2)) {
                Carta cartaPop = monto1.pop();
                monto2.push(cartaPop);
            } else {
                System.out.println("Movimiento erróneo");
            }
        }

        if(jugadorClass.finJuego(mesaClass)) {
            System.out.println("¡Felicidades has resuelto el solitario!");
        }

    }
}
