package org.nine;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class HEXASTOCK {
    static Scanner sc = new Scanner(System.in);
    //colores
    static final String LAVANDA = "\033[38;5;183m";
    static final String ROSA_PASTEL = "\033[38;5;217m";
    static final String AZUL_ABISMO = "\033[38;5;17m";
    static final String AZUL_MARINO = "\033[38;5;18m";
    static final String AZUL_PROFUNDO = "\033[38;5;19m";
    static final String ROSA_PALO = "\033[38;5;181m";
    static final String CIRUELA = "\033[38;5;96m";
    static final String UVA_CLARA = "\033[38;5;177m";
    static final String VIOLETA_PASTEL = "\033[38;5;183m";
    static final String AZUL_PASTEL1 = "\033[38;5;195m";
    static final String LILA_PASTEL = "\033[38;5;189m";
    static final String NEGRITA = "\033[1m";
    static final String AZUL_BEBE = "\033[38;5;153m";
    static final String AZUL_PASTEL = "\033[38;5;152m";
    static final String AZUL_PERIWINKLE = "\033[38;5;104m";
    static final String AZUL_HIELO = "\033[38;5;189m";
    static final String AZUL_ACERO = "\033[38;5;67m";
    static final String AZUL_CIELO = "\033[38;5;117m";
    static final String AZUL_GLACIAR = "\033[38;2;165;201;216m";
    static final String ROJO = "\033[31m";
    static final String AMARILLO = "\033[33m";
    static final String NARANJA = "\033[38;5;208m";
    static final String ROSA_CEREZA = "\033[38;2;166;77;101m";
    static final String RESET = "\033[0m";
    //archivo de texto
    static boolean sesionIniciada = false;
    static boolean programaActivo = true;
    static String usuarioActual = "";
    static File archivo = new File("usuarios.txt");
    static File archivoDatos;
    // arreglos
    static int[] cantidadesMedicamentos = new int[100];
    static String[] fechasMedicamentos = new String[100];
    static double[] preciosMedicamentos = new double[100];
    static String[] presentacionesMedicamentos = new String[100];
    static String[] concentracionesMedicamentos = new String[100];
    static String[] contenidoEnvaseMedicamentos = new String[100];
    static String[] nombresMedicamentos = new String[100];
    //variables
    static int contador = 0;
    static int diasFechaActual = 0;
    // ==================== METODO PRINCIPAL ====================
    public static void main(String[] args) throws IOException {
        while (programaActivo) {
            if (!sesionIniciada) {
                menuAutenticacion();
            } else {
                menuPrincipal();
            }
        }
        System.out.println(NEGRITA+"Programa finalizado."+RESET);
        System.out.println(AZUL_CIELO+"Vuelve pronto."+RESET);
    }
    // ==================== METODOS DE LECTURA ====================
    public static int leerEntero() {
        return Integer.parseInt(sc.nextLine());
    }
    public static double leerDouble() {
        return Double.parseDouble(sc.nextLine());
    }
    // ==================== CONVERSION DE FECHAS ====================
    public static int convertirFechaADias(String fecha) {
        try {
            String[] partes = fecha.split("/");
            if (partes.length == 3) {
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int anio = Integer.parseInt(partes[2]);
                if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12) {
                    return (anio * 365) + (mes * 30) + dia;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
    // ==================== PRESENTACIONES ====================
    public static String seleccionarPresentacion() {
        String[] presentaciones = {
                "Comprimidos",
                "Tabletas",
                "Grageas",
                "Capsulas duras",
                "Capsulas blandas",
                "Polvos",
                "Granulados",
                "Óvulos",
                "Supositorios",
                "Parches transdermicos",
                "Implantes",
                "Cremas",
                "Pomadas",
                "Geles",
                "Pastas",
                "Soluciones",
                "Gotas",
                "Jarabes",
                "Elixires",
                "Suspensiones",
                "Emulsiones",
                "Inyectables (ampollas)",
                "Inyectables (viales)",
                "Jeringas precargadas",
                "Plumas precargadas",
                "Lociones",
                "Linimentos",
                "Aerosoles",
                "Inhaladores"
        };
        int opcion;
        System.out.println(AZUL_PASTEL1+"\n------- PRESENTACIONES -------"+RESET);
        for (int i = 0; i < presentaciones.length; i++) {
            System.out.println((i + 1) + ". " + presentaciones[i]);
        }
        System.out.println("\nSelecciona una presentación:");
        opcion = leerEntero();
        while (opcion < 1 || opcion > presentaciones.length) {
            System.out.println(ROJO + "Opción inválida. Intenta nuevamente." + RESET);
            opcion = leerEntero();
        }
        return presentaciones[opcion - 1];
    }
    // ==================== AUTENTICACION ====================
    public static void menuAutenticacion() throws IOException {
        System.out.println(AZUL_BEBE + "\n===== AUTENTICACIÓN =====" + RESET);
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print(AZUL_HIELO + "Selecciona una opcion: " + RESET);
        int opcion = leerEntero();
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
    public static void registrarUsuario() throws IOException {
        System.out.println(AZUL_ACERO + "\n===== REGISTRO DEL USUARIO =====" + RESET);
        System.out.print("Ingresa tu usuario: ");
        String usuario = sc.nextLine();
        if (usuario.isEmpty()) {
            System.out.println(NEGRITA+"El usuario no puede estar vacio."+RESET);
            return;
        }
        if (existeUsuario(usuario)) {
            System.out.println(ROJO + "Este usuario ya esta registrado." + RESET);
        } else {
            System.out.print("Ingresa tu contrasena: ");
            String contrasena = sc.nextLine();
            if (contrasena.isEmpty()) {
                System.out.println(NEGRITA+"La contrasena no puede estar vacia."+RESET);
                return;
            }
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(usuario + ";" + contrasena + "\n");
            escribir.close();
            System.out.println(AZUL_GLACIAR+"Registro exitoso."+RESET);
            usuarioActual = usuario;
            sesionIniciada = true;
            System.out.println(AZUL_CIELO+"Ingresa la fecha de hoy (DD/MM/AAAA):"+RESET);
            String fechaHoy = sc.nextLine();
            diasFechaActual = convertirFechaADias(fechaHoy);
            cargarDatos();
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
            System.out.println(AZUL_CIELO+"Ingresa la fecha de hoy (DD/MM/AAAA):"+RESET);
            String fechaHoy = sc.nextLine();
            diasFechaActual = convertirFechaADias(fechaHoy);
            cargarDatos();
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
            if (datos.length == 2 &&
                    datos[0].equals(usuario) &&
                    datos[1].equals(contrasena)) {
                leer.close();
                return true;
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
            if (datos.length == 2 && datos[0].equals(usuario)) {
                leer.close();
                return true;
            }
        }
        leer.close();
        return false;
    }
    // ==================== GUARDAR Y CARGAR DATOS ====================
    public static void guardarDatos() throws IOException {
        archivoDatos = new File("datos_" + usuarioActual + ".txt");
        FileWriter escribir = new FileWriter(archivoDatos, false);
        for (int i = 0; i < contador; i++) {
            escribir.write(
                    nombresMedicamentos[i] + ";" +
                            cantidadesMedicamentos[i] + ";" +
                            fechasMedicamentos[i] + ";" +
                            preciosMedicamentos[i] + ";" +
                            presentacionesMedicamentos[i] + ";" +
                            concentracionesMedicamentos[i] + ";" +
                            contenidoEnvaseMedicamentos[i] + "\n"
            );
        }
        escribir.close();
    }
    public static void cargarDatos() throws IOException {
        contador = 0;
        archivoDatos = new File("datos_" + usuarioActual + ".txt");
        if (!archivoDatos.exists()) {
            return;
        }
        BufferedReader leer = new BufferedReader(new FileReader(archivoDatos));
        String linea;
        while ((linea = leer.readLine()) != null && contador < 100) {
            String[] datos = linea.split(";", -1);
            if (datos.length == 7 || datos.length == 6) {
                nombresMedicamentos[contador] = datos[0];
                cantidadesMedicamentos[contador] =
                        Integer.parseInt(datos[1]);
                fechasMedicamentos[contador] = datos[2];
                preciosMedicamentos[contador] =
                        Double.parseDouble(datos[3]);
                presentacionesMedicamentos[contador] = datos[4];
                concentracionesMedicamentos[contador] = datos[5];
                if (datos.length == 7) {
                    contenidoEnvaseMedicamentos[contador] = datos[6];
                } else {
                    contenidoEnvaseMedicamentos[contador] = "No especificado";
                }
                contador++;
            }
        }
        leer.close();
    }
    // ==================== REGISTRAR MEDICAMENTOS ====================
    public static void registrarMedicamentos() throws IOException {
        System.out.println(AZUL_PASTEL1+"\n------- REGISTRAR MEDICAMENTOS -------"+RESET);
        System.out.println("Ingresa cuantos medicamentos vas a registrar:");
        int cuantos = leerEntero();
        if (cuantos <= 0) {
            System.out.println(ROSA_CEREZA+"Cantidad no valida."+RESET);
        } else if (contador + cuantos > 100) {
            System.out.println(ROJO + "¡Error! No hay suficiente espacio." + RESET);
        } else {
            for (int i = 0; i < cuantos; i++) {
                System.out.println("\nMedicamento " + (contador + 1));
                System.out.println("Ingresa el nombre del medicamento:");
                nombresMedicamentos[contador] = sc.nextLine();
                System.out.println("Selecciona la presentación:");
                presentacionesMedicamentos[contador] = seleccionarPresentacion();
                System.out.println("Ingresa la concentración del medicamento:");
                System.out.println("Ejemplo: 500 mg/tableta, 250 mg/5 mL, 1 %");
                concentracionesMedicamentos[contador] = sc.nextLine();
                System.out.println("Ingresa el contenido del envase:");
                System.out.println("Ejemplo: 20 tabletas, 120 mL, 10 capsulas");
                contenidoEnvaseMedicamentos[contador] = sc.nextLine();
                System.out.println("Ingresa la cantidad en stock:");
                cantidadesMedicamentos[contador] = leerEntero();
                System.out.println("Ingresa la fecha de caducidad (DD/MM/AAAA):");
                fechasMedicamentos[contador] = sc.nextLine();
                System.out.println("Ingresa el precio unitario del medicamento:");
                preciosMedicamentos[contador] = leerDouble();
                contador++;
            }
            guardarDatos();
            System.out.println(AZUL_GLACIAR +
                    "¡Medicamentos guardados correctamente!" + RESET);
            System.out.println("¿Deseas ingresar mas medicamentos? (1- Si / 2- No)");
            int opc = leerEntero();
            while (opc != 1 && opc != 2) {
                System.out.println(ROJO+"Error, opcion invalida, ingresa 1 o 2"+RESET);
                opc = leerEntero();
            }
            if (opc == 1) {
                registrarMedicamentos();
            }
        }
    }
    // ==================== CONSULTAR MEDICAMENTOS ====================
    public static void consultarMedicamentos() {
        System.out.println(AZUL_PASTEL1+"\n------- LISTA DE MEDICAMENTOS REGISTRADOS -------"+RESET);
        if (contador == 0) {
            System.out.println(ROJO+"NO hay medicamentos registrados."+RESET);
        } else {
            System.out.printf("%-4s | %-20s | %-30s | %-18s | %-18s | %-8s | %-12s | %-8s\n",
                    AZUL_BEBE+"No.", "Nombre", "Presentación", "Concentración",
                    "Contenido", "Stock", "Caducidad", "Precio"+RESET);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < contador; i++) {
                System.out.printf("%-4d | %-20s | %-30s | %-18s | %-18s | %-8d | %-12s | $%-8.2f\n",
                        (i + 1),
                        nombresMedicamentos[i],
                        presentacionesMedicamentos[i],
                        concentracionesMedicamentos[i],
                        contenidoEnvaseMedicamentos[i],
                        cantidadesMedicamentos[i],
                        fechasMedicamentos[i],
                        preciosMedicamentos[i]
                );
            }
        }
    }
    // ==================== MOSTRAR UN PRODUCTO ====================
    public static void mostrarProducto(int indice) {
        System.out.println(AZUL_PASTEL1+"\n------- INFORMACION DEL MEDICAMENTO -------"+RESET);
        System.out.printf("%-4s | %-20s | %-30s | %-18s | %-18s | %-8s | %-12s | %-8s\n",
                AZUL_BEBE+"No.", "Nombre", "Presentación", "Concentración",
                "Contenido", "Stock", "Caducidad", "Precio"+RESET);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4d | %-20s | %-30s | %-18s | %-18s | %-8d | %-12s | $%-8.2f\n",
                (indice + 1),
                nombresMedicamentos[indice],
                presentacionesMedicamentos[indice],
                concentracionesMedicamentos[indice],
                contenidoEnvaseMedicamentos[indice],
                cantidadesMedicamentos[indice],
                fechasMedicamentos[indice],
                preciosMedicamentos[indice]);
    }
    // ==================== BUSCAR PRODUCTO ====================
    public static int buscarProducto() {
        System.out.println(AZUL_ACERO + "\n===== BUSCAR PRODUCTO =====" + RESET);
        if (contador == 0) {
            System.out.println(ROJO + "No hay medicamentos registrados." + RESET);
            return -1;
        }
        System.out.println("¿Como deseas buscar el medicamento?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por presentación");
        System.out.println("3. Por concentración");
        System.out.println("4. Volver");
        int opcion = leerEntero();
        while (opcion < 1 || opcion > 4) {
            System.out.println(ROJO + "Opción inválida. Intenta nuevamente." + RESET);
            opcion = leerEntero();
        }
        if (opcion == 4) {
            return -1;
        }
        System.out.println("Ingresa el dato que deseas buscar:");
        String datoBuscar = sc.nextLine().toLowerCase();
        int[] encontrados = new int[100];
        int coincidencias = 0;
        for (int i = 0; i < contador; i++) {
            boolean coincide = false;
            switch (opcion) {
                case 1:
                    coincide = nombresMedicamentos[i].toLowerCase().contains(datoBuscar);
                    break;
                case 2:
                    coincide = presentacionesMedicamentos[i].toLowerCase().contains(datoBuscar);
                    break;
                case 3:
                    coincide = concentracionesMedicamentos[i].toLowerCase().contains(datoBuscar);
                    break;
            }
            if (coincide) {
                encontrados[coincidencias] = i;
                coincidencias++;
            }
        }
        if (coincidencias == 0) {
            System.out.println(ROJO + "No se encontraron coincidencias." + RESET);
            return -1;
        }
        System.out.println(AZUL_PASTEL1+"\n------- RESULTADOS DE BUSQUEDA -------"+RESET);
        System.out.printf("%-4s | %-20s | %-30s | %-18s | %-8s\n",
                AZUL_BEBE+    "No.", "Nombre", "Presentación", "Concentración", "Stock"+RESET);
        for (int i = 0; i < coincidencias; i++) {
            int indice = encontrados[i];
            System.out.printf("%-4d | %-20s | %-30s | %-18s | %-8d\n",
                    (i + 1),
                    nombresMedicamentos[indice],
                    presentacionesMedicamentos[indice],
                    concentracionesMedicamentos[indice],
                    cantidadesMedicamentos[indice]);
        }
        System.out.println("\nSelecciona el número del medicamento que deseas consultar:");
        int seleccion = leerEntero();
        while (seleccion < 1 || seleccion > coincidencias) {
            System.out.println(ROJO + "Selección inválida. Intenta nuevamente." + RESET);
            seleccion = leerEntero();
        }
        int indiceSeleccionado = encontrados[seleccion - 1];
        mostrarProducto(indiceSeleccionado);
        return indiceSeleccionado;
    }
    // ==================== OPERACIONES DEL PRODUCTO ENCONTRADO ====================
    public static void operacionesProducto() throws IOException {
        int indice = buscarProducto();
        if (indice == -1) {
            return;
        }
        int opcion;
        do {
            System.out.println(AZUL_ACERO + "\n===== OPERACIONES DEL PRODUCTO =====" + RESET);
            System.out.println("Medicamento: " +AZUL_PASTEL1+ nombresMedicamentos[indice]+RESET);
            System.out.println("1. Modificar información");
            System.out.println("2. Registrar entrada");
            System.out.println("3. Registrar salida");
            System.out.println("4. Eliminar medicamento");
            System.out.println("5. Volver");
            System.out.println("Selecciona una opcion:");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    modificarMedicamento(indice);
                    break;
                case 2:
                    registrarEntradaProducto(indice);
                    break;
                case 3:
                    registrarSalidaProducto(indice);
                    break;
                case 4:
                    eliminarMedicamento(indice);
                    return;
                case 5:
                    System.out.println(AZUL_HIELO+"Regresando..."+RESET);
                    break;
                default:
                    System.out.println(ROJO + "Opción inválida." + RESET);
                    break;
            }
        } while (opcion != 5);
    }
    // ==================== MODIFICAR MEDICAMENTO ENCONTRADO ====================
    public static void modificarMedicamento(int indice) throws IOException {
        int opcion;
        do {
            System.out.println(AZUL_ACERO + "\n===== MODIFICAR MEDICAMENTO =====" + RESET);
            mostrarProducto(indice);
            System.out.println("\n¿Qué dato deseas modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Presentación");
            System.out.println("3. Concentración");
            System.out.println("4. Contenido del envase");
            System.out.println("5. Stock");
            System.out.println("6. Fecha de caducidad");
            System.out.println("7. Precio");
            System.out.println("8. Terminar modificación");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    System.out.println("Ingresa el nuevo nombre:");
                    nombresMedicamentos[indice] = sc.nextLine();
                    break;
                case 2:
                    presentacionesMedicamentos[indice] = seleccionarPresentacion();
                    break;
                case 3:
                    System.out.println("Ingresa la nueva concentración:");
                    concentracionesMedicamentos[indice] = sc.nextLine();
                    break;
                case 4:
                    System.out.println("Ingresa el nuevo contenido del envase:");
                    contenidoEnvaseMedicamentos[indice] = sc.nextLine();
                    break;
                case 5:
                    System.out.println("Ingresa el nuevo stock:");
                    int nuevoStock = leerEntero();
                    if (nuevoStock >= 0) {
                        cantidadesMedicamentos[indice] = nuevoStock;
                    } else {
                        System.out.println(ROJO + "El stock no puede ser negativo." + RESET);
                    }
                    break;
                case 6:
                    System.out.println("Ingresa la nueva fecha de caducidad:");
                    fechasMedicamentos[indice] = sc.nextLine();
                    break;
                case 7:
                    System.out.println("Ingresa el nuevo precio:");
                    double nuevoPrecio = leerDouble();
                    if (nuevoPrecio >= 0) {
                        preciosMedicamentos[indice] = nuevoPrecio;
                    } else {
                        System.out.println(ROJO + "El precio no puede ser negativo." + RESET);
                    }
                    break;
                case 8:
                    break;
                default:
                    System.out.println(ROJO + "Opción inválida." + RESET);
                    break;
            }
            if (opcion >= 1 && opcion <= 7) {
                guardarDatos();
                System.out.println(AZUL_GLACIAR +
                        "Información actualizada correctamente." + RESET);
            }
        } while (opcion != 8);
    }
    // ==================== ENTRADA DESDE BUSQUEDA ====================
    public static void registrarEntradaProducto(int indice) throws IOException {
        System.out.println(VIOLETA_PASTEL+"\n===== REGISTRAR ENTRADA ====="+RESET);
        System.out.println("Medicamento: " + nombresMedicamentos[indice]);
        System.out.println("Stock actual: " + cantidadesMedicamentos[indice]);
        System.out.println("¿Cuántas unidades ingresarán?");
        int cantidad = leerEntero();
        if (cantidad <= 0) {
            System.out.println(ROJO + "Cantidad inválida." + RESET);
            return;
        }
        cantidadesMedicamentos[indice] += cantidad;
        guardarDatos();
        System.out.println(AZUL_GLACIAR +
                "Entrada registrada. Nuevo stock: " +
                cantidadesMedicamentos[indice] + RESET);
    }
    // ==================== SALIDA DESDE BUSQUEDA ====================
    public static void registrarSalidaProducto(int indice) throws IOException {
        System.out.println(LILA_PASTEL+"\n===== REGISTRAR SALIDA ====="+RESET);
        System.out.println("Medicamento: " + nombresMedicamentos[indice]);
        System.out.println("Stock actual: " + cantidadesMedicamentos[indice]);
        System.out.println("¿Cuántas unidades saldrán?");
        int cantidad = leerEntero();
        if (cantidad <= 0) {
            System.out.println(ROJO + "Cantidad inválida." + RESET);
            return;
        }
        if (cantidad > cantidadesMedicamentos[indice]) {
            System.out.println(ROJO + "No hay suficiente stock." + RESET);
            return;
        }
        cantidadesMedicamentos[indice] -= cantidad;
        guardarDatos();
        System.out.println(AZUL_GLACIAR +
                "Salida registrada. Stock restante: " +
                cantidadesMedicamentos[indice] + RESET);
    }
    // ==================== ELIMINAR MEDICAMENTO ====================
    public static void eliminarMedicamento(int indice) throws IOException {
        System.out.println(ROSA_CEREZA+"\n===== ELIMINAR MEDICAMENTO ====="+RESET);
        System.out.println("¿Estás seguro de eliminar " +
                nombresMedicamentos[indice] + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = leerEntero();
        if (opcion != 1) {
            System.out.println("Operación cancelada.");
            return;
        }
        for (int i = indice; i < contador - 1; i++) {
            nombresMedicamentos[i] = nombresMedicamentos[i + 1];
            presentacionesMedicamentos[i] = presentacionesMedicamentos[i + 1];
            concentracionesMedicamentos[i] = concentracionesMedicamentos[i + 1];
            contenidoEnvaseMedicamentos[i] = contenidoEnvaseMedicamentos[i + 1];
            cantidadesMedicamentos[i] = cantidadesMedicamentos[i + 1];
            fechasMedicamentos[i] = fechasMedicamentos[i + 1];
            preciosMedicamentos[i] = preciosMedicamentos[i + 1];
        }
        contador--;
        nombresMedicamentos[contador] = "";
        presentacionesMedicamentos[contador] = "";
        concentracionesMedicamentos[contador] = "";
        contenidoEnvaseMedicamentos[contador] = "";
        cantidadesMedicamentos[contador] = 0;
        fechasMedicamentos[contador] = "";
        preciosMedicamentos[contador] = 0.0;
        guardarDatos();
        System.out.println(ROSA_CEREZA +
                "Medicamento eliminado correctamente." + RESET);
    }
    // ==================== MODIFICAR O ELIMINAR GENERAL ====================
    public static void modificar() throws IOException {
        System.out.println(AZUL_ACERO +
                "\n===== MODIFICAR O ELIMINAR MEDICAMENTO =====" + RESET);
        if (contador == 0) {
            System.out.println(ROJO +
                    "No hay medicamentos registrados para modificar." + RESET);
            return;
        }
        consultarMedicamentos();
        System.out.println("\nIngresa el número del medicamento que deseas gestionar:");
        int opcionMedicamento = leerEntero();
        while (opcionMedicamento < 1 || opcionMedicamento > contador) {
            System.out.println(ROJO + "Número de medicamento inválido." + RESET);
            opcionMedicamento = leerEntero();
        }
        int indice = opcionMedicamento - 1;
        int opcion;
        do {
            System.out.println(AZUL_PERIWINKLE+"\n===== GESTIONAR MEDICAMENTO ====="+RESET);
            System.out.println("1. Modificar información");
            System.out.println("2. Eliminar medicamento");
            System.out.println("3. Volver");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    modificarMedicamento(indice);
                    break;
                case 2:
                    eliminarMedicamento(indice);
                    return;
                case 3:
                    break;
                default:
                    System.out.println(ROJO + "Opción inválida." + RESET);
                    break;
            }
        } while (opcion != 3);
    }
    // ==================== ENTRADA GENERAL ====================
    public static void entrada() throws IOException {
        System.out.println(AZUL_ACERO +NEGRITA+ "\n===== ENTRADA DE MEDICAMENTOS =====" + RESET);
        if (contador == 0) {
            System.out.println(ROJO + "No hay medicamentos registrados." + RESET);
            return;
        }
        consultarMedicamentos();
        System.out.println("Ingresa el número del medicamento:");
        int opcion = leerEntero();
        while (opcion < 1 || opcion > contador) {
            System.out.println(ROJO + "Número inválido." + RESET);
            opcion = leerEntero();
        }
        registrarEntradaProducto(opcion - 1);
        System.out.println("¿Desea registrar mas salidas de medicamentos? (1- Si / 2- No)");
        int opc = leerEntero();
        while (opc != 1 && opc != 2) {
            System.out.println(ROJO+"Error, opcion invalida, ingresa 1 o 2"+RESET);
            opc = leerEntero();
        }
        if (opc == 1) {
            entrada();
        }
    }
    // ==================== SALIDA GENERAL ====================
    public static void salida() throws IOException {
        System.out.println(AZUL_ACERO +
                "\n===== SALIDA DE MEDICAMENTOS =====" + RESET);
        if (contador == 0) {
            System.out.println(ROJO + "No hay medicamentos registrados." + RESET);
            return;
        }
        consultarMedicamentos();
        System.out.println("Ingresa el número del medicamento:");
        int opcion = leerEntero();
        while (opcion < 1 || opcion > contador) {
            System.out.println(ROJO + "Número inválido." + RESET);
            opcion = leerEntero();
        }
        registrarSalidaProducto(opcion - 1);
        System.out.println("¿Deseas registrar mas salidas de  medicamentos? (1- Si / 2- No)");
        int opc = leerEntero();
        while (opc != 1 && opc != 2) {
            System.out.println(ROJO+"Error, opcion invalida, ingresa 1 o 2"+RESET);
            opc = leerEntero();
        }
        if (opc == 1) {
            salida();
        }
    }
    // ==================== ALERTAS ====================
    public static void mostrarAlertas() {
        System.out.println(AZUL_ACERO +
                "\n===== MONITOR DE ALERTAS Y NOTIFICACIONES =====" + RESET);
        if (contador == 0) {
            System.out.println(NEGRITA+"No hay medicamentos registrados para analizar."+RESET);
            return;
        }
        int stockBajoCount = 0;
        int sinStockCount = 0;
        int porCaducarCount = 0;
        int caducadosCount = 0;
        System.out.println("\n--- ALERTAS DE STOCK ---");
        for (int i = 0; i < contador; i++) {
            if (cantidadesMedicamentos[i] == 0) {
                System.out.println(ROJO +
                        "[SIN STOCK] " + nombresMedicamentos[i] + RESET);
                sinStockCount++;
            } else if (cantidadesMedicamentos[i] < 10) {
                System.out.println(AMARILLO +
                        "[STOCK BAJO] " + nombresMedicamentos[i] +
                        " tiene " + cantidadesMedicamentos[i] + " unidades." + RESET);
                stockBajoCount++;
            }
        }
        if (sinStockCount == 0 && stockBajoCount == 0) {
            System.out.println(NEGRITA+"Todos los medicamentos tienen stock adecuado."+RESET);
        }
        System.out.println("\n--- ALERTAS DE CADUCIDAD ---");
        for (int i = 0; i < contador; i++) {
            int diasCaducidadMedicamento =
                    convertirFechaADias(fechasMedicamentos[i]);
            if (diasCaducidadMedicamento > 0 && diasFechaActual > 0) {
                int diferenciaDias =
                        diasCaducidadMedicamento - diasFechaActual;
                if (diferenciaDias < 0) {
                    System.out.println(ROJO +
                            "[CADUCADO] " + nombresMedicamentos[i] +
                            " - " + fechasMedicamentos[i] + RESET);
                    caducadosCount++;
                } else if (diferenciaDias <= 14) {
                    System.out.println(NARANJA +
                            "[POR CADUCAR] " + nombresMedicamentos[i] +
                            " - Faltan " + diferenciaDias + " días." + RESET);
                    porCaducarCount++;
                }
            }
        }
        if (porCaducarCount == 0 && caducadosCount == 0) {
            System.out.println("No hay medicamentos próximos a caducar ni caducados.");
        }
        System.out.println("\n--------------------------------------------------");
        System.out.println("Resumen:");
        System.out.println(ROJO+"Sin stock: "+RESET + sinStockCount);
        System.out.println(AMARILLO+"Stock bajo: "+RESET + stockBajoCount);
        System.out.println(NARANJA+"Por caducar: "+RESET + porCaducarCount);
        System.out.println(ROSA_CEREZA+"Caducados: "+RESET + caducadosCount);
    }
    // ==================== MENU PRINCIPAL ====================
    public static void menuPrincipal() throws IOException {
        System.out.println(AZUL_BEBE + "\n===== MENU PRINCIPAL =====" + RESET);
        System.out.println(LAVANDA+"Bienvenido " + usuarioActual+RESET);
        System.out.println("1. Realizar operaciones");
        System.out.println("2. Cerrar sesion");
        System.out.println("3. Salir del programa");
        System.out.print("Selecciona una opcion: ");
        int opcion = leerEntero();
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
                System.out.println(ROJO + "Opcion no valida." + RESET);
                break;
        }
    }
    // ==================== REALIZAR OPERACIONES ====================
    public static void realizarOperaciones() throws IOException {
        boolean enOperaciones = true;
        while (enOperaciones) {
            System.out.println(AZUL_PASTEL + "\n===== BIENVENIDO A TUS OPERACIONES =====" + RESET);
            System.out.println("1. Registrar Medicamentos"+ RESET);
            System.out.println("2. Consultar Medicamentos"+RESET);
            System.out.println("3. Registrar entrada de medicamentos"+RESET);
            System.out.println("4. Registrar salida de medicamentos"+RESET);
            System.out.println("5. Ver Alertas y Monitoreo"+RESET);
            System.out.println("6. Modificar medicamentos"+RESET);
            System.out.println("7. Buscar producto"+RESET);
            System.out.println("8. Salir al menu principal"+RESET);
            System.out.print("Seleccione una opcion: ");
            int opc = leerEntero();
            switch (opc) {
                case 1:
                    registrarMedicamentos();
                    break;
                case 2:
                    consultarMedicamentos();
                    break;
                case 3:
                    entrada();
                    break;
                case 4:
                    salida();
                    break;
                case 5:
                    mostrarAlertas();
                    break;
                case 6:
                    modificar();
                    break;
                case 7:
                    operacionesProducto();
                    break;
                case 8:
                    enOperaciones = false;
                    break;
                default:
                    System.out.println(ROJO + "Opcion invalida." + RESET);
                    break;
            }
        }
    }
    // ==================== CERRAR SESION ====================
    public static void cerrarSesion() throws IOException {
        guardarDatos();
        for (int i = 0; i < contador; i++) {
            nombresMedicamentos[i] = "";
            cantidadesMedicamentos[i] = 0;
            fechasMedicamentos[i] = "";
            preciosMedicamentos[i] = 0.0;
            presentacionesMedicamentos[i] = "";
            concentracionesMedicamentos[i] = "";
            contenidoEnvaseMedicamentos[i] = "";
        }
        contador = 0;
        sesionIniciada = false;
        usuarioActual = "";
        diasFechaActual = 0;
        System.out.println(ROSA_CEREZA + "Sesion cerrada correctamente." + RESET);
    }
    // ==================== SALIR DEL PROGRAMA ====================
    public static void salirPrograma() throws IOException {
        if (sesionIniciada) {
            guardarDatos();
        }
        programaActivo = false;
        sesionIniciada = false;
    }
}