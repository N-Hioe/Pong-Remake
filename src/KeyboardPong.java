/// KeyboardPong Main Program
/// By: Nicholas Hioe
/// ICS4U1
/// Version 1.0
/// 2021-11-19

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class KeyboardPong implements KeyListener{

	// Properties
	JFrame frame = new JFrame("Pong");
	PPanel panel = new PPanel();
	
	// Methods
	public void keyReleased(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			panel.intP1DefY = 0;
		}else if(evt.getKeyChar() == 's'){
			panel.intP1DefY = 0;
		}
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			panel.intP2DefY = 0;
		}else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			panel.intP2DefY = 0;
		}
	}
	public void keyPressed(KeyEvent evt){
		// Restart Game (only accessible if someone wins, and win screen appears
		if(evt.getKeyCode() == KeyEvent.VK_ENTER){
			if(panel.intP1Score == 5 || panel.intP2Score == 5){
				// Reset scores and paddle positions
				panel.intP1Score = 0;
				panel.intP2Score = 0;
				panel.intP1Y = 250;
				panel.intP2Y = 250;
			}
		}
		
		// Serve
		if(evt.getKeyCode() == KeyEvent.VK_SPACE){
			panel.blnStart = true;
		}
		
		// Up for player 1
		if(evt.getKeyChar() == 'w'){
			if(panel.intP1Y > 0){
				panel.intP1DefY = -10;
			}else{
				panel.intP1DefY = 0;
				panel.intP1Y = 0;
			}
		}
		// Down for player 1
		if(evt.getKeyChar() == 's'){
			if(panel.intP1Y < 500){
				panel.intP1DefY = 10;
			}
		}
		// Up for player 2
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			if(panel.intP2Y > 0){
				panel.intP2DefY = -10;
			}
		}
		// Down for player 2
		if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			if(panel.intP2Y < 500){
				panel.intP2DefY = 10;
			}else{
				panel.intP2DefY = 0;
				panel.intP2Y = 500;
			}
		}
	}
	
	public void keyTyped(KeyEvent evt){
	}
	
	// Constructor
	public KeyboardPong(){
		frame.addKeyListener(this);
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	// Main Method
	public static void main(String[] args){
		new KeyboardPong();
		
	}
}
		
		
