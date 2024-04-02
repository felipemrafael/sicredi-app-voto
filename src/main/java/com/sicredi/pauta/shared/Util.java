package com.sicredi.pauta.shared;

import java.util.Collection;

public class Util {

    public static boolean estaNuloOuVazio(Object valor) {
        return estaNulo(valor) || estaVazio(valor);
    }

    public static boolean estaNulo(Object valor) {
        return valor == null;
    }

    public static boolean estaVazio(Object valor) {
        return valor instanceof Collection ? ((Collection) valor).isEmpty() : "".equals(valor.toString().trim());
    }

}
