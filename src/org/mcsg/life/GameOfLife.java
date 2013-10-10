package org.mcsg.life;

public class GameOfLife {
	
	public static void main(String args[]){
		//new LifeGame(100,70,100,-1);
		new LifeGamePureRandom(100,70,10,-1).start();  //x,y,size,seed. Increas x/y and decrease size for more density
	}
}
