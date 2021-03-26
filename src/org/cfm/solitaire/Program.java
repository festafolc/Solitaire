package org.cfm.solitaire;

import org.cfm.solitaire.models.Baraja;
import org.cfm.solitaire.models.Mesa;

public class Program {
    public static void main(String[] args) {

        Baraja barajaClass = new Baraja();

        Mesa mesa = new Mesa();

        mesa.crearMesa(barajaClass);
        mesa.mostrarMesa();

    }

}
