/// PPanel - KeyboardPong
/// By: Nicholas Hioe
/// ICS4U1
/// Version 1.0
/// 2021-11-19

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PPanel extends JPanel implements ActionListener{
	// Properties
	Timer timer = new Timer(1000/60, this);
	int intP1X = 50;
	int intP2X = 740;
	int intP1Y = 250;
	int intP2Y = 250;
	int intP1DefY;
	int intP2DefY;
	int intP1Score;
	int intP2Score;
	boolean blnStart = false;
	GetPongBall ball = new GetPongBall();
	BufferedImage p1win = null;
	BufferedImage p2win = null;
	BufferedImage p1easter = null;
	BufferedImage p2easter = null;
	Font score = new Font("", Font.BOLD, 100);
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		}
	}
	
	// Override paintComponent
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		g.setFont(score);
		if(intP1Score == 5){
			// Reset ball location to override serving during win screen
			blnStart = false;
			ball.intX = 390;
			ball.intY = 290;
			ball.intDefY = 0;
			// P1 Easter Egg
			if(intP2Score == 0){
				g.drawImage(p1easter, 0, 0, null);
			// P1 Win
			}else{
				g.drawImage(p1win, 0, 0, null);
			}
		}else if(intP2Score == 5){
			// Reset ball location to override serving during win screen
			blnStart = false;
			ball.intX = 390;
			ball.intY = 290;
			ball.intDefY = 0;
			// P2 Easter Egg
			if(intP1Score == 0){
				g.drawImage(p2easter, 0, 0, null);
			// P2 Win	
			}else{
				g.drawImage(p2win, 0, 0, null);
			}
		}else{
			// Score
			g.setColor(new Color(195, 195, 195));
			g.drawString(""+intP1Score, 150, 150);
			g.drawString(""+intP2Score, 630, 150);
			g.setColor(Color.WHITE);
			
			// Paddle 1
			if(intP1Y <= 490 && intP1Y >= 10){		
				g.fillRect(intP1X, intP1Y, 10, 100);
			}else if(intP1Y > 490){
				intP1Y = 490;
				g.fillRect(intP1X, intP1Y, 10, 100);
			}else if(intP1Y < 10){
				intP1Y = 10;
				g.fillRect(intP1X, intP1Y, 10, 100);
			}
			
			// Paddle 2
			if(intP2Y <= 490 && intP2Y >= 10){		
				g.fillRect(intP2X, intP2Y, 10, 100);
			}else if(intP2Y > 490){
				intP2Y = 490;
				g.fillRect(intP2X, intP2Y, 10, 100);
			}else if(intP2Y < 10){
				intP2Y = 10;
				g.fillRect(intP2X, intP2Y, 10, 100);
			}
			
			// Adjust position of paddles
			intP1Y = intP1Y + intP1DefY;
			intP2Y = intP2Y + intP2DefY;
			
			// Side Borders
			g.setColor(new Color(195, 195, 195));
			g.fillRect(0, 0, 800, 10);
			g.fillRect(0, 590, 800, 10);
			
			// Middle Dividing Line
			g.fillRect(395, 0, 5, 40);
			g.fillRect(395, 60, 5, 40);
			g.fillRect(395, 120, 5, 40);
			g.fillRect(395, 180, 5, 40);
			g.fillRect(395, 240, 5, 40);
			g.fillRect(395, 310, 5, 40);
			g.fillRect(395, 370, 5, 40);
			g.fillRect(395, 430, 5, 40);
			g.fillRect(395, 490, 5, 40);
			g.fillRect(395, 550, 5, 40);
			
			// Ball
			g.setColor(Color.WHITE);
			g.fillOval(ball.intX, ball.intY, 15, 15);
			if(blnStart == true){
				ball.drawBall(g);
			}
			
			// Paddle 1 interaction with ball
			if(intP1X + 10 > ball.intX && intP1X < ball.intX + 15){	
				// Hits middle portion of paddle
				if(ball.intDefX < 0){
					if(intP1Y + 60 > ball.intY && intP1Y + 40 < ball.intY + 15){
						ball.intDefY = 0;
						ball.intDefX = ball.intDefX * -1;
					// Hits upper portion of paddle
					}else if(intP1Y + 40 > ball.intY && intP1Y < ball.intY + 15){
						ball.intDefY = -5;
						ball.intDefX = ball.intDefX * -1;	
					// Hits lower portion of paddle
					}else if(intP1Y + 100 > ball.intY && intP1Y + 60 < ball.intY + 15){
						ball.intDefY = 5;
						ball.intDefX = ball.intDefX * -1;
					}
				}
			}
		
			// Paddle 2 interaction with ball
			if(intP2X + 10 > ball.intX && intP2X < ball.intX + 15){	
				if(ball.intDefX > 0){
					// Hits middle portion of paddle
					if(intP2Y + 60 > ball.intY && intP2Y + 40 < ball.intY + 15){
						ball.intDefY = 0;
						ball.intDefX = ball.intDefX * -1;
					// Hits upper portion of paddle
					}else if(intP2Y + 40 > ball.intY && intP2Y < ball.intY + 15){
						ball.intDefY = -5;
						ball.intDefX = ball.intDefX * -1;	
					// Hits lower portion of paddle
					}else if(intP2Y + 100 > ball.intY && intP2Y + 60 < ball.intY + 15){
						ball.intDefY = 5;
						ball.intDefX = ball.intDefX * -1;
					}
				}
			}
			// Ball interaction with borders
			if(ball.intY <= 10 || ball.intY >= 580){
				ball.intDefY = ball.intDefY * -1;
			}	
			// Player 2 Scores
			if(ball.intX < -15){
				intP2Score++;
				ball.intX = 390;
				//ball.intY = 290;
				//ball.intDefY = 0;
				g.setColor(Color.WHITE);
				g.fillOval(ball.intX, ball.intY, 15, 15);
				blnStart = false;	
			// Player 1 Scores
			}else if(ball.intX > 800){
				intP1Score++;
				ball.intX = 390;
				//ball.intY = 290;
				//ball.intDefY = 0;
				g.setColor(Color.WHITE);
				g.fillOval(ball.intX, ball.intY, 15, 15);
				blnStart = false;
			}
		}
	}
	
	// Constructor
	public PPanel(){
		super();
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		timer.start();
		// Load end screens
		try{
			p1win = ImageIO.read(new File("p1win.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
		try{
			p2win = ImageIO.read(new File("p2win.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
		try{
			p1easter = ImageIO.read(new File("p1easter.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
		try{
			p2easter = ImageIO.read(new File("p2easter.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
	}
}
