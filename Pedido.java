
package restaurante;

public class Pedido {
    int id;
    String cliente;
    String producto;
    double precio;
    String estado;

    public Pedido(int id, String cliente,
                  String producto, double precio) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
        this.estado = "Pendiente";
    }
}
