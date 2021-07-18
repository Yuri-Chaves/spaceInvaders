package com.SKGames.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.SKGames.main.Game;
import com.SKGames.world.World;

public class UI {
	public static int seconds = 0;
	public static int minutes = 0;
	public static int hours = 0;
	public static int frames = 0;
	
	public void tick() {
		frames ++;
		if(frames == 60) {
			frames =0;
			seconds ++;
			if(seconds == 60) {
				seconds = 0;
				minutes ++;
				if(minutes %2 ==0 ) {
					World.cicle ++;
					if(World.cicle > World.night) {
						World.cicle = 0;
					}
				}
				if(minutes == 60) {
					minutes = 0;
					hours ++;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		String formatTime = "";
		if (hours < 10) {
			formatTime += "0"+ hours +":";
		}else {
			formatTime += hours+":";
		}
		if (minutes < 10) {
			formatTime += "0"+ minutes +":";
		}else {
			formatTime += minutes+":";
		}
		if (seconds < 10) {
			formatTime += "0"+ seconds;
		}else {
			formatTime += seconds;
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString(formatTime, 20 , 20);
		g.drawString("Score : "+Game.Score, 300, 20);
		
		g.setColor(Color.red);
		g.fillRect(Game.WIDTH*Game.SCALE - 220, 3, 100*2, 15);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH*Game.SCALE - 220, 3,(int)((Game.life/100)*100*2), 15);
		
	}
	
}
