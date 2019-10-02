package juego.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu implements ActionListener{
	
	JFrame pantalla = null;
	JMenuBar javaMenuBar = null;
	JMenu jmFile = null;
	JMenu jmOptions = null;
	
	Menu() {
		
		//Se crea la pantalla con su tamaño
	    pantalla = new JFrame("Menu Demo");
	    pantalla.setSize(220, 200);
	    
	    //Se definen las opciones por default
	    pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    javaMenuBar = new JMenuBar();

	    jmFile = new JMenu("File");
	    
	    //Se crean los items de la pantalla
	    JMenuItem jmiOpen = new JMenuItem("Open");
	    JMenuItem jmiClose = new JMenuItem("Close");
	    JMenuItem jmiSave = new JMenuItem("Save");
	    JMenuItem jmiExit = new JMenuItem("Exit");
	    
	    //Se agregan los items al archivo
	    jmFile.add(jmiOpen);
	    jmFile.add(jmiClose);
	    jmFile.add(jmiSave);
	    jmFile.addSeparator();
	    jmFile.add(jmiExit);
	    javaMenuBar.add(jmFile);
	    
	    jmOptions = new JMenu("Options");
	    JMenu a = new JMenu("A");
	    JMenuItem b = new JMenuItem("B");
	    JMenuItem c = new JMenuItem("C");
	    JMenuItem d = new JMenuItem("D");
	    a.add(b);
	    a.add(c);
	    a.add(d);
	    jmOptions.add(a);

	    JMenu e = new JMenu("E");
	    e.add(new JMenuItem("F"));
	    e.add(new JMenuItem("G"));
	    jmOptions.add(e);

	    javaMenuBar.add(jmOptions);

	    JMenu jmHelp = new JMenu("Help");
	    JMenuItem jmiAbout = new JMenuItem("About");
	    jmHelp.add(jmiAbout);
	    javaMenuBar.add(jmHelp);

	    jmiOpen.addActionListener(this);
	    jmiClose.addActionListener(this);
	    jmiSave.addActionListener(this);
	    jmiExit.addActionListener(this);
	    b.addActionListener(this);
	    c.addActionListener(this);
	    d.addActionListener(this);
	    jmiAbout.addActionListener(this);

	    pantalla.setJMenuBar(javaMenuBar);
	    pantalla.setVisible(true);
	  }
	  public void actionPerformed(ActionEvent ae) {
	    String comStr = ae.getActionCommand();
	    
	    if(comStr == "Exit") {
	    	pantalla.dispose();
	    }
	    
	    System.out.println(comStr + " Selected");
	  }
	  public static void main(String args[]) {
	    new Menu();
	  }
}
