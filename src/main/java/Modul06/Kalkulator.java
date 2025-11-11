package Modul06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kalkulator extends JFrame implements ActionListener {
    private final JTextField display;
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public Kalkulator() {
        setTitle("Kalkulator Sederhana");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5,5));

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel tombolPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] tombol = {
            "C", "", "", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "0", ".", "="
        };

        for (String t : tombol) {
            if (t.equals("")) {
                tombolPanel.add(new JLabel()); // spacer
            } else {
                JButton btn = new JButton(t);
                btn.setFont(new Font("SansSerif", Font.PLAIN, 18));
                btn.addActionListener(this);
                tombolPanel.add(btn);
            }
        }

        add(tombolPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("C")) {
            display.setText("0");
            firstOperand = 0;
            operator = "";
            startNewNumber = true;
            return;
        }

        if ("0123456789.".indexOf(cmd) >= 0) {
            if (startNewNumber) {
                // start new number (replace display)
                if (cmd.equals(".")) {
                    display.setText("0.");
                } else {
                    display.setText(cmd);
                }
                startNewNumber = false;
            } else {
                // append digit if valid (avoid multiple dots)
                if (cmd.equals(".") && display.getText().contains(".")) return;
                display.setText(display.getText() + cmd);
            }
            return;
        }

        if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/")) {
            try {
                firstOperand = Double.parseDouble(display.getText());
            } catch (NumberFormatException ex) {
                firstOperand = 0;
            }
            operator = cmd;
            startNewNumber = true;
            return;
        }

        if (cmd.equals("=")) {
            try {
                double secondOperand = Double.parseDouble(display.getText());
                double result = calculate(firstOperand, secondOperand, operator);
                // format result: if integer-like, show without .0
                if (Math.abs(result - Math.round(result)) < 1e-12) {
                    display.setText(String.valueOf((long)Math.round(result)));
                } else {
                    display.setText(String.valueOf(result));
                }
            } catch (ArithmeticException ex) {
                display.setText("Error");
            } catch (NumberFormatException ex) {
                display.setText("0");
            }
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (Math.abs(b) < 1e-12) throw new ArithmeticException("Division by zero");
                return a / b;
            default: return b;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Kalkulator k = new Kalkulator();
            k.setVisible(true);
        });
    }
}
