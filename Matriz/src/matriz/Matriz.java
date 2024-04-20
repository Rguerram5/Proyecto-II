package matriz;

import java.util.Scanner;

public class Matriz {

    public static void main(String[] args) {
        MatrizOrtogonal matriz = new MatrizOrtogonal();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Insertar nodo");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Eliminar nodo");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Ingrese color: ");
                    String color = scanner.nextLine();
                    System.out.print("Ingrese Marca: ");
                    String linea = scanner.nextLine();
                    System.out.print("Ingrese Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ingrese propietario: ");
                    String propietario = scanner.nextLine();
                    matriz.insertar(placa, color, linea, modelo, propietario);
                    break;
                case 2:
                    System.out.print("Ingrese la propiedad a buscar (placa, color, Marca, modelo, propietario): ");
                    String valor = scanner.nextLine();
                    String resultado = matriz.buscar(valor);
                    System.out.println(resultado);
                    break;

                case 3:
                    System.out.print("Ingrese fila para eliminar: ");
                    int fila = scanner.nextInt();
                    System.out.print("Ingrese columna para eliminar: ");
                    int columna = scanner.nextInt();
                    matriz.eliminar(fila, columna);
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no valida.");
                    break;
            }
        }
    }
}
