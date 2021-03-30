package org.cfm.solitaire.models;

import org.cfm.solitaire.enums.Palos;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Jugador {

    public List<Palos> encontrarPalo(Mesa mesa) {
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        List<Palos> palos = new ArrayList<>();
        for (Stack<Carta> palo : mesaExterior) {
            if (palo.lastElement().getPalo() != null) {
                palos.add(palo.lastElement().getPalo());
            }
        }
        return palos;
    }

    public boolean movimientoPosible(Mesa mesa) {

        boolean movimiento = false;
        boolean pilaVacia = false;

        Stack<Carta>[][] mesaInterior = mesa.getMontonInterior();
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
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

        for (int i = 1; i < cartasBastos.size(); i++) {
            int min = Math.min(cartasBastos.get(i - 1).getNumeroCarta(), cartasBastos.get(i).getNumeroCarta());
            if (min == 1) {
                movimiento = true;
                break;
            }
            if (pilaVacia && (cartasBastos.get(i).getNumeroCarta() == 12)) {
                movimiento = true;
                break;
            }
            try {
                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.BASTOS)].size() > 0) {
                    if (min == mesaExterior[encontrarPalo(mesa).indexOf(Palos.BASTOS)].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break;
                    }
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        for (int i = 1; i < cartasCopas.size(); i++) {
            int min = Math.min(cartasCopas.get(i - 1).getNumeroCarta(), cartasCopas.get(i).getNumeroCarta());
            if (min == 1) {
                movimiento = true;
                break;
            }
            if (pilaVacia && (cartasCopas.get(i).getNumeroCarta() == 12)) {
                movimiento = true;
                break;
            }
            try {
                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.COPAS)].size() > 0) {
                    if (min == mesaExterior[encontrarPalo(mesa).indexOf(Palos.COPAS)].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break;
                    }
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        for (int i = 1; i < cartasEspadas.size(); i++) {
            int min = Math.min(cartasEspadas.get(i - 1).getNumeroCarta(), cartasEspadas.get(i).getNumeroCarta());
            if (min == 1) {
                movimiento = true;
                break;
            }
            if (pilaVacia && (cartasEspadas.get(i).getNumeroCarta() == 12)) {
                movimiento = true;
                break;
            }
            try {
                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.ESPADAS)].size() > 0) {
                    if (min == mesaExterior[encontrarPalo(mesa).indexOf(Palos.ESPADAS)].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break;
                    }
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        for (int i = 1; i < cartasOros.size(); i++) {
            int min = Math.min(cartasOros.get(i - 1).getNumeroCarta(), cartasOros.get(i).getNumeroCarta());
            if (min == 1) {
                movimiento = true;
                break;
            }
            if (pilaVacia && (cartasOros.get(i).getNumeroCarta() == 12)) {
                movimiento = true;
                break;
            }
            try {
                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.OROS)].size() > 0) {
                    if (min == mesaExterior[encontrarPalo(mesa).indexOf(Palos.OROS)].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break;
                    }
                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }


        //Verificar movimientos en el monto interior entre cartas
        if (!movimiento) {
            if (cartasBastos.size() > 1) {
                for (int i = 0; i < cartasBastos.size(); i++) {
                    if (i < cartasBastos.size() - 1) {
                        int numero1 = cartasBastos.get(i).getNumeroCarta();
                        int numero2 = cartasBastos.get(i + 1).getNumeroCarta();
                        if (Math.abs(numero1 - numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
            if (cartasCopas.size() > 1) {
                for (int i = 0; i < cartasCopas.size(); i++) {
                    if (i < cartasCopas.size() - 1) {
                        int numero1 = cartasCopas.get(i).getNumeroCarta();
                        int numero2 = cartasCopas.get(i + 1).getNumeroCarta();
                        if (Math.abs(numero1 - numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
            if (cartasEspadas.size() > 1) {
                for (int i = 0; i < cartasEspadas.size(); i++) {
                    if (i < cartasEspadas.size() - 1) {
                        int numero1 = cartasEspadas.get(i).getNumeroCarta();
                        int numero2 = cartasEspadas.get(i + 1).getNumeroCarta();
                        if (Math.abs(numero1 - numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
            if (cartasOros.size() > 1) {
                for (int i = 0; i < cartasOros.size(); i++) {
                    if (i < cartasOros.size() - 1) {
                        int numero1 = cartasOros.get(i).getNumeroCarta();
                        int numero2 = cartasOros.get(i + 1).getNumeroCarta();
                        if (Math.abs(numero1 - numero2) == 1) {
                            movimiento = true;
                            break;
                        }
                    }
                }
            }
        }

        //Verificar movimientos entre los 7 y los 10 del mismo palo
        boolean numero7 = false;
        boolean numero10 = false;
        if(!movimiento) {
            for(int i = 1; i < cartasBastos.size(); i++) {
                if(cartasBastos.get(i).getNumeroCarta() == 7) {
                    numero7= true;
                }
                if(cartasBastos.get(i).getNumeroCarta() == 10) {
                    numero10= true;
                }
                if(numero7 && numero10) {
                    movimiento = true;
                    break;
                }
            }
            for(int i = 1; i < cartasCopas.size(); i++) {
                if(cartasCopas.get(i).getNumeroCarta() == 7) {
                    numero7= true;
                }
                if(cartasCopas.get(i).getNumeroCarta() == 10) {
                    numero10= true;
                }
                if(numero7 && numero10) {
                    movimiento = true;
                    break;
                }
            }
            for(int i = 1; i < cartasEspadas.size(); i++) {
                if(cartasEspadas.get(i).getNumeroCarta() == 7) {
                    numero7= true;
                }
                if(cartasEspadas.get(i).getNumeroCarta() == 10) {
                    numero10= true;
                }
                if(numero7 && numero10) {
                    movimiento = true;
                    break;
                }
            }
            for(int i = 1; i < cartasOros.size(); i++) {
                if(cartasOros.get(i).getNumeroCarta() == 7) {
                    numero7= true;
                }
                if(cartasOros.get(i).getNumeroCarta() == 10) {
                    numero10= true;
                }
                if(numero7 && numero10) {
                    movimiento = true;
                    break;
                }
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
}
