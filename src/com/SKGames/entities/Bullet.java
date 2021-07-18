package com.SKGames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.SKGames.main.Game;

public class Bullet extends Entity{

	public Bullet(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

	}

	public void tick() {
		y -= speed;
		
		if(y < 0 ) {
			Game.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(76, 255, 0));
		g.fillRect(this.getX() - 1, this.getY() + 8, width, height);
	}
}
