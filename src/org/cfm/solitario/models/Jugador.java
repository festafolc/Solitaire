package org.cfm.solitario.models;

import org.cfm.solitario.enums.Palos;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Jugador {

    private List<Palos> encontrarPalo(Mesa mesa) {
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        List<Palos> palos = new ArrayList<>();
        for (Stack<Carta> palo : mesaExterior) {
            if (palo.lastElement().getPalo() != null) {
                palos.add(palo.lastElement().getPalo());
            }
        }
        return palos;
    }

    private boolean montonInteriorExterior(List<Carta> lista, Mesa mesa, boolean pilaVacia, Palos palo) {
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        boolean mover = false;

        for (int i = 1; i < lista.size(); i++) {
            int min = Math.min(lista.get(i - 1).getNumeroCarta(), lista.get(i).getNumeroCarta());
            if (min == 1) {
                mover = true;
                break;
            }
            if (pilaVacia && (lista.get(i).getNumeroCarta() == 12)) {
                mover = true;
                break;
            }
            try {
                if (mesaExterior[encontrarPalo(mesa).indexOf(palo)].size() > 0) {
                    if (min == mesaExterior[encontrarPalo(mesa).indexOf(palo)].lastElement().getNumeroCarta() + 1) {
                        mover = true;
                        break;
                    }
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        return mover;
    }

    private boolean montonInterior(List<Carta> lista) {
        boolean mover = false;
        if(lista.size() > 1) {
            for (int i = 0; i < lista.size() -1; i++) {
                int numero1 = lista.get(i).getNumeroCarta();
                for(int j = i + 1; j < lista.size() - 2; j++) {
                    int numero2 = lista.get(j).getNumeroCarta();
                    if (Math.abs(numero1 - numero2) == 1) {
                        mover = true;
                        break;
                    }
                }
            }
        }
        return mover;
    }

    private boolean carta7y10(List<Carta> lista) {
        boolean mover = false;
        boolean numero7 = false;
        boolean numero10 = false;
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNumeroCarta() == 7) {
                numero7= true;
            }
            if(lista.get(i).getNumeroCarta() == 10) {
                numero10= true;
            }
            if(numero7 && numero10) {
                mover = true;
                break;
            }
        }
        return mover;
    }

    public boolean movimientoPosible(Mesa mesa) {

        boolean movimiento = false;
        boolean pilaVacia = false;

        Stack<Carta>[][] mesaInterior = mesa.getMontonInterior();

        List<Carta> cartasInterior = new ArrayList<>();
        List<Carta> cartasBastos = new ArrayList<>();
        List<Carta> cartasCopas = new ArrayList<>();
        List<Carta> cartasEspadas = new ArrayList<>();
        List<Carta> cartasOros = new ArrayList<>();

        for (Stack<Carta>[] juego : mesaInterior) {
            for (Stack<Carta> cartas : juego) {
                if (cartas.size() == 0) {   //Verificar si la pila está vacía
                    pilaVacia = true;
                } else {
                    cartasInterior.add(cartas.lastElement());
                }
            }
        }


        for (Carta cartaEncima : cartasInterior) {
            if (cartaEncima.getPalo() == Palos.BASTOS) {
                cartasBastos.add(cartaEncima);
            }
            if (cartaEncima.getPalo() == Palos.COPAS) {
                cartasCopas.add(cartaEncima);
            }
            if (cartaEncima.getPalo() == Palos.ESPADAS) {
                cartasEspadas.add(cartaEncima);
            }
            if (cartaEncima.getPalo() == Palos.OROS) {
                cartasOros.add(cartaEncima);
            }
        }

        //Verificar si una carta del interior puede ir al exterior
        if(montonInteriorExterior(cartasBastos, mesa, pilaVacia, Palos.BASTOS)) {
            movimiento = true;
        }
        if(montonInteriorExterior(cartasCopas, mesa, pilaVacia, Palos.COPAS)) {
            movimiento = true;
        }
        if(montonInteriorExterior(cartasEspadas, mesa, pilaVacia, Palos.ESPADAS)) {
            movimiento = true;
        }
        if(montonInteriorExterior(cartasOros, mesa, pilaVacia, Palos.OROS)) {
            movimiento = true;
        }

        //Verificar movimientos en el monto interior entre cartas
        if(!movimiento) {
            if(montonInterior(cartasBastos)) {
                movimiento = true;
            }
            if(montonInterior(cartasCopas)) {
                movimiento = true;
            }
            if(montonInterior(cartasEspadas)) {
                movimiento = true;
            }
            if(montonInterior(cartasOros)) {
                movimiento = true;
            }
        }

        //Verificar movimientos entre los 7 y los 10 del mismo palo
        if(!movimiento) {
            if(carta7y10(cartasBastos)) {
                movimiento = true;
            }
            if(carta7y10(cartasCopas)) {
                movimiento = true;
            }
            if(carta7y10(cartasEspadas)) {
                movimiento = true;
            }
            if(carta7y10(cartasOros)) {
                movimiento = true;
            }
        }
        return (movimiento);
    }

    public boolean comprobarMovimiento(Stack<Carta> monto1, Stack<Carta> monto2) {

        boolean mover = false;

        if (monto2.size() == 0) {
            if (monto1.lastElement().getNumeroCarta() == 1) {
                mover = true;
            }
            if (monto1.lastElement().getNumeroCarta() == 12) {
                mover = true;
            }
        }

        if (monto2.size() > 0) {
            if (monto1.lastElement().getPalo() == monto2.lastElement().getPalo()) {
                if (monto1.lastElement().getNumeroCarta() == monto2.lastElement().getNumeroCarta() + 1) {
                    mover = true;
                } else if (monto1.lastElement().getNumeroCarta() == monto2.lastElement().getNumeroCarta() - 1) {
                    mover = true;
                } else if ((monto1.lastElement().getNumeroCarta() == 7) && (monto2.lastElement().getNumeroCarta() == 10)){
                    mover = true;
                } else {
                    mover = false;
                }
            }
        }
        return mover;
    }

    public boolean finJuego(Mesa mesa) {
        boolean fin = false;
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        int cartasTotal = 0;
        for(Stack<Carta> stack : mesaExterior) {
            if(stack.size() == 10) {
                cartasTotal+=10;
            }
        }
        if(cartasTotal == 40) {
            fin = true;
        }
        return fin;
    }
}
