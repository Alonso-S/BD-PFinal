package modelo;

import java.time.LocalDate;


public class Jugador {
    private int codigo;
    private String nick;
    private String nombre;
    private String posicion;
    private LocalDate fechaIngresoEquipo;
    private int codigoEquipo;
    
	public void setFechaIngresoEquipo(LocalDate fechaIngresoEquipo) {
		this.fechaIngresoEquipo = fechaIngresoEquipo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public LocalDate getFechaIngresoEquipo() {
		return fechaIngresoEquipo;
	}

	public int getCodigoEquipo() {
		return codigoEquipo;
	}
	public void setCodigoEquipo(int codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

}
