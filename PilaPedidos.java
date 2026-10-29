package restaurante;

public class PilaPedidos {
    private NodoPedido cima;

    public void apilar(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public Pedido cima() {
        if (cima == null) {
            return null;
        }

        return cima.dato;
    }
}