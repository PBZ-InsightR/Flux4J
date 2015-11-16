
/**
 * Here is the code for View. 
 * Just simple view as our professor said in the class.
 * 
 * we should change code in method "actionPerformed"
 * to make sure that I is changed by "store".
 *
 * First version on 2015/11/13
 */

package com.flux4j;


import java.awt.event.*;

import javax.swing.*;

import java.io.*;



public class View extends JFrame implements ActionListener {
    private  int i=0;

   
    private JTextArea text_AReceive;

    private JButton button_dec;

    private JButton button_inc;

 

 

    public View()   {
    	
    	super("Flux4J ISN4");
      
        this.setBounds(20, 20, 300, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        this.text_AReceive = new JTextArea();
        this.text_AReceive.setEditable(false);

        this.getContentPane().add(new JScrollPane(this.text_AReceive));

        JPanel panel = new JPanel();
        this.getContentPane().add(panel, "South");
        this.button_dec = new JButton("dec");
        panel.add(this.button_dec);
        button_dec.addActionListener(this);
        button_inc = new JButton("inc");
        panel.add(button_inc);
        button_inc.addActionListener(this);

        this.setVisible(true);

       
        text_AReceive.append("value is "  + i + "\r\n");
        
       

    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_inc) {
        	
        	i++;    // we should change code here to make sure that I is changed by "store".        	
            
        	text_AReceive.append("value is "  + i + "\r\n");

        }
        if (e.getSource() == button_dec) {
        	
        	i--;    // we should change code here to make sure that I is changed by "store".        	
            
        	text_AReceive.append("value is "  + i + "\r\n");

        }

    }

}
