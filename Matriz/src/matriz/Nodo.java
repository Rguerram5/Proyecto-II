package matriz;

import java.util.Scanner;

class Nodo {

    String placa;
    String color;
    String linea;
    String modelo;
    String propietario;
    Nodo arriba;
    Nodo abajo;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(String placa, String color, String linea, String modelo, String propietario) {
        this.placa = placa;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        this.propietario = propietario;
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }
}

class MatrizOrtogonal {

    Nodo root;

    public MatrizOrtogonal() {
        this.root = null;
    }

    public void insertar(String placa, String color, String linea, String modelo, String propietario) {
        Nodo nuevo = new Nodo(placa, color, linea, modelo, propietario);
        if (root == null) {
            root = nuevo;
            System.out.println("Nodo guardado con exito en fila 0, columna 0");
            return;
        }

        Nodo temp = root;
        Nodo lastRight = null;
        int fila = 0, columna = 0;

        while (temp != null) {
            lastRight = temp;
            while (lastRight.derecha != null) {
                lastRight = lastRight.derecha;
                columna++;
            }
            if (lastRight.derecha == null) {
                lastRight.derecha = nuevo;
                nuevo.izquierda = lastRight;
                System.out.println("Nodo guardado con exito en fila " + fila + ", columna " + (columna + 1));
                return;
            }
            temp = temp.abajo;
            fila++;
            columna = 0;
        }

        // Si no hay más espacio en la fila actual, agregue una nueva fila
        lastRight = root;
        while (lastRight.abajo != null) {
            lastRight = lastRight.abajo;
        }
        lastRight.abajo = nuevo;
        nuevo.arriba = lastRight;
        System.out.println("Nodo guardado con exito en fila " + (fila + 1) + ", columna 0");
    }

    public String buscar(String valor) {
        Nodo temp = root;
        int fila = 0;
        while (temp != null) {
            Nodo innerTemp = temp;
            int columna = 0;
            while (innerTemp != null) {
                if (innerTemp.placa.equalsIgnoreCase(valor) || innerTemp.color.equalsIgnoreCase(valor)
                        || innerTemp.linea.equalsIgnoreCase(valor) || innerTemp.modelo.equalsIgnoreCase(valor)
                        || innerTemp.propietario.equalsIgnoreCase(valor)) {
                    return "Nodo encontrado en fila " + fila + ", columna " + columna + " con datos: "
                            + "Placa: " + innerTemp.placa + ", Color: " + innerTemp.color + ", Linea: " + innerTemp.linea
                            + ", Modelo: " + innerTemp.modelo + ", Propietario: " + innerTemp.propietario;
                }
                innerTemp = innerTemp.derecha;
                columna++;
            }
            temp = temp.abajo;
            fila++;
        }
        return "No se encontro el nodo.";
    }

    public void eliminar(int fila, int columna) {
        if (root == null) {
            System.out.println("Matriz vacia.");
            return;
        }
        // Encontrar el nodo a eliminar
        Nodo actual = root;
        for (int i = 0; i < fila; i++) {
            if (actual.abajo == null) {
                System.out.println("Fila fuera de rango.");
                return;
            }
            actual = actual.abajo;
        }
        Nodo nodoAEliminar = actual;
        for (int j = 0; j < columna; j++) {
            if (nodoAEliminar.derecha == null) {
                System.out.println("Columna fuera de rango.");
                return;
            }
            nodoAEliminar = nodoAEliminar.derecha;
        }

        // Reorganizar la matriz para eliminar el nodo
        Nodo filaActual = nodoAEliminar;
        while (filaActual != null) {
            Nodo mover = filaActual.derecha;
            while (mover != null) {
                filaActual.placa = mover.placa;
                filaActual.color = mover.color;
                filaActual.linea = mover.linea;
                filaActual.modelo = mover.modelo;
                filaActual.propietario = mover.propietario;
                filaActual = mover;
                mover = mover.derecha;
            }
            if (filaActual == nodoAEliminar) {
                if (filaActual.izquierda != null) {
                    filaActual.izquierda.derecha = null;
                } else { // Eliminar fila completa si el nodo es el primer nodo
                    if (filaActual.arriba != null) {
                        filaActual.arriba.abajo = filaActual.abajo;
                    }
                    if (filaActual.abajo != null) {
                        filaActual.abajo.arriba = filaActual.arriba;
                    }
                    if (filaActual == root) {
                        root = filaActual.abajo;
                    }
                }
                break;
            }
            filaActual.derecha = null;
            filaActual = filaActual.abajo;
        }
        System.out.println("Nodo eliminado y matriz reorganizada.");
    }
}
