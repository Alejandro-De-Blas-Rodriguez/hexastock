package org.nine;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class HEXASTOCK {
    static String[] nombresProductos = new String[100];
    static int[] cantidadesProductos = new int [100];
    static int contador = 0;
    static int total = 0;
    static final String AZUL_BEBE = "\033[38;5;153m";
    static final String AZUL_PASTEL = "\033[38;5;152m";
    static final String AZUL_LAVANDA = "\033[38;5;146m";
    static final String AZUL_PERIWINKLE = "\033[38;5;104m";
    static final String AZUL_HIELO = "\033[38;5;189m";
    static final String AZUL_POLVO = "\033[38;5;110m";
    static final String AZUL_ACERO = "\033[38;5;67m";
    static final String AZUL_PIZARRA = "\033[38;5;60m";
    static final String AZUL_MARINO = "\033[38;5;17m";
    static final String AZUL_NOCHE = "\033[38;5;18m";
    static final String AZUL_PETROLEO = "\033[38;5;23m";
    static final String AZUL_TORMENTA = "\033[38;5;59m";
    static final String LILA_POLVO = "\033[38;5;146m";
    static final String AZUL_GLACIAR = "\033[38;2;165;201;216m";
    static final String ROJO = "\033[31m";
    static final String AZUL_ECLIPSE = "\033[38;2;48;59;80m";
    static final String ROSA_CEREZA = "\033[38;2;166;77;101m";
    static final String RESET = "\033[0m";
    static final String NEGRITA = "\033[1m";
    static Scanner sc = new Scanner(System.in);
    static boolean sesionIniciada = false;
    static boolean programaActivo = true;
    static String usuarioActual = "";
    static File archivo = new File("usuarios.txt");
    public static void main(String[] args) throws IOException {
        while (programaActivo) {
            while (programaActivo && !sesionIniciada) {
                menuAutenticacion();
            }
            while (programaActivo && sesionIniciada) {
                menuPrincipal();
            }
        }
        System.out.println("Programa finalizado.");
        System.out.println("Vuelve pronto.");
    }
    public static void menuAutenticacion() throws IOException {
        System.out.println(AZUL_BEBE + "\n===== AUTENTICACIÓN =====" + RESET);
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print(AZUL_HIELO + "Selecciona una opcion: " + RESET);
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                salirPrograma();
                break;
            default:
                System.out.println(ROJO + "Opcion no valida." + RESET);
                break;
        }
    }

    private static void registrarProductos() {
        System.out.println("-------Registrar productos------- ");
        System.out.println("Ingresa cuantos medicamento vas a registrar ");
        int cuantos = sc.nextInt();
        if (contador + cuantos > 100) {
            System.out.println("¡Error! No hay suficiente espacio. Solo puedes registrar " + (100 - contador) + " medicamentos más.");
        } else {
            for (int i = 0; i < cuantos; i++) {
                System.out.println("Producto " + (contador + 1));
                System.out.println("ingresa el nombre del producto " + (contador + 1));
                nombresProductos[contador] = sc.next();
                System.out.println("ingresa la cantidad que va a ser registrada del producto " + (contador + 1));
                cantidadesProductos[contador] = sc.nextInt();
                contador++;
            }
            System.out.println("¿Deseas ingresar mas productos? ( 1-si / 2-no)");
            int opc = sc.nextInt();
            switch (opc) {
                case 1:
                    registrarProductos();
                    break;
                case 2:
                    break;
            }
        }
    }
    private static void consultarProductos() {
        System.out.println("-------Lista de productos registrados-------");
        if (contador == 0) {
            System.out.println("NO hay productos registrados");
        } else {
            for (int i = 0; i < contador; i++) {
                System.out.println("Producto " + (i + 1) + " -> Nombre: " + nombresProductos[i] + " Cantidad  " + cantidadesProductos[i]);
            }

        }
    }
    public static void registrarUsuario() throws IOException {
        System.out.println(AZUL_ACERO + "\n===== REGISTRO DEL USUARIO =====" + RESET);
        System.out.print("Ingresa tu usuario: ");
        String usuario = sc.nextLine();
        if (existeUsuario(usuario)) {
            System.out.println(ROJO + "Este usuario ya esta registrado." + RESET);
        } else {
            System.out.print("Ingresa tu contrasena: ");
            String contrasena = sc.nextLine();
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(usuario + ";" + contrasena + "\n");
            escribir.close();
            System.out.println(LILA_POLVO + "Registro exitoso." + RESET);
            // Iniciar sesion automaticamente
            usuarioActual = usuario;
            sesionIniciada = true;
        }
    }
    public static void iniciarSesion() throws IOException {
        System.out.println(AZUL_PERIWINKLE + "\n===== INICIAR SESION =====" + RESET);
        System.out.print("Ingresa tu usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Ingresa tu contrasena: ");
        String contrasena = sc.nextLine();
        if (buscarUsuario(usuario, contrasena)) {
            System.out.println(AZUL_GLACIAR + "Bienvenido " + usuario + RESET);
            usuarioActual = usuario;
            sesionIniciada = true;
        } else {
            System.out.println(ROJO + "Usuario o contrasena incorrectos." + RESET);
        }
    }
    public static boolean buscarUsuario(String usuario, String contrasena) throws IOException {

        if (!archivo.exists()) {
            return false;
        }
        BufferedReader leer = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = leer.readLine()) != null) {
            String[] datos = linea.split(";");
            if (datos.length == 2) {
                if (datos[0].equals(usuario) && datos[1].equals(contrasena)) {
                    leer.close();
                    return true;
                }
            }
        }
        leer.close();
        return false;
    }
    public static boolean existeUsuario(String usuario) throws IOException {
        if (!archivo.exists()) {
            return false;
        }
        BufferedReader leer = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = leer.readLine()) != null) {
            String[] datos = linea.split(";");
            if (datos.length == 2) {
                if (datos[0].equals(usuario)) {
                    leer.close();
                    return true;
                }
            }
        }
        leer.close();
        return false;
    }
    public static void menuPrincipal() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("Usuario: " + usuarioActual);
        System.out.println("1. Realizar operaciones");
        System.out.println("2. Cerrar sesion");
        System.out.println("3. Salir del programa");
        System.out.print("Selecciona una opcion: ");
        int opcion =sc.nextInt();
        switch (opcion) {
            case 1:
                realizarOperaciones();
                break;
            case 2:
                cerrarSesion();
                break;
            case 3:
                salirPrograma();
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }

    }
    public static void realizarOperaciones() {
        System.out.println("\n=====Bienvenido a tus operaciones.=====");
        System.out.println("1. Registrar Productos");
        System.out.println("2. Consultar Productos");
        System.out.println("3. Salir a menú principal");
        System.out.println(" Seleccione una opción: ");
        int opc =sc.nextInt();
       switch (opc){
           case 1:
               registrarProductos();
               break;
           case 2:
               consultarProductos();
               break;
           case 3:
               menuPrincipal();
               break;
           default:
               System.out.println("Opcion Invalida");
               break;
       }
    }
    public static void cerrarSesion() {
        sesionIniciada = false;
        usuarioActual = "";
        System.out.println(ROSA_CEREZA + "Sesion cerrada correctamente." + RESET);
    }
    public static void salirPrograma() {
        programaActivo = false;
        sesionIniciada = false;
        System.out.println("Saliendo del programa...");
        System.out.println(AZUL_ECLIPSE + "Vuelve pronto" + RESET);
    }
}