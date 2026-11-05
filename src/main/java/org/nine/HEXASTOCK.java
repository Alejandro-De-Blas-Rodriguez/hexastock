package org.nine;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class HEXASTOCK {
    static String[] nombres = new String[10000];
    static int[] cantidad = new int[10000];
    static int total = 0;
     static String azulprofundo = "\u001B[48;5;153m";
     static String negro = "\u001B[30";
    static String reset = "\u001B[0m";


    public static void main(String[] Args) {
        menu();

    }

    public static void registrarProductos() {
        Scanner sc = new Scanner(System.in);
//int [] cantidad = new int[10000];
        System.out.println("Ingresa cuantos medicamento vas a registrar ");
        int cuantos = sc.nextInt();
        for (int i = 1; i <= cuantos; i++) {
            System.out.println("Producto " + i);
            System.out.println("ingresa el nombre del producto " + i);
            String productoNombre = sc.next();
            System.out.println("ingresa la cantidad que va a ser registarda del producto " + i);
            int cantidad= sc.nextInt();

        }
        System.out.println("Deseas Ingresar mas productos? ( 1-si / 2-no)");
        int opc = sc.nextInt();
        switch (opc){
            case 1:
                registrarProductos();
            case 2:
                menu();
        }

    }

    public static void  iniciarsesion (){
        System.out.println("Iniciar sesion");

    }

    public static void registrarUsuarios (){
        System.out.println("Registrar usuarios");
    }
    public static void menu() {
        int opcmenu;
        Scanner sc = new Scanner(System.in);
        System.out.println("HEXASTOCK");
        System.out.println("Bienvenido de nuevo");
        do {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Registrar producto");
            System.out.println("4. Salir");
            System.out.println("Ingresa la opcion que desees: ");
            opcmenu = sc.nextInt();
            switch (opcmenu) {
                case 1:
                iniciarsesion ();
                break;
                case 2:
                registrarUsuarios();
                break;
                case 3:
                    registrarProductos();
                    break;
                case 4:
                    System.out.println( "Hasta pronto ......." );
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcmenu != 4);

    }
}
