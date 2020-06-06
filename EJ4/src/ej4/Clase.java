package ej4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
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
    private DecimalFormat formatter;
    private String operacion = "";
    private int x = 30, y = 60;
    private File file = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"guardado.txt");

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
        decimal.addItem("0");
        decimal.addItem("1");
        decimal.addItem("2");
        decimal.addItem("3");
        decimal.addItem("4");
        decimal.addItem("5");
        decimal.setSize(decimal.getPreferredSize());
        decimal.setSelectedIndex(0);
        decimal.addItemListener(this);
        this.add(decimal);

        formatter = new DecimalFormat("0");

        this.addWindowListener(new WindowHandler(this));

        if(file.exists() && file.length() != 0){
            try (Scanner sc = new Scanner(file)) {
                this.txa.setText(sc.nextLine());
                this.txa2.setText(sc.nextLine());
                this.lbloperacion.setText(sc.nextLine());
            } catch (Exception e) {

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
            String s = "";
            for(int i = 0;i < Integer.parseInt(this.decimal.getSelectedItem().toString()) && Integer.parseInt(this.decimal.getSelectedItem().toString()) != 0;i++){
                s+="0";
            }
            formatter = new DecimalFormat("0." + s);
            if(Integer.parseInt(this.decimal.getSelectedItem().toString()) == 0){
                formatter = new DecimalFormat("0");
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        lblerror.setForeground(null);
        lblerror.setText("");
        lblerror.setSize(lblerror.getPreferredSize());
        this.lbligual.setText("=");
        try {
            if(Integer.parseInt(this.txa2.getText()) == 0 && this.operacion == "/"){
                lblerror.setText("División entre 0");
                lblerror.setForeground(Color.red);
                lblerror.setSize(lblerror.getPreferredSize());
                JOptionPane.showMessageDialog(this,"Selecciona un número que no sea cero para dividir");
            }else{
                switch (operacion) {
                    case "+":
                        this.lbligual.setText(this.lbligual.getText() + " "+ formatter.format(Double.parseDouble(this.txa.getText()) + Double.parseDouble(this.txa2.getText())));
                        this.lbligual.setSize(lbligual.getPreferredSize());
                        break;
                    case "-":
                        this.lbligual.setText(this.lbligual.getText() + " "+ formatter.format(Double.parseDouble(this.txa.getText()) - Double.parseDouble(this.txa2.getText())));
                        this.lbligual.setSize(lbligual.getPreferredSize());
                        break;
                    case "*":
                        this.lbligual.setText(this.lbligual.getText() + " "+ formatter.format(Double.parseDouble(this.txa.getText()) * Double.parseDouble(this.txa2.getText())));
                        this.lbligual.setSize(lbligual.getPreferredSize());
                        break;
                    case "/":
                        this.lbligual.setText(this.lbligual.getText() + " "+ formatter.format(Double.parseDouble(this.txa.getText()) / Double.parseDouble(this.txa2.getText())));
                        this.lbligual.setSize(lbligual.getPreferredSize());
                        break;
                    default:
                        break;
                }
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