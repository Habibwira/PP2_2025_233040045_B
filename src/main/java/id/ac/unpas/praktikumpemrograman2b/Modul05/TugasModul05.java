/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemrograman2b.Modul05;


import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public class TugasModul05 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                frame.setLayout(new BorderLayout());
                
                JLabel label = new JLabel("Label ada di Atas (NORTH)" , JLabel.CENTER);
                
                JButton btnNorth = new JButton ("NORTH");
                JButton btnSouth = new JButton ("SOUTH");
                JButton btnWest = new JButton ("WEST");
                JButton btnEast = new JButton ("EAST");
                JButton btnCenter = new JButton ("CENTER");


                
                btnNorth.addActionListener(e -> {
                label.setText("Tombol NORTH dikliik!");
            });
                
                btnSouth.addActionListener(e -> {
                label.setText("Tombol SOUTH dikliik!");
            });
                
                btnWest.addActionListener(e -> {
                label.setText("Tombol WEST dikliik!");
            });
                
                btnEast.addActionListener(e -> {
                label.setText("Tombol EAST dikliik!");
            });
                
                btnCenter.addActionListener(e -> {
                label.setText("Tombol CENTER dikliik!");
            });
                
                frame.add(label, BorderLayout.NORTH);
                frame.add(btnSouth, BorderLayout.SOUTH);
                frame.add(btnWest, BorderLayout.WEST);
                frame.add(btnEast, BorderLayout.EAST);
                frame.add(btnCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });

    }    
}