public class UsuarioSeguroAvanzado {

    private String username;
    private String password;
    private int intentosFallidos;
    private boolean bloqueado;
    private int maxIntentos;
    private boolean accesoExitoso;

    public UsuarioSeguroAvanzado(String username, String password, int maxIntentos) {
        this.username = username;
        this.password = password;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.accesoExitoso = false;
        // Si maxIntentos es 0 o negativo, por defecto es 3
        this.maxIntentos = (maxIntentos <= 0) ? 3 : maxIntentos;
    }

    // --- Getters ---
    public String getUsername() { return username; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isBloqueado() { return bloqueado; }
    public int getMaxIntentos() { return maxIntentos; }
    public boolean isAccesoExitoso() { return accesoExitoso; }

    // --- Métodos de Lógica ---

    public boolean autenticar(String passwordIngresada) {
        if (bloqueado) {
            return false;
        }

        if (this.password.equals(passwordIngresada)) {
            this.intentosFallidos = 0;
            this.accesoExitoso = true;
            return true;
        } else {
            this.intentosFallidos++;
            if (this.intentosFallidos >= this.maxIntentos) {
                this.bloqueado = true;
            }
            return false;
        }
    }

    public void reiniciarAcceso() {
        this.intentosFallidos = 0;
        this.bloqueado = false;
    }

    public boolean validarPasswordSegura(String nueva) {
        // Regla 1: Mínimo 8 caracteres
        if (nueva == null || nueva.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneNumero = false;

        // Recorremos la cadena para verificar condiciones
        for (char c : nueva.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }

        return tieneMayuscula && tieneNumero;
    }

    public boolean cambiarPassword(String actual, String nueva) {
        // No se puede cambiar si está bloqueado
        if (this.bloqueado) {
            return false;
        }
        // La contraseña actual debe coincidir
        if (!this.password.equals(actual)) {
            return false;
        }
        // La nueva contraseña debe ser segura
        if (!validarPasswordSegura(nueva)) {
            return false;
        }

        this.password = nueva;
        return true;
    }
}