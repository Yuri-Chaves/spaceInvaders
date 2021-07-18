package com.SKGames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.SKGames.entities.Entity;
import com.SKGames.entities.Player;
import com.SKGames.graphics.Spritesheet;
import com.SKGames.graphics.UI;
import com.SKGames.world.World;


public class Game extends Canvas implements Runnable,KeyListener,MouseListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 720;
	public static final int HEIGHT = 690;
	public static final int SCALE = 1;
	
	private BufferedImage image;
	
	public BufferedImage BackGround;
	public BufferedImage BackGround2;
	public int BackY = 0;
	public int BackY2 = -690;
	public int BackSpeed = 2;
	
	public static int Score = 0;
	public static double life=100;
	
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	public static Player player;
	public UI ui;
	public static World world;
	public static EnemySpawn enemyspawn;
	
	public Game(){
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos.
		spritesheet = new Spritesheet("/SpriteSheet.png");
		entities = new ArrayList<Entity>();
		player = new Player(WIDTH /2,HEIGHT - 64,64,64,3,spritesheet.getSprite(0, 0, 64, 64));
		//world = new World();
		ui = new UI();
		entities.add(player);
		enemyspawn = new EnemySpawn();
		try {
			BackGround = ImageIO.read(getClass().getResource("/BackGround.png"));
			BackGround2 = ImageIO.read(getClass().getResource("/BackGround2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initFrame(){
		frame = new JFrame("Space Invaders");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		
		//Player.life -= Entity.rand.nextInt(2);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		ui.tick();	
		enemyspawn.tick();
		
		BackY += BackSpeed;
		if(BackY - 690 >= 0) {
			BackY = -690;
		}
		BackY2 += BackSpeed;
		if(BackY2 - 690 >= 0) {
			BackY2 = -690;
		}
	}
	


	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(25,50,75));
		g.fillRect(0, 0,WIDTH*SCALE,HEIGHT*SCALE);
		g.drawImage(BackGround, 0, BackY, null);
		g.drawImage(BackGround2, 0, BackY2, null);
		/***/
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		//world.render(g);
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		player.render(g);
		ui.render(g);
		bs.show();
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
		
			player.right = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
		
			player.left = true;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.isShooting = true;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			player.right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			player.left = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
			player.isShooting = false;
		}
	}

	
	public void keyTyped(KeyEvent e) {
	
		
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			
		}
		
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

	
}
