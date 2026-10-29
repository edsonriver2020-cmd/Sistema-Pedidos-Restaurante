package restaurante;

public class ColaPedidos {
    private NodoPedido frente;
    private NodoPedido fin;
    private int cantidad;

    public void encolar(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);

        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }

        cantidad++;
    }

    public Pedido desencolar() {
        if (frente == null) {
            return null;
        }

        Pedido pedido = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        cantidad--;
        return pedido;
    }

    public int tamano() {
        return cantidad;
    }
}