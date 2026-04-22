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
        this.maxIntentos = (maxIntentos <= 0) ? 3 : maxIntentos;
    }

    public String getUsername() { return username; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isBloqueado() { return bloqueado; }
    public int getMaxIntentos() { return maxIntentos; }
    public boolean isAccesoExitoso() { return accesoExitoso; }

    public boolean autenticar(String passwordIngresada) {
        if (bloqueado) return false;
        if (password.equals(passwordIngresada)) {
            intentosFallidos = 0;
            accesoExitoso = true;
            return true;
        } else {
            intentosFallidos++;
            if (intentosFallidos >= maxIntentos) bloqueado = true;
            return false;
        }
    }

    public void reiniciarAcceso() {
        intentosFallidos = 0;
        bloqueado = false;
    }

    public boolean validarPasswordSegura(String nueva) {
        if (nueva.length() < 8) return false;
        boolean tieneMayuscula = !nueva.equals(nueva.toLowerCase());
        boolean tieneNumero = nueva.contains("0") || nueva.contains("1") ||
                nueva.contains("2") || nueva.contains("3") ||
                nueva.contains("4") || nueva.contains("5") ||
                nueva.contains("6") || nueva.contains("7") ||
                nueva.contains("8") || nueva.contains("9");
        return tieneMayuscula && tieneNumero;
    }

    public boolean cambiarPassword(String actual, String nueva) {
        if (bloqueado) return false;
        if (!password.equals(actual)) return false;
        if (!validarPasswordSegura(nueva)) return false;
        password = nueva;
        return true;
    }
}