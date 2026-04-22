import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== SISTEMA DE USUARIO SEGURO ===");

        // Crear usuario
        System.out.print("Ingrese username: ");
        String username = sc.nextLine();

        System.out.print("Ingrese password inicial: ");
        String password = sc.nextLine();

        System.out.print("Ingrese max intentos (ej: 3): ");
        int maxIntentos = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        UsuarioSeguroAvanzado usuario = new UsuarioSeguroAvanzado(username, password, maxIntentos);

        int opcion;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Autenticar");
            System.out.println("2. Reiniciar acceso");
            System.out.println("3. Cambiar contrase\u00f1a");
            System.out.println("4. Ver estado del usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opci\u00f3n: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese contrase\u00f1a: ");
                    String passIngreso = sc.nextLine();
                    boolean resultado = usuario.autenticar(passIngreso);
                    if (resultado) {
                        System.out.println("Autenticaci\u00f3n exitosa");
                    } else {
                        System.out.println("Fallo de autenticaci\u00f3n");
                    }
                    break;

                case 2:
                    usuario.reiniciarAcceso();
                    System.out.println("Acceso reiniciado correctamente");
                    break;

                case 3:
                    System.out.print("Ingrese contrase\u00f1a actual: ");
                    String actual = sc.nextLine();
                    System.out.print("Ingrese nueva contrase\u00f1a: ");
                    String nueva = sc.nextLine();
                    boolean cambio = usuario.cambiarPassword(actual, nueva);
                    if (cambio) {
                        System.out.println("Contrase\u00f1a cambiada correctamente");
                    } else {
                        System.out.println("No se pudo cambiar la contrase\u00f1a");
                    }
                    break;

                case 4:
                    System.out.println("\n=== ESTADO DEL USUARIO ===");
                    System.out.println("Username: " + usuario.getUsername());
                    System.out.println("Intentos fallidos: " + usuario.getIntentosFallidos());
                    System.out.println("Bloqueado: " + usuario.isBloqueado());
                    System.out.println("Acceso exitoso alguna vez: " + usuario.isAccesoExitoso());
                    System.out.println("M\u00e1x intentos: " + usuario.getMaxIntentos());
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opci\u00f3n inv\u00e1lida");
            }

        } while (opcion != 0);

        sc.close();
    }
}