package org.cfm.solitaire.models;

import org.cfm.solitaire.enums.Palos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jugador {

/*    public Palos encontrarPalo(Mesa mesa, Palos palo) {
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();

        for (Stack<Carta> palos : mesaExterior) {
            if (palos.lastElement().getPalo().equals(Palos.BASTOS)) {
                palo = Palos.BASTOS;
                break;
            } else if (palos.lastElement().getPalo().equals(Palos.COPAS)) {
                palo = Palos.COPAS;
                break;
            } else if (palos.lastElement().getPalo().equals(Palos.ESPADAS)) {
                palo = Palos.ESPADAS;
                break;
            } else if (palos.lastElement().getPalo().equals(Palos.OROS)){
                palo = Palos.OROS;
                break;
            } else {
                palo = null;
            }
        }
        return palo;
    }*/

    public List<Palos> encontrarPalo(Mesa mesa) {
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        List<Palos> palos = new ArrayList<>();
        for(Stack<Carta> palo : mesaExterior) {
            palos.add(palo.lastElement().getPalo());
        }
        return palos;
    }

    public boolean movimientoPosible(Mesa mesa) {

        boolean movimiento1 = false;
        boolean movimiento = false;

        Stack<Carta>[][] mesaInterior = mesa.getMontonInterior();
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();
        List<Carta> cartasInterior = new ArrayList<>();
        List<Carta> cartasBastos = new ArrayList<>();
        List<Carta> cartasCopas = new ArrayList<>();
        List<Carta> cartasEspadas = new ArrayList<>();
        List<Carta> cartasOros = new ArrayList<>();

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

            //Verificar si una carta del interior puede ir al exterior
            int min = 0;
            for (int i = 1; i < cartasBastos.size(); i++) {
                min = (cartasBastos.get(min).getNumeroCarta() < cartasBastos.get(i).getNumeroCarta()) ? min : i;
                if (min == 1) {
                    movimiento = true;
                    break salir;
                }

                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.BASTOS)].size() > 0) {
                    if (min == mesaExterior[0].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break salir;
                    }
                }
            }
            min = 0;
            for (int i = 1; i < cartasCopas.size(); i++) {
                min = (cartasCopas.get(min).getNumeroCarta() < cartasCopas.get(i).getNumeroCarta()) ? min : i;
                if (min == 1) {
                    movimiento = true;
                    break salir;
                }
                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.COPAS)].size() > 0) {
                    if (min == mesaExterior[1].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break salir;
                    }
                }
            }
            min = 0;
            for (int i = 1; i < cartasEspadas.size(); i++) {
                min = (cartasEspadas.get(min).getNumeroCarta() < cartasEspadas.get(i).getNumeroCarta()) ? min : i;
                if (min == 1) {
                    movimiento = true;
                    break salir;
                }
                if (mesaExterior[encontrarPalo(mesa).indexOf(Palos.ESPADAS)].size() > 0) {
                    if (min == mesaExterior[2].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break salir;
                    }
                }
            }
            min = 0;
            for (int i = 1; i < cartasOros.size(); i++) {
                min = (cartasOros.get(min).getNumeroCarta() < cartasOros.get(i).getNumeroCarta()) ? min : i;
                if (min == 1) {
                    movimiento = true;
                    break salir;
                }
                if (mesaExterior[3].size() > 0) {
                    if (min == mesaExterior[encontrarPalo(mesa).indexOf(Palos.OROS)].lastElement().getNumeroCarta() + 1) {
                        movimiento = true;
                        break salir;
                    }
                }
            }

        }

        //Verificar movimientos en el monto interior entre cartas
        if (!movimiento && !movimiento1) {
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
        return (movimiento1 || movimiento);
    }

    public boolean comprobarMovimiento(Mesa mesa, Carta carta, int fila, int columna) {

        boolean mover = false;

        Stack<Carta>[][] mesaInterior = mesa.getMontonInterior();
        Stack<Carta>[] mesaExterior = mesa.getMontonExterior();

        Stack<Carta> monto1 = carta.seleccionarMonton(mesa, fila, columna);
        Stack<Carta> monto2 = carta.seleccionarMonton(mesa, fila, columna);

        //Verificación movimiento del monto interior al exterior
        if (monto2.size() == 0) {
            if (monto1.lastElement().getNumeroCarta() == 1) {
                mover = true;
            } else {
                System.out.println("Movimiento erróneo");
            }
        }
        if (monto2.size() > 0) {
            //Palos palo = encontrarPalo(mesa, monto2.lastElement().getPalo());
            Palos palo = monto2.lastElement().getPalo();
            if (monto1.lastElement().getPalo().equals(palo)) {
                if (monto1.lastElement().getNumeroCarta() == monto2.lastElement().getNumeroCarta() + 1) {
                    mover = true;
                }
            } else {
                System.out.println("movimiento erróneo");
            }
        }

        //Verificación movimiento en el monto interior
        if (monto1.lastElement().getPalo() == monto2.lastElement().getPalo()) {
            if (monto1.lastElement().getNumeroCarta() == monto2.lastElement().getNumeroCarta() - 1)
                mover = true;
        } else {
            System.out.println("Movimiento erróneo");
        }
        return mover;
    }
}
