package com.jgsolucion.prueba.model;

import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import java.util.Date;
import java.util.Objects;

public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String dui;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, Date fechaNacimiento, String dui) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dui = dui;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(getId(), usuario.getId()) && Objects.equals(getNombre(), usuario.getNombre()) && Objects.equals(getApellido(), usuario.getApellido()) && Objects.equals(getFechaNacimiento(), usuario.getFechaNacimiento()) && Objects.equals(getDui(), usuario.getDui());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellido(), getFechaNacimiento(), getDui());
    }

    @Override
    public String toString() {
        return "usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", dui='" + dui + '\'' +
                '}';
    }
}
