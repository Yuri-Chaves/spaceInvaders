package com.SKGames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.SKGames.main.Game;


public class Enemy extends Entity {
	
	public static BufferedImage[] enemy;
	
	public int r = Entity.rand.nextInt(7);
	
	public int life = Entity.rand.nextInt(7 - 3) + 3;
		
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		
		
		enemy = new BufferedImage[7];
		
		enemy[0] = Game.spritesheet.getSprite(64, 0, 64, 64);
		enemy[1] = Game.spritesheet.getSprite(128, 0, 64, 64);
		enemy[2] = Game.spritesheet.getSprite(192, 0, 64, 64);
		enemy[3] = Game.spritesheet.getSprite(256, 0, 64, 64);
		enemy[4] = Game.spritesheet.getSprite(320, 0, 64, 64);
		enemy[5] = Game.spritesheet.getSprite(384, 0, 64, 64);
		enemy[6] = Game.spritesheet.getSprite(448, 0, 64, 64);
		
		
	}
	public void tick() {
		depth = 2;
		y+= speed;
		if(y >= Game.HEIGHT) {
			Game.entities.remove(this);
			Game.Score -= 10;
			Game.life -= Entity.rand.nextInt(10);
			return;
		}
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Bullet) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(e);
					life --;
					if(life == 0) {
						Game.Score += 3;
						Explosion explosion = new Explosion(x,y,64,64,0,null);
						Game.entities.remove(this);
						Game.entities.add(explosion);
						return;
					}
					break;
				}
			}
			
		}
		
	}
	public void render(Graphics g) {
		g.drawImage(enemy[r], this.getX(), this.getY(), null);
	}

}
