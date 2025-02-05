package clasesFueradeUso;

import java.awt.image.BufferedImage;

/**
 * Esta clase representa una moneda fiduciaria, es decir, una moneda emitida por un gobierno,
 * cuyo valor esta respaldado por el emisor y no por un valor intrinseco como el oro.
 * 
 * Hereda de la clase abstracta {@link Moneda}, que proporciona los atributos y metodos comunes
 * a todas las monedas.
 * 
 * @author Luciano Francisco Soto
 * @author Baltazar Patane
 * @version 1.0
 */
public class Fiat extends Moneda {

    /** El pais emisor de la moneda fiduciaria. */
    private String paisEmisor;

    /** La tasa de inflacion actual de la moneda. */
    private float tasainflacion;

    /** La cantidad de moneda actualmente en circulacion. */
    private float circulacionActual;

    /**
     * Constructor que inicializa los atributos de la moneda fiduciaria.
     * 
     * @param nombre El nombre de la moneda.
     * @param precioRespectoDolar El precio de la moneda respecto al dolar.
     * @param codigoISO El codigo ISO que identifica la moneda.
     * @param fechaCreacion La fecha en que fue creada la moneda.
     * @param simbolo El simbolo grafico de la moneda.
     * @param paisEmisor El pais que emite la moneda.
     * @param tasainflacion La tasa de inflacion de la moneda.
     * @param circulacionActual La cantidad de moneda en circulacion.
     */
    public Fiat(String nombre, float precioRespectoDolar, String codigoISO, Fecha fechaCreacion, BufferedImage simbolo,
                String paisEmisor, float tasainflacion, float circulacionActual) {
        super(nombre, precioRespectoDolar, codigoISO, fechaCreacion, simbolo);
        this.paisEmisor = paisEmisor;
        this.tasainflacion = tasainflacion;
        this.circulacionActual = circulacionActual;
    }

    /**
     * Obtiene el pais emisor de la moneda.
     * 
     * @return El pais emisor de la moneda.
     */
    public String getPaisEmisor() {
        return paisEmisor;
    }

    /**
     * Establece el pais emisor de la moneda.
     * 
     * @param paisEmisor El nuevo país emisor de la moneda.
     */
    public void setPaisEmisor(String paisEmisor) {
        this.paisEmisor = paisEmisor;
    }

    /**
     * Obtiene la tasa de inflacion actual de la moneda.
     * 
     * @return La tasa de inflacion actual.
     */
    public float getTasainflacion() {
        return tasainflacion;
    }

    /**
     * Establece la tasa de inflacion de la moneda.
     * 
     * @param tasainflacion La nueva tasa de inflacion de la moneda.
     */
    public void setTasainflacion(float tasainflacion) {
        this.tasainflacion = tasainflacion;
    }

    /**
     * Obtiene la cantidad actual de moneda en circulacion.
     * 
     * @return La cantidad de moneda en circulacion.
     */
    public float getCirculacionActual() {
        return circulacionActual;
    }

    /**
     * Establece la cantidad de moneda en circulacion.
     * 
     * @param circulacionActual La nueva cantidad de moneda en circulacion.
     */
    public void setCirculacionActual(float circulacionActual) {
        this.circulacionActual = circulacionActual;
    }
}
