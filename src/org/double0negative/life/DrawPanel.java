package org.double0negative.life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{

	private Color[][] colors;
	private int size;

	public DrawPanel(int w, int h, int size){
		
		this.setBounds(0,0,w,h);
		this.size = size;
		this.setBackground(Color.black);
		
	}
	
	
	
	
	  public void paintComponent(Graphics g1) {
		    super.paintComponent(g1);
		    Graphics2D g = (Graphics2D)g1; 
		    
			for(int x = 0; x < colors.length; x++){
				for(int y = 0; y < colors[x].length; y++){
					g.setColor(colors[x][y]);
					g.fillRect(x * size, y * size, size, size);
				}
			}
	  }
	
	
	public void render(Color[][] colors){
		this.colors = colors;
		this.repaint();
	}
	
	
	
	
	
	
	
	
}
