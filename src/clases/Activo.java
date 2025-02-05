package clases;

/**
 * Esta clase representa un activo que incluye una cantidad de criptomonedas, una criptomoneda especifica
 * y una direccion asociada.
 * 
 * @author Luciano Francisco Soto
 * @author Baltazar Patane
 * @version 1.0
 */
public class Activo {

    /** Cantidad de criptomonedas que posee el activo. */
    private int cantidad;

    /** La criptomoneda asociada al activo. */
    private Criptomoneda cripto;

    /** Direccion del activo  */
    private String direccion;

    /**
     * Constructor para crear una instancia de Activo.
     *
     * @param cantidad La cantidad de criptomonedas que posee el activo.
     * @param cripto La criptomoneda asociada al activo.
     * @param direccion La direccion de la criptomoneda.
     */
    public Activo(int cantidad, Criptomoneda cripto, String direccion) {
        this.cantidad = cantidad;
        this.cripto = cripto;
        this.direccion = direccion;
    }

    /**
     * Devuelve la cantidad de criptomonedas que posee el activo.
     *
     * @return La cantidad de criptomonedas.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de criptomonedas que posee el activo.
     *
     * @param cantidad La nueva cantidad de criptomonedas.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve la criptomoneda asociada al activo.
     *
     * @return La criptomoneda asociada.
     */
    public Criptomoneda getCripto() {
        return cripto;
    }

    /**
     * Establece una nueva criptomoneda asociada al activo.
     *
     * @param cripto La nueva criptomoneda a asociar.
     */
    public void setCripto(Criptomoneda cripto) {
        this.cripto = cripto;
    }

    /**
     * Devuelve la direccion del activo.
     *
     * @return La direccion del activo.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece una nueva direccion para el activo.
     *
     * @param direccion La nueva direccion del activo.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
