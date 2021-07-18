package com.SKGames.main;

import com.SKGames.entities.Enemy;
import com.SKGames.entities.Entity;

public class EnemySpawn {

	public int targetTime = 120;
	public int curTime = 0;
	
	public void tick() {
		curTime ++;
		if(curTime == targetTime) {
			curTime = 0;
			int yy = 20;
			int xx = Entity.rand.nextInt(Game.WIDTH - 64);
			
			Enemy enemy = new Enemy(xx,yy,64,64,4,null);
			Game.entities.add(enemy);
		}
	}
}
