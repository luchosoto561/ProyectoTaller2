package clasesFueradeUso;

import java.awt.image.BufferedImage;

/**
 * Esta clase abstracta representa una moneda generica, la cual puede ser una Moneda Fiat o Criptomoneda.
 * Proporciona los atributos comunes entre diferentes tipos de monedas, como el nombre, el precio respecto
 * al dolar, el codigo ISO, la fecha de creacion y el simbolo grafico.
 * 
 * @author Luciano Francisco Soto
 * @author Baltazar Patane
 * @version 1.0
 */
public abstract class Moneda {

    /** El nombre de la moneda. */
    private String nombre;

    /** El precio de la moneda respecto al dolar estadounidense. */
    private float precioRespectoDolar;

    /** El codigo ISO que representa la moneda. */
    private String codigoISO;

    /** La fecha de creacion de la moneda. */
    private Fecha fechaCreacion;

    /** El simbolo grafico que representa la moneda. */
    private BufferedImage simbolo;

    /**
     * Constructor para crear una moneda con los atributos especificados.
     * 
     * @param nombre El nombre de la moneda.
     * @param precioRespectoDolar El precio de la moneda respecto al dolar.
     * @param codigoISO El codigo ISO que identifica a la moneda.
     * @param fechaCreacion La fecha en que fue creada la moneda.
     * @param simbolo El simbolo grafico que representa la moneda.
     */
    public Moneda(String nombre, float precioRespectoDolar, String codigoISO, Fecha fechaCreacion, BufferedImage simbolo) {
        super();
        this.nombre = nombre;
        this.precioRespectoDolar = precioRespectoDolar;
        this.codigoISO = codigoISO;
        this.fechaCreacion = fechaCreacion;
        this.simbolo = simbolo;
    }

    /**
     * Obtiene el nombre de la moneda.
     * 
     * @return El nombre de la moneda.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la moneda.
     * 
     * @param nombre El nuevo nombre de la moneda.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio de la moneda respecto al dolar.
     * 
     * @return El precio de la moneda respecto al dolar.
     */
    public float getPrecioRespectoDolar() {
        return precioRespectoDolar;
    }

    /**
     * Establece el precio de la moneda respecto al dolar.
     * 
     * @param precioRespectoDolar El nuevo precio de la moneda respecto al dolar.
     */
    public void setPrecioRespectoDolar(float precioRespectoDolar) {
        this.precioRespectoDolar = precioRespectoDolar;
    }

    /**
     * Obtiene el codigo ISO de la moneda.
     * 
     * @return El codigo ISO de la moneda.
     */
    public String getCodigoISO() {
        return codigoISO;
    }

    /**
     * Establece el codigo ISO de la moneda.
     * 
     * @param codigoISO El nuevo codigo ISO de la moneda.
     */
    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    /**
     * Obtiene la fecha de creacion de la moneda.
     * 
     * @return La fecha de creacion de la moneda.
     */
    public Fecha getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creacion de la moneda.
     * 
     * @param fechaCreacion La nueva fecha de creacion de la moneda.
     */
    public void setFechaCreacion(Fecha fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el simbolo grofico de la moneda.
     * 
     * @return El simbolo grofico de la moneda.
     */
    public BufferedImage getSimbolo() {
        return simbolo;
    }

    /**
     * Establece el simbolo grafico de la moneda.
     * 
     * @param simbolo El nuevo simbolo grafico de la moneda.
     */
    public void setSimbolo(BufferedImage simbolo) {
        this.simbolo = simbolo;
    }
}
	

	
