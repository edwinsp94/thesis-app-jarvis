package com.android.jarvis.reconocedor;

public class Complejo {

    private double real;
    private double imag;

    public Complejo() {
        this.real = 0;
        this.imag = 0;
    }

    public Complejo(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public void setComplejo(Complejo c){
        this.real = c.getReal();
        this.imag = c.getImag();
    }

    public Complejo sumar(Complejo b) {
        Complejo c = new Complejo();
        c.real = this.real + b.real;
        c.imag = this.imag + b.imag;
        return c;
    }

    public Complejo restar(Complejo b) {
        Complejo c = new Complejo();
        c.real = this.real - b.real;
        c.imag = this.imag - b.imag;
        return c;
    }

    public Complejo multiplicar(Complejo b) {
        Complejo c = new Complejo();
        c.real = this.real*b.real - this.imag*b.imag;
        c.imag = this.real*b.imag + this.imag*b.real;
        return c;
    }

    public double getModulo() {
        return Math.sqrt(Math.pow(real,2) + Math.pow(imag,2));
    }

    public double getModulo2() {
        return Math.pow(real,2) + Math.pow(imag,2);
    }

}
