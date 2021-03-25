package org.cfm.solitaire.models;

import java.util.Stack;

public class Mesa {

    private Stack<Carta> [][] montonInterior;
    private Stack<Carta> [] montonExterior;

    public Mesa() {
        montonInterior = new Stack[4][4];
        montonExterior = new Stack[4];
    }

    public Stack<Carta>[][] getMontonInterior() {
        return montonInterior;
    }

    public void setMontonInterior(Stack<Carta>[][] montonInterior) {
        this.montonInterior = montonInterior;
    }

    public Stack<Carta>[] getMontonExterior() {
        return montonExterior;
    }

    public void setMontonExterior(Stack<Carta>[] montonExterior) {
        this.montonExterior = montonExterior;
    }

    public void crearMesa() {
        Mesa mesa = new Mesa();

        mesa.getMontonInterior();
    }

}
