import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PruebaCuenta extends JFrame {
    private JTextField saldoField;
    private JTextField tasaField;
    private JTextField cantidadDepositarField;
    private JTextField cantidadRetirarField;
    private JTextArea resultadoArea;

    public PruebaCuenta() {
        setTitle("Prueba de Cuenta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        saldoField = new JTextField(10);
        tasaField = new JTextField(10);
        cantidadDepositarField = new JTextField(10);
        cantidadRetirarField = new JTextField(10);
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        JButton btnProcesar = new JButton("Procesar");
        btnProcesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarCuenta();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("Saldo inicial:"));
        panel.add(saldoField);
        panel.add(new JLabel("Tasa de interés:"));
        panel.add(tasaField);
        panel.add(new JLabel("Cantidad a consignar:"));
        panel.add(cantidadDepositarField);
        panel.add(new JLabel("Cantidad a retirar:"));
        panel.add(cantidadRetirarField);
        panel.add(btnProcesar);

        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void procesarCuenta() {
        try {
            float saldoInicial = Float.parseFloat(saldoField.getText());
            float tasaInteres = Float.parseFloat(tasaField.getText());
            float cantidadDepositar = Float.parseFloat(cantidadDepositarField.getText());
            float cantidadRetirar = Float.parseFloat(cantidadRetirarField.getText());

            CuentaAhorros cuenta = new CuentaAhorros(saldoInicial, tasaInteres);
            cuenta.consignar(cantidadDepositar);
            cuenta.retirar(cantidadRetirar);
            cuenta.extractoMensual();

            resultadoArea.setText("Saldo: $" + cuenta.getSaldo() + "\n");
            resultadoArea.append("Comisión mensual: $" + cuenta.getComisiónMensual() + "\n");
            resultadoArea.append("Número de transacciones: " + (cuenta.getNúmeroConsignaciones() + cuenta.getNúmeroRetiros()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PruebaCuenta pruebaCuenta = new PruebaCuenta();
                pruebaCuenta.setVisible(true);
            }
        });
    }
}
