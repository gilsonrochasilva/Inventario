package br.com.inventario.model.emuns;

/**
 * Created by GilsonRocha on 26/12/13.
 */
public enum Local {
    IT, LOJA, DEPOSITO;

    @Override
    public String toString() {
        switch(this) {
            case IT: return "IT";
            case LOJA: return "Loja";
            case DEPOSITO: return "Depósito";
        }

        return "";
    }
}
