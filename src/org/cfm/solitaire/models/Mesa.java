package org.cfm.solitaire.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Mesa {

    private static final int filas = 4;
    private static final int columnas = 4;
    private Stack<Carta>[][] montonInterior;
    private Stack<Carta>[] montonExterior;


    public Mesa() {
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

    public void crearMesa(List baraja) {
        Random randomObj = new Random();
        List<Carta> cartasAleatorias = new ArrayList<>();
        int cartaAleatoria;
        Carta carta;

        for(int i = 0; i < 40; i++) {
                cartaAleatoria = randomObj.nextInt(40 - i);
                carta = (Carta) baraja.get(cartaAleatoria);
                cartasAleatorias.add(carta);
                baraja.remove(cartaAleatoria);
        }

        montonInterior = new Stack[filas][columnas];
        for (int i = 0; i < montonInterior.length; i++) {
            for (int j = 0; j < montonInterior[i].length; j++) {
                montonInterior[i][j] = new Stack<>();
            }
        }

        int k = 0;
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < montonInterior.length; i++) {
                for (int j = 0; j < montonInterior[i].length; j++) {
                    if (k == 1) {
                        if((i==0 && j==0) || (i==0 && j==3) || (i==1 && j==1) || (i==1 && j==2) || (i==2 && j==1) || (i==2 && j==2) || (i==3 && j==0) || (i==3 && j==3)) {
                            montonInterior[i][j].push(cartasAleatorias.get(0));
                            cartasAleatorias.remove(0);
                        }
                    } else {
                        montonInterior[i][j].push(cartasAleatorias.get(0));
                        cartasAleatorias.remove(0);
                    }
                }
            }
            k++;
        }


        montonExterior = new Stack[columnas];
        for(int i = 0; i < columnas; i++) {
            montonExterior[i] = new Stack<>();
        }

    }

    public void mostrarMesa() {
        for (int i = 0; i < montonInterior.length; i++) {
            int k = 0;
            for (int j = 0; j < montonInterior[i].length; j++) {
                if (k != 3) {
                    System.out.print(montonInterior[i][j].toString() + "\t\t|\t\t");
                } else {
                    System.out.print(montonInterior[i][j].toString() + "\n");
                }
                k++;
            }
        }
        System.out.println("....................................................");
        for(int i = 0; i < columnas; i++) {
            System.out.print(montonExterior[i].toString() + "\t\t\t\t");
        }
    }
}
