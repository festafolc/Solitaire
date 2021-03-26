package org.cfm.solitaire.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jugador {

    public boolean movimientoPosible(Mesa mesa, Carta carta) {

        boolean movimiento1 = false;
        boolean movimiento;

        Stack<Carta> mesaJuego[][] = mesa.getMontonInterior();
        List<Carta> cartasEncima = new ArrayList<>();

        buscar:
        for (int i = 0; i < mesaJuego.length; i++) {
            for (int j = 0; j < mesaJuego[i].length; j++) {
                if (mesaJuego[i][j].size() == 0) {
                    movimiento1 = true;
                    break buscar;
                }
                cartasEncima.add(mesaJuego[i][j].lastElement());
            }
        }
        movimiento = cartasEncima.contains(carta.comprobarCarta(carta.getPalo(), carta.getNumeroCarta() - 1));
        return (movimiento1 || movimiento);
    }
}
