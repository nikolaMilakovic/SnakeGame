import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean play = true;
	
	private int lengthofsnake = 3;
	private int moves = 0;
	private int score = 0;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private Timer timer;
	private  int delay = 100;
	
	private int [] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int [] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private ImageIcon snakeImage;
	private ImageIcon titleImage;
	private ImageIcon enemyImage;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g) {
		
		if(moves == 0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		
		//draw title image borders
		g.setColor(Color.white);
		g.fillRect(24, 10, 852, 56);
		
		//draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 852, 577);
		
		//draw background for gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: "+score, 750, 30);
		
		//draw length of snake
			
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));		
		g.drawString("Length of snake: "+lengthofsnake, 750, 50);
		
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a = 0; a < lengthofsnake; a++) {
			if(a == 0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			if(a == 0 && left) {
				leftmouth = new ImageIcon("rightmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			if(a == 0 && down) {
				downmouth = new ImageIcon("rightmouth.png");
				downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			if(a == 0 && up) {
				upmouth = new ImageIcon("rightmouth.png");
				upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}
			if(a != 0) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		
		enemyImage = new ImageIcon("enemy.png");
		
		if(enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]) {
			score++;
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
			
		enemyImage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int i = 1; i < lengthofsnake; i++) {
			if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				
				right = false;
				left = false;
				up = false;
				down = false;
				play = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.PLAIN, 50));
				g.drawString("GAME OVER", 300, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("space to RESTART", 350, 340);
			}
		}
		
		g.dispose();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right && play) 
		{
			for(int i = lengthofsnake-1; i >= 0; i--) {
				snakeylength[i+1] = snakeylength[i];
			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if(i == 0) {
					snakexlength[i] = snakexlength[i] + 25;
				} else {
					snakexlength[i] = snakexlength[i-1];
				}
				if(snakexlength[i] > 850) {
					snakexlength[i] = 25;
				}
			}
			repaint();
		}
		if(left && play) 
		{
			for(int i = lengthofsnake-1; i >= 0; i--) {
				snakeylength[i+1] = snakeylength[i];
			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if(i == 0) {
					snakexlength[i] = snakexlength[i] - 25;
				} else {
					snakexlength[i] = snakexlength[i-1];
				}
				if(snakexlength[i] < 25) {
					snakexlength[i] = 850;
				}
			}
			repaint();
		}
		if(up && play) 
		{
			for(int i = lengthofsnake-1; i >= 0; i--) {
				snakexlength[i+1] = snakexlength[i];
			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if(i == 0) {
					snakeylength[i] = snakeylength[i] - 25;
				} else {
					snakeylength[i] = snakeylength[i-1];
				}
				if(snakeylength[i] < 75) {
					snakeylength[i] = 625;
				}
			}
			repaint();
		}
		if(down && play) 
		{
			for(int i = lengthofsnake-1; i >= 0; i--) {
				snakexlength[i+1] = snakexlength[i];
			}
			for (int i = lengthofsnake; i >= 0; i--) {
				if(i == 0) {
					snakeylength[i] = snakeylength[i] + 25;
				} else {
					snakeylength[i] = snakeylength[i-1];
				}
				if(snakeylength[i] > 625) {
					snakeylength[i] = 75;
				}
			}
			repaint();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			play = true;
			lengthofsnake = 3;
			moves = 0;
			score = 0;
			repaint();
		}
		if( e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				left = true;
				right = false;
			}
			up = false;
			down = false;
		}
		if( e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				right = true;
				left = false;
			}
			up = false;
			down = false;
		}
		if( e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				down = true;
				up = false;
			}
			left = false;
			right = false;
		}
		if( e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				up = true;
				down = false;
			}
			left = false;
			right = false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
