package com.labs.lab4;
//Обратите внимание на то, что у Java нет подходящего типа данных для
//комплексных чисел, поэтому необходимо будет реализовать итеративную
//функцию, используя отдельные переменные для действительной и мнимой
//частей.

public class Complex {
    private double re, im;
    Complex(){
        re=0;
        im=0;
    }
    Complex (double x, double y){
        re=x;
        im=y;
    }
    // Метод возведения в степень, т.к. встроенный понижает быстродействие
    Complex Pow(){
        return(new Complex(re*re-im*im, 2*re*im));
    }
    Complex Plus(Complex complex){
        return(new Complex(complex.re+re, complex.im+im));
    }
    double modul(){
        return(re*re+im*im);
    }
}
