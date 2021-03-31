package org.cfm.solitaire.models;

import java.util.List;
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

    public Stack<Carta>[] getMontonExterior() {
        return montonExterior;
    }

    public void crearMesa(Baraja baraja) {

        List<Carta> barajaBarajada = baraja.barajarBaraja(baraja.crearBaraja());

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
                        if((i==j) || (i==0 && j==3) || (i==1 && j==2) || (i==2 && j==1) || (i==3 && j==0)) {
                            montonInterior[i][j].push(barajaBarajada.get(0));
                            barajaBarajada.remove(0);
                        }
                    } else {
                        montonInterior[i][j].push(barajaBarajada.get(0));
                        barajaBarajada.remove(0);
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
        System.out.println("...................................................................................");
        for (Stack<Carta>[] stacks : montonInterior) {
            int k = 0;
            for (Stack<Carta> stack : stacks) {
                if (k != 3) {
                    if (stack.size() > 0) {
                        System.out.print(stack.lastElement() + "\t\t|\t\t");
                    } else {
                        System.out.print(stack + "\t\t|\t\t");
                    }
                } else {
                    if (stack.size() > 0) {
                        System.out.print(stack.lastElement() + "\n");
                    } else {
                        System.out.print(stack + "\n");
                    }
                }
                k++;
            }
        }

        System.out.println("...................................................................................");

        for(int i = 0; i < columnas; i++) {
            if(montonExterior[i].size() > 0) {
                System.out.print(montonExterior[i].lastElement() + "\t\t\t\t");
            } else {
                System.out.print(montonExterior[i] + "\t\t\t\t");
            }

        }
        System.out.println();
        System.out.println("...................................................................................");
    }
}