package org.double0negative.life;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

public class LifeGame extends JFrame{

	protected boolean[][] life;
	protected Color[][] lifecolors;
	protected int size;
	protected Random rand;
	protected DrawPanel drawPanel;

	protected int x;
	protected int y;

	public LifeGame(int x, int y, int size, int seed){
		life = new boolean[x][y];
		lifecolors = new Color[x][y];
		this.size = size;
		if(seed == -1){
			rand = new Random();
		} else {
			rand = new Random(seed);
		}
		this.x = x; this.y = y;

		this.setTitle("Game Of Life - Double0negative");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((x * size), y * size + 30);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.black);

		drawPanel = new DrawPanel(this.getWidth(), this.getHeight(), size);
		this.getContentPane().add(drawPanel);


	}

	public void start(){
		this.setVisible(true);
		addAll();
		render();

		animatein();
		setup();
		renderFromLife();

		while(true){
			sleep(100);
			tick();
		}
	}

	protected void animatein() {
		for(int y = 0; y < lifecolors[0].length; y++){
			for(int x = 0; x < lifecolors.length; x++){
				lifecolors[x][y] = Color.black;
				render();
			}
			try{Thread.sleep(10);}catch(Exception e){}
		}

	}

	protected void addAll(){
		for(int x = 0; x < lifecolors.length; x++){
			for(int y = 0; y < lifecolors[x].length; y++){
				lifecolors[x][y] = Color.WHITE;
			}
		}
	}

	protected void renderFromLife(){
		for(int x = 0; x < lifecolors.length; x++){
			for(int y = 0; y < lifecolors[x].length; y++){
				if(life[x][y]){
					lifecolors[x][y] = Color.RED;
				}
				else{
					lifecolors[x][y] = Color.BLACK;
				}
			}
		}
		render();
	}

	protected void render(){
		drawPanel.render(lifecolors);
	}

	public void setup(){
		for(int a = 0; a < rand.nextInt((x * y)); a++){
			int rx = rand.nextInt(life.length);
			int ry = rand.nextInt(life[0].length);
			if(rx > 3 && ry > 3 && rx < life.length-4 && ry < life[0].length-4){
				for(int x1 = rx - 4; x1 < rx+4; x1++){
					for(int y1 = ry - 4; y1 < ry+4; y1++){
						if(rand.nextBoolean()){
							life[x1][y1] = true;
						}
					}
				}
			}
			life[rx][ry] = true;
		}
	}

	public void tick(){
		boolean[][] snap = cloneArray(life);
		for(int x = 0; x < lifecolors.length; x++){
			for(int y = 0; y < lifecolors[x].length; y++){
				int lives = getNearLives(snap, x, y);
				boolean alive = snap[x][y];
				if(alive){
					if(lives < 2){
						life[x][y] = false;
					}
					if(lives > 3){
						life[x][y] = false;
					}
				} else {
					if(lives == 3){
						life[x][y] = true;
					}
				}
			}
			//sleep(100);

		}
		renderFromLife();

	}

	public boolean[][] cloneArray(boolean[][] array){
		boolean narray[][] = new boolean[array.length][array[0].length];
		for(int x = 0; x < array.length; x++){
			for(int y = 0; y <array[0].length; y++){
				narray[x][y] = array[x][y];
			}
		}
		return narray;
	}

	public int getNearLives(boolean[][] snap, int rx, int ry){
		int count = 0;
		if(rx > 0 && ry > 0 && rx < snap.length-1 && ry < snap[0].length-1){
			for(int x1 = rx - 1; x1 <= rx+1; x1++){
				for(int y1 = ry - 1; y1 <= ry+1; y1++){
//					lifecolors[rx][ry] = Color.blue;
//					render();
					if(!(x1 == rx && y1 == ry)){
//						render();
//						lifecolors[x1][y1] = Color.green;
						if(snap[x1][y1]){
							count++;
//							System.out.println(count);
//							sleep(3000);
						}
					}
//					sleep(10);
//					renderFromLife();
				}
			}
		}
		return count;
	}

	protected void sleep(long time){
		try{ Thread.sleep(time); } catch(Exception e){ }
	}
}
