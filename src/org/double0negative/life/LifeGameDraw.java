package org.double0negative.life;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class LifeGameDraw extends LifeGame{

	public LifeGameDraw(int x, int y, int size, int seed) {
		super(x, y, size, seed);
		// TODO Auto-generated constructor stub
	}
	
	private boolean wait = true;
	
	public void start(){
		this.setVisible(true);
		addAll();
		render();
		animatein();
		
		this.drawPanel.addMouseListener(new DrawListener());
		this.drawPanel.addKeyListener(new EnterListener());
		this.drawPanel.addMouseMotionListener(new MouseMovmentListener());
		this.drawPanel.requestFocus();
		
		while(wait){
			sleep(10);  //wait here for drawing to be complete. Keep everything off the AWT Event thread
		}
		
		renderFromLife();

		while(true){
			sleep(100);
			super.tick();
		}
	}


	public void setup(){

	}
	
	public class EnterListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode()+" "+KeyEvent.VK_ENTER);
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				wait = false;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	boolean MOUSE_DOWN_1 = false;
	boolean MOUSE_DOWN_2 = false;
	
	public class DrawListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getPoint());
			int x = (e.getX() / size) ;
			int y = (e.getY() / size) ;
			if(e.getButton() == MouseEvent.BUTTON1){
				life[x][y] = true;
			} else {
				life[x][y] = false;
			}
			renderFromLife();
		}

		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				MOUSE_DOWN_1 = true;
			} else if (e.getButton() == MouseEvent.BUTTON3){
				MOUSE_DOWN_2 = true;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				MOUSE_DOWN_1 = false;
			} else if (e.getButton() == MouseEvent.BUTTON3){
				MOUSE_DOWN_2 = false;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	public class MouseMovmentListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			int x = (e.getX() / size) ;
			int y = (e.getY() / size) ;
			if(MOUSE_DOWN_1){
				life[x][y] = true;
			} else if (MOUSE_DOWN_2){
				life[x][y] = false;
			}
			renderFromLife();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	
	}
}
