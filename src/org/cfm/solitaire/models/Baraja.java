package org.cfm.solitaire.models;

import org.cfm.solitaire.enums.Palos;

import java.util.ArrayList;
import java.util.List;

public class Baraja {

    public void crearBaraja() {
        List<Palos> palos = new ArrayList<>();
        palos.add(Palos.OROS);
        palos.add(Palos.BASTOS);
        palos.add(Palos.COPAS);
        palos.add(Palos.ESPADAS);

        List<Carta> baraja = new ArrayList<>();

        for (Palos palo : palos) {
            for (int i = 1; i <= 12; i++) {
                if(i != 8 && i != 9) {
                    baraja.add(new Carta(palo, i));
                }
            }
        }

    }
}
