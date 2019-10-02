package juego.menu.help;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Help implements ActionListener{

	private static final Border Border = null;
	JFrame pantalla = null;
	JMenuBar javaMenuBar = null;
	JMenu jmFile = null;
	JMenu jmOptions = null;
	Icon upIcon = new ImageIcon("31838.png");
	
	Help() {
		
		//Se crea la pantalla con su tamaño
	    pantalla = new JFrame("Help");
	    pantalla.setSize(640,480);
	    
	    Container content = pantalla.getContentPane();
	    content.setLayout(new GridLayout(2, 2));
	    Border border = LineBorder.createGrayLineBorder();
	    
	    JLabel label1 = new JLabel(upIcon);
	    label1.setHorizontalTextPosition(JLabel.LEFT);
	    label1.setVerticalTextPosition(JLabel.BOTTOM);
	    label1.setBorder(border);
	    content.add(label1);
	    
	    label1.setText("The buttons for the game are: ");
	    //pantalla.add(label1);
	    label1.setVisible(true);
	    
	    //JLabel label2 = new JLabel("UP\n Down\n Right\n Left\n Space\n Enter\n Escape\n");
	    //pantalla.add(label2);
	    //label2.setVisible(true);
	    
	    //Se definen las opciones por default
	    pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    javaMenuBar = new JMenuBar();

	    jmFile = new JMenu("File");
	    
	    //Se crean los items de la pantalla
	    JMenuItem jmiExit = new JMenuItem("Exit");
	    
	    //Se agregan los items al archivo
	    jmFile.add(jmiExit);
	    javaMenuBar.add(jmFile);
	    
	    jmiExit.addActionListener(this);
	    
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
		  new Help();
	  }
}
