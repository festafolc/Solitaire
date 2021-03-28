package org.cfm.solitaire.enums;

public enum Palos {
    OROS(3),
    BASTOS(0),
    COPAS(1),
    ESPADAS(2);

    int i;
    Palos(int i){
        this.i= i;
    }

    public int getI() {
        return i;
    }
}
