package org.mcsg.life;

public class LifeGamePureRandom extends LifeGame{


	public LifeGamePureRandom(int x, int y, int size, int seed) {
		super(x, y, size, seed);
		// TODO Auto-generated constructor stub
	}

	public void setup(){
		for(int x = 0; x < lifecolors.length; x++){
			for(int y = 0; y < lifecolors[x].length; y++){
				life[x][y] = rand.nextBoolean();
			}
		}
	}
}
