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
        this.maxIntentos = (maxIntentos <= 0) ? 3 : maxIntentos;
        reiniciarAcceso();
        this.accesoExitoso = false;
    }

    // Métodos accesorios
    public String getUsername() { return username; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isBloqueado() { return bloqueado; }
    public int getMaxIntentos() { return maxIntentos; }
    public boolean isAccesoExitoso() { return accesoExitoso; }

    // Métodos de negocio

    public boolean autenticar(String passwordIngresada) {
        if (!puedeIntentar()) {
            return false;
        }

        if (esPasswordCorrecta(passwordIngresada)) {
            procesarExito();
            return true;
        }

        procesarFallo();
        return false;
    }

    private boolean puedeIntentar() {
        return !bloqueado;
    }

    private boolean esPasswordCorrecta(String passwordIngresada) {
        return this.password.equals(passwordIngresada);
    }

    private void procesarExito() {
        intentosFallidos = 0;
        accesoExitoso = true;
    }

    private void procesarFallo() {
        intentosFallidos++;
        if (intentosFallidos >= maxIntentos) {
            bloqueado = true;
        }
    }

    public void reiniciarAcceso() {
        intentosFallidos = 0;
        bloqueado = false;
    }

    public boolean cambiarPassword(String actual, String nueva) {
        if (bloqueado || !esPasswordCorrecta(actual)) {
            return false;
        }

        if (!validarPasswordSegura(nueva)) {
            return false;
        }

        password = nueva;
        return true;
    }

    public boolean validarPasswordSegura(String nueva) {
        if (nueva == null || nueva.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneNumero = false;

        for (int i = 0; i < nueva.length(); i++) {
            char c = nueva.charAt(i);

            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }

            if (Character.isDigit(c)) {
                tieneNumero = true;
            }

            if (tieneMayuscula && tieneNumero) {
                return true;
            }
        }

        return false;
    }
}