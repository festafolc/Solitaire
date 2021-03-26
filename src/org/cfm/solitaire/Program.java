package org.cfm.solitaire;

import org.cfm.solitaire.models.Baraja;
import org.cfm.solitaire.models.Carta;
import org.cfm.solitaire.models.Jugador;
import org.cfm.solitaire.models.Mesa;

public class Program {
    public static void main(String[] args) {

        Carta cartaClass = new Carta();

        Baraja barajaClass = new Baraja();

        Mesa mesaClass = new Mesa();

        Jugador jugadorClass = new Jugador();

        mesaClass.crearMesa(barajaClass);
        mesaClass.mostrarMesa();

        jugadorClass.movimientoPosible(mesaClass, cartaClass);


    }

}
