package Proyecto1entregable;

import java.awt.image.BufferedImage;

/**
 * Esta clase representa una criptomoneda, que es un tipo de moneda digital descentralizada.
 * 
 * @author Luciano Francisco Soto
 * @author Baltazar Patane
 * @version 1.0
 */
public class Criptomoneda extends Moneda {

    /** El suministro maximo de unidades que pueden existir de esta criptomoneda. */
    private int stock;

    /**
     * Constructor para crear instancia de una nueva criptomoneda.
     *
     * @param nombre El nombre de la criptomoneda.
     * @param precioRespectoDolar El precio de la criptomoneda respecto del dolar.
     * @param codigoISO El codigo ISO de la criptomoneda.
     * @param fechaCreacion La fecha de creacion de la criptomoneda.
     * @param simbolo El simbolo de la criptomoneda.
     * @param suministroMaximo El numero maximo de unidades de la criptomoneda que pueden existir.
     */
    public Criptomoneda(String nombre, float precioRespectoDolar, String codigoISO, Fecha fechaCreacion,
                        BufferedImage simbolo, int stock) {
        super(nombre, precioRespectoDolar, codigoISO, fechaCreacion, simbolo);
        this.stock = stock;
    }
    /**
     * Devuelve el suministro maximo de la criptomoneda.
     *
     * @return El numero de suministro maximo.
     */
    public int getSuministroMaximo() {
        return stock;
    }

    /**
     * Establece el suministro maximo de la criptomoneda.
     *
     * @param suministroMaximo El numero de suministro maximo.
     */
    public void setSuministroMaximo(int stock) {
        this.stock = stock;
    }
}
