
package restaurante;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RestauranteApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestauranteForm formulario = new RestauranteForm();

            JFrame ventana = new JFrame("Sistema de Pedidos del Restaurante");

            ventana.setContentPane(formulario.getPanelPrincipal());
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
