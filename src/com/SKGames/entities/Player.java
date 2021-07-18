package com.SKGames.entities;

import java.awt.image.BufferedImage;

import com.SKGames.main.Game;


public class Player extends Entity{	
	
	public boolean right, left;
	
	public boolean isShooting = false;
	
	public double speed = 10;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	
		
	}
	
	public void tick(){
		depth = 1;
		if(right) {
			x+= speed;
		}else if(left) {
			x-= speed;
		}
		
		if(x >= Game.WIDTH) {
			x = -64;
		}else if(x + 64 < 0) {
			x = Game.WIDTH;
		}
		
		if(isShooting) {
			isShooting = false;
			int xx = this.getX() + 32;
			int yy = this.getY();
			Bullet bullet = new Bullet(xx,yy,3,10,20,null);
			Game.entities.add(bullet);
		}
	}
	
	

}
