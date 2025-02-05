package clases;

/**
 * Esta clase representa una fecha con dia, mes y anio.
 * 
 * Proporciona metodos para obtener y modificar estos valores.
 * 
 * @author Luciano Francisco Soto
 * @author Baltazar Patane
 * @version 1.0
 */
public class Fecha {

    /** El dia de la fecha. */
    private int dia;

    /** El mes de la fecha. */
    private int mes;

    /** El anio de la fecha. */
    private int anio;

    /**
     * Constructor que inicializa la fecha con dia, mes y anio.
     * 
     * @param dia El dia de la fecha.
     * @param mes El mes de la fecha.
     * @param anio El anio de la fecha.
     */
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /**
     * Obtiene el dia de la fecha.
     * 
     * @return El dia de la fecha.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Establece el dia de la fecha.
     * 
     * @param dia El nuevo dia de la fecha.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Obtiene el mes de la fecha.
     * 
     * @return El mes de la fecha.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Establece el mes de la fecha.
     * 
     * @param mes El nuevo mes de la fecha.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Obtiene el anio de la fecha.
     * 
     * @return El anio de la fecha.
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Establece el anio de la fecha.
     * 
     * @param anio El nuevo año de la fecha.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
}
