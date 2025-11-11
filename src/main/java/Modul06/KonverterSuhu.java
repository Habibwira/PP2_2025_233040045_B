package Modul06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KonverterSuhu extends JFrame implements ActionListener {
    private final JTextField inputField;
    private final JComboBox<String> fromCombo;
    private final JComboBox<String> toCombo;
    private final JTextField outputField;

    public KonverterSuhu() {
        setTitle("Konverter Suhu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblInput = new JLabel("Masukkan suhu:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblInput, gbc);

        inputField = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        add(inputField, gbc);

        fromCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        add(fromCombo, gbc);

        toCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        gbc.gridx = 1; gbc.gridy = 1;
        add(toCombo, gbc);

        JButton convertBtn = new JButton("Convert");
        convertBtn.addActionListener(this);
        gbc.gridx = 2; gbc.gridy = 1;
        add(convertBtn, gbc);

        JLabel lblOutput = new JLabel("Hasil:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblOutput, gbc);

        outputField = new JTextField();
        outputField.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        add(outputField, gbc);

        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(ev -> {
            inputField.setText("");
            outputField.setText("");
        });
        gbc.gridx = 2; gbc.gridy = 3;
        add(clearBtn, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inText = inputField.getText().trim();
        if (inText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nilai suhu terlebih dahulu.", "Input kosong", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double value;
        try {
            value = Double.parseDouble(inText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid.", "Format salah", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String from = (String) fromCombo.getSelectedItem();
        String to = (String) toCombo.getSelectedItem();
        double result = convertTemperature(value, from, to);

        // tampilkan hasil rapi (jika integer -> tanpa .0)
        if (Math.abs(result - Math.round(result)) < 1e-12) {
            outputField.setText(String.valueOf((long)Math.round(result)) + " " + unitSuffix(to));
        } else {
            outputField.setText(String.format("%.4f %s", result, unitSuffix(to)));
        }
    }

    private String unitSuffix(String unit) {
        switch (unit) {
            case "Celsius": return "°C";
            case "Fahrenheit": return "°F";
            case "Kelvin": return "K";
            default: return "";
        }
    }

    private double convertTemperature(double val, String from, String to) {
        if (from.equals(to)) return val;

        double celsius;
        // convert from source to celsius
        switch (from) {
            case "Celsius": celsius = val; break;
            case "Fahrenheit": celsius = (val - 32) * 5.0/9.0; break;
            case "Kelvin": celsius = val - 273.15; break;
            default: celsius = val; break;
        }

        // convert from celsius to target
        switch (to) {
            case "Celsius": return celsius;
            case "Fahrenheit": return celsius * 9.0/5.0 + 32;
            case "Kelvin": return celsius + 273.15;
            default: return celsius;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KonverterSuhu k = new KonverterSuhu();
            k.setVisible(true);
        });
    }
}
