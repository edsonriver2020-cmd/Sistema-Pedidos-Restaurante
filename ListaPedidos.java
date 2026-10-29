package restaurante;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidos {
    private NodoPedido cabeza;

    public void agregar(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoPedido actual = cabeza;

        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
    }

    public List<Pedido> obtenerTodos() {
        List<Pedido> resultado = new ArrayList<>();
        NodoPedido actual = cabeza;

        while (actual != null) {
            resultado.add(actual.dato);
            actual = actual.siguiente;
        }

        return resultado;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}