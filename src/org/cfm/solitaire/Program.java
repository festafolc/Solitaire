package org.cfm.solitaire;

import org.cfm.solitaire.models.Baraja;
import org.cfm.solitaire.models.Carta;
import org.cfm.solitaire.models.Mesa;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        Baraja barajaClass = new Baraja();

        List<Carta> baraja = barajaClass.crearBaraja();

        Mesa mesa = new Mesa();

        mesa.crearMesa(baraja);
        mesa.mostrarMesa();

    }

}
