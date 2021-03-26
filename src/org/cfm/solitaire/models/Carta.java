package org.cfm.solitaire.models;

import org.cfm.solitaire.enums.Palos;

public class Carta {

    private Palos palo;
    private int numeroCarta;

    public Carta() {
    }

    public Carta(Palos palo, int numeroCarta) {
        this.palo = palo;
        this.numeroCarta = numeroCarta;
    }

    public int getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(int numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public Palos getPalo() {
        return palo;
    }

    public void setPalo(Palos palo) {
        this.palo = palo;
    }

    public Carta comprobarCarta (Palos palo, int numeroCarta) {
        Carta carta = new Carta(palo, numeroCarta);
        return carta;
    }

    @Override
    public String toString() {
        return palo + " " + numeroCarta;
    }
}
