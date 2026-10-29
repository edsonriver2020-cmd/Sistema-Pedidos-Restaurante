package restaurante;

public class NodoPedido {
    Pedido dato;
    NodoPedido siguiente;

    public NodoPedido(Pedido dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}