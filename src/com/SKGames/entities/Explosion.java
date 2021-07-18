package com.SKGames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.SKGames.main.Game;

public class Explosion extends Entity{
	
	private int frames = 0, maxframes = 8, index, maxindex = 6;
	
	public BufferedImage[] explosion;

	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		explosion = new BufferedImage[7];
		
		for(int i = 0; i < 7; i++) {
			explosion[i] = Game.spritesheet.getSprite(0 +(i*64), 64, 64, 64);
		}
	}

	public void tick() {
		frames ++;
			if(frames == maxframes) {
				frames = 0;
				index++;
				if(index >= maxindex) {
					Game.entities.remove(this);
					return;
				}
			}
	}
	public void render(Graphics g) {
		g.drawImage(explosion[index], this.getX(), this.getY(),null);
	}
}
