
package restaurante;

import javax.swing.*;

public class RestauranteForm {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JButton btnRegistrar;
    private JButton btnUltimo;
    private JButton btnAtender;
    private JButton btnMostrar;
    private JTextArea areaPedidos;
    private JTextField txtCliente;
    private JComboBox<String> cboProducto;
    private JLabel lblEstado;

    private final ListaPedidos lista = new ListaPedidos();
    private final ColaPedidos cola = new ColaPedidos();
    private final PilaPedidos pila = new PilaPedidos();

    private int contador = 1;

    public RestauranteForm() {
        // Agregar productos al menú
        cboProducto.addItem("Hamburguesa");
        cboProducto.addItem("Pizza");
        cboProducto.addItem("Pollo a la brasa");
        cboProducto.addItem("Salchipapa");
        cboProducto.addItem("Gaseosa");

        // Acciones de los botones
        btnRegistrar.addActionListener(e -> registrarPedido());
        btnUltimo.addActionListener(e -> mostrarUltimoPedido());
        btnAtender.addActionListener(e -> atenderPedido());
        btnMostrar.addActionListener(e -> mostrarPedidos());
    }

    // Registrar un nuevo pedido
    private void registrarPedido() {
        String cliente = txtCliente.getText().trim();
        String seleccion = (String) cboProducto.getSelectedItem();

        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Ingrese el nombre del cliente."
            );
            return;
        }

        if (seleccion == null) {
            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Seleccione un producto."
            );
            return;
        }

        // Quitar el precio del nombre seleccionado
        String producto = seleccion.split(" - S/")[0].trim();

        double precio;

        switch (producto) {
            case "Hamburguesa":
                precio = 10.00;
                break;
            case "Pizza":
                precio = 25.00;
                break;
            case "Pollo a la brasa":
                precio = 50.00;
                break;
            case "Salchipapa":
                precio = 10.00;
                break;
            case "Gaseosa":
                precio = 5.00;
                break;
            default:
                JOptionPane.showMessageDialog(
                        panelPrincipal,
                        "No se encontró el precio del producto: " + producto
                );
                return;
        }

        Pedido pedido = new Pedido(
                contador++, cliente, producto, precio
        );

        lista.agregar(pedido);
        cola.encolar(pedido);
        pila.apilar(pedido);

        lblEstado.setText("Pedido registrado correctamente.");
        txtCliente.setText("");

        mostrarPedidos();
    }

    // Mostrar el último pedido registrado
    private void mostrarUltimoPedido() {
        Pedido pedido = pila.cima();

        if (pedido == null) {
            lblEstado.setText("No hay pedidos registrados.");
            return;
        }

        JOptionPane.showMessageDialog(
                panelPrincipal,
                "Último pedido registrado:\n\n" +
                        "Cliente: " + pedido.cliente + "\n" +
                        "Producto: " + pedido.producto + "\n" +
                        "Precio: S/ " +
                        String.format("%.2f", pedido.precio) + "\n" +
                        "Estado: " + pedido.estado
        );
    }

    // Atender el siguiente pedido pendiente
    private void atenderPedido() {
        Pedido pedido = cola.desencolar();

        if (pedido == null) {
            lblEstado.setText("No hay pedidos pendientes.");
            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "No hay pedidos pendientes por atender."
            );
            return;
        }

        pedido.estado = "Atendido";

        lblEstado.setText(
                "Se atendió el pedido de " + pedido.cliente
        );

        // Mostrar todos, incluidos los atendidos
        btnMostrar.addActionListener(e -> mostrarPedidos());
    }

    // Mostrar todos los pedidos registrados
    private void mostrarPedidos() {
        StringBuilder texto = new StringBuilder();

        for (Pedido pedido : lista.obtenerTodos()) {
            texto.append("Pedido N.º ")
                    .append(pedido.id)
                    .append("\nCliente: ")
                    .append(pedido.cliente)
                    .append("\nProducto: ")
                    .append(pedido.producto)
                    .append("\nPrecio: S/ ")
                    .append(String.format(java.util.Locale.US, "%.2f", pedido.precio))
                    .append("\nEstado: ")
                    .append(pedido.estado)
                    .append("\n------------------------------\n");
        }

        if (texto.length() == 0) {
            texto.append("No hay pedidos registrados.");
        }

        areaPedidos.setText(texto.toString());
        areaPedidos.setCaretPosition(0);
    }

    // Entregar el panel a la ventana principal
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
