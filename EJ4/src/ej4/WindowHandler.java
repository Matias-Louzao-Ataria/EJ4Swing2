package ej4;

import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class WindowHandler extends WindowAdapter{

    private Clase c;

    public WindowHandler(Clase c) {
        super();
        this.c = c;
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        if(!this.c.getTxa().getText().equals("1er número") && !this.c.getTxa2().getText().equals("2ndo número")){
            if(guardar()){
                JOptionPane.showMessageDialog(this.c, "Se ha guardado correctamente.");
                c.dispose();
            }else{
                JOptionPane.showMessageDialog(this.c, "No se ha podido guardar correctamente.");
                c.dispose();
            }
        }else{
            this.c.dispose();
        }
    }

    public boolean guardar(){
        try (PrintWriter esc = new PrintWriter(new FileWriter(this.c.getFile()),true)) {
            esc.append(this.c.getTxa().getText()+"\n");
            esc.append(this.c.getTxa2().getText()+"\n");
            esc.append(this.c.getLbloperacion().getText()+"\n");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}