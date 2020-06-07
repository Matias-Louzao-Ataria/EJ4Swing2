package ej4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Clase extends JFrame implements ItemListener, ActionListener {

    private JTextArea txa = new JTextArea("1er número");
    private JTextArea txa2 = new JTextArea("2ndo número");
    private JLabel lbloperacion = new JLabel("+");
    private JLabel lbligual = new JLabel("=");
    private JLabel lblerror = new JLabel();
    private JRadioButton suma = new JRadioButton("+");
    private JRadioButton resta = new JRadioButton("-");
    private JRadioButton multiplicacion = new JRadioButton("*");
    private JRadioButton division = new JRadioButton("/");
    private JButton btnoperacion = new JButton("Calcular");
    private ButtonGroup operaciones = new ButtonGroup();
    private JComboBox<String> decimal = new JComboBox<String>();
    private String operacion = "";
    private int x = 30, y = 60;
    private File file = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"guardado.txt");
    private int decimales;

    public Clase() {
        super("EJ4");
        this.setLayout(null);

        operaciones.add(suma);
        suma.setSize(suma.getPreferredSize());
        suma.setLocation(x, y);
        suma.addItemListener(this);
        this.add(suma);

        x += 40;

        operaciones.add(resta);
        resta.setSize(resta.getPreferredSize());
        resta.setLocation(x, y);
        ;
        resta.addItemListener(this);
        this.add(resta);

        x += 40;

        operaciones.add(multiplicacion);
        multiplicacion.setSize(multiplicacion.getPreferredSize());
        multiplicacion.setLocation(x, y);
        multiplicacion.addItemListener(this);
        this.add(multiplicacion);

        x += 40;

        operaciones.add(division);
        division.setSize(division.getPreferredSize());
        division.setLocation(x, y);
        division.addItemListener(this);
        this.add(division);

        suma.setSelected(true);

        txa.setSize(txa.getPreferredSize());
        txa.setLocation(15, 30);
        this.add(txa);

        lbloperacion.setSize(lbloperacion.getPreferredSize());
        lbloperacion.setLocation(110, 30);
        this.add(lbloperacion);

        txa2.setSize(txa2.getPreferredSize());
        txa2.setLocation(135, 30);
        this.add(txa2);

        btnoperacion.setSize(btnoperacion.getPreferredSize());
        btnoperacion.setLocation(65, 100);
        btnoperacion.addActionListener(this);
        this.add(btnoperacion);

        lbligual.setSize(lbligual.getPreferredSize());
        lbligual.setLocation(265, 30);
        this.add(lbligual);

        lblerror.setSize(lblerror.getPreferredSize());
        lblerror.setLocation(65, 130);
        this.add(lblerror);

        decimal.setSize(decimal.getPreferredSize());
        decimal.setLocation(65, 150);
        for(int i = 0; i < 6;i++){
            this.decimal.addItem(String.valueOf(i));
        }
        decimal.setSize(decimal.getPreferredSize());
        decimal.setSelectedIndex(0);
        decimal.addItemListener(this);
        this.add(decimal);

        this.addWindowListener(new WindowHandler(this));

        if(file.exists() && file.length() != 0){
            try (Scanner sc = new Scanner(file)) {
                this.txa.setText(sc.nextLine());
                this.txa2.setText(sc.nextLine());
                this.lbloperacion.setText(sc.nextLine());
                switch(this.lbloperacion.getText()){
                    case "+":
                        this.suma.setSelected(true);
                        break;

                    case "-":
                        this.resta.setSelected(true);
                        break;

                    case "*":
                        this.multiplicacion.setSelected(true);
                        break;

                    case "/":
                        this.division.setSelected(true);
                        break;
                }
            } catch (FileNotFoundException e) {
               
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {

        if (arg0.getSource() == suma) {

            this.lbloperacion.setText("+");
            operacion = "+";

        } else if (arg0.getSource() == resta) {

            this.lbloperacion.setText("-");
            operacion = "-";
            
        } else if (arg0.getSource() == multiplicacion) {

            this.lbloperacion.setText("*");
            operacion = "*";

        } else if (arg0.getSource() == division) {
            this.lbloperacion.setText("/");
            operacion = "/";

        } else {
            decimales = Integer.parseInt(this.decimal.getSelectedItem().toString());
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        lblerror.setForeground(null);
        lblerror.setText("");
        lblerror.setSize(lblerror.getPreferredSize());
        this.lbligual.setText("=");
        try {
            double res = 0;
            switch (operacion) {
                case "+":
                    res = Double.parseDouble(this.txa.getText()) + Double.parseDouble(this.txa2.getText());
                    break;
                case "-":
                    res = Double.parseDouble(this.txa.getText()) - Double.parseDouble(this.txa2.getText());
                    break;
                case "*":
                    res = Double.parseDouble(this.txa.getText()) * Double.parseDouble(this.txa2.getText());
                    break;
                case "/":
                    res = Double.parseDouble(this.txa.getText()) / Double.parseDouble(this.txa2.getText());
                    break;
                default:
                    break;
                }
                if(Double.isNaN(res) || Double.isInfinite(res)){
                    lblerror.setText("Operación no válida!");
                    lblerror.setForeground(Color.red);
                    lblerror.setSize(lblerror.getPreferredSize());
                    JOptionPane.showMessageDialog(this,"Introduce una operación valida a realizar.");
                }else{
                    this.lbligual.setText(this.lbligual.getText() + " "+ String.format("%"+"."+this.decimales+"f",res));
                    this.lbligual.setSize(lbligual.getPreferredSize());
                }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "No se han introducido números válidos.");
        }
    }

    public File getFile() {
        return file;
    }

    public JTextArea getTxa() {
        return txa;
    }

    public JTextArea getTxa2() {
        return txa2;
    }

    public JLabel getLbloperacion() {
        return lbloperacion;
    }  
}