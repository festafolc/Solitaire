package org.cfm.solitaire;

import org.cfm.solitaire.models.Baraja;
import org.cfm.solitaire.models.Carta;
import org.cfm.solitaire.models.Jugador;
import org.cfm.solitaire.models.Mesa;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Carta cartaClass = new Carta();
        Baraja barajaClass = new Baraja();
        Mesa mesaClass = new Mesa();
        Jugador jugadorClass = new Jugador();
        Scanner sc = new Scanner(System.in);

        mesaClass.crearMesa(barajaClass);
        mesaClass.mostrarMesa();

        while(jugadorClass.movimientoPosible(mesaClass)) {
            System.out.println("Pop and Push[1], Peek[2]");
            int mover = sc.nextInt();
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            switch (mover) {
                case 1:
                    Carta cartaPop = cartaClass.seleccionarMonton(mesaClass, fila, columna).lastElement();
                    fila = sc.nextInt();
                    columna = sc.nextInt();
                    cartaClass.seleccionarMonton(mesaClass, fila, columna).push(cartaPop);
                case 2:
                    Carta cartaPeek = cartaClass.seleccionarMonton(mesaClass, fila, columna).lastElement();
                default:
                    System.out.println("Seleccione una opción válida");
            }
        }

        System.out.println("fin");
    }

}
