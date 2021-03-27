package org.cfm.solitaire.models;

import org.cfm.solitaire.enums.Palos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jugador {

    public boolean movimientoPosible(Mesa mesa, Carta carta) {

        boolean movimiento1 = false;
        boolean movimiento = false;

        Stack<Carta>[][] mesaInterior = mesa.getMontonInterior();
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        List<Carta> cartasInterior = new ArrayList<>();
        List<Carta> cartasExterior = new ArrayList<>();
        List<Carta> cartasBastos = new ArrayList<>();
        List<Carta> cartasCopas = new ArrayList<>();
        List<Carta> cartasEspadas = new ArrayList<>();
        List<Carta> cartasOros = new ArrayList<>();


        for (Stack<Carta> cartaExterior : mesaExterior) {
            cartasExterior.add(cartaExterior.lastElement());
        }

        buscar:
        for (Stack<Carta>[] juego : mesaInterior) {
            for (Stack<Carta> cartas : juego) {
                if (cartas.size() == 0) {   //Verificar si la pila está vacía
                    movimiento1 = true;
                    break buscar;
                } else {
                    cartasInterior.add(cartas.lastElement());
                }
            }
        }

        salir:
        if (!movimiento1) {
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

            int min = 0;
            for (int i = 1; i < cartasBastos.size(); i++) { //Verificar si una carta del interior puede ir al exterior
                min = (cartasBastos.get(min).getNumeroCarta() < cartasBastos.get(i).getNumeroCarta()) ? min : i;
                if (min == cartasExterior.get(0).getNumeroCarta() + 1) {
                    movimiento = true;
                    break salir;
                }
            }
            min = 0;
            for (int i = 1; i < cartasCopas.size(); i++) {
                min = (cartasCopas.get(min).getNumeroCarta() < cartasCopas.get(i).getNumeroCarta()) ? min : i;
                if (min == cartasExterior.get(1).getNumeroCarta() + 1) {
                    movimiento = true;
                    break salir;
                }
            }
            min = 0;
            for (int i = 1; i < cartasEspadas.size(); i++) {
                min = (cartasEspadas.get(min).getNumeroCarta() < cartasEspadas.get(i).getNumeroCarta()) ? min : i;
                if (min == cartasExterior.get(2).getNumeroCarta() + 1) {
                    movimiento = true;
                    break salir;
                }
            }
            min = 0;
            for (int i = 1; i < cartasOros.size(); i++) {
                min = (cartasOros.get(min).getNumeroCarta() < cartasOros.get(i).getNumeroCarta()) ? min : i;
                if (min == cartasExterior.get(3).getNumeroCarta() + 1) {
                    movimiento = true;
                    break salir;
                }
            }
        }

        //Verificar movimientos en el monto interior entre cartas
        if(!movimiento && !movimiento1) {
            if(cartasBastos.size() > 1) {
                for(int i = 0; i < cartasBastos.size(); i++) {
                    if(i < cartasBastos.size()-1){
                        int numero1 = cartasBastos.get(i).getNumeroCarta();
                        int numero2 = cartasBastos.get(i+1).getNumeroCarta();
                        if(Math.abs(numero1-numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
            if(cartasCopas.size() > 1) {
                for(int i = 0; i < cartasCopas.size(); i++) {
                    if(i < cartasBastos.size()-1){
                        int numero1 = cartasCopas.get(i).getNumeroCarta();
                        int numero2 = cartasCopas.get(i+1).getNumeroCarta();
                        if(Math.abs(numero1-numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
            if(cartasEspadas.size() > 1) {
                for(int i = 0; i < cartasEspadas.size(); i++) {
                    if(i < cartasEspadas.size()-1){
                        int numero1 = cartasEspadas.get(i).getNumeroCarta();
                        int numero2 = cartasEspadas.get(i+1).getNumeroCarta();
                        if(Math.abs(numero1-numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
            if(cartasOros.size() > 1) {
                for(int i = 0; i < cartasOros.size(); i++) {
                    if(i < cartasEspadas.size()-1){
                        int numero1 = cartasOros.get(i).getNumeroCarta();
                        int numero2 = cartasOros.get(i+1).getNumeroCarta();
                        if(Math.abs(numero1-numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }

        }
        return (movimiento1 || movimiento);
    }
}
