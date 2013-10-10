package org.double0negative.life;

public class LifeGameTest extends LifeGame{

	public LifeGameTest(int x, int y, int size, int seed) {
		super(x, y, size, seed);
	}

	
	public void setup(){
		life[3][3] = true;
		life[4][3] = true;
		life[5][3] = true;
	}
}
