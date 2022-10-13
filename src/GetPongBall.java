/// GetPongBall - KeyboardPong
/// By: Nicholas Hioe
/// ICS4U1
/// Version 1.0
/// 2021-11-19

import java.awt.*;

public class GetPongBall{
	// Properties
	int intX = 390;
	int intY = 290;
	int intDefY = 0;
	int intDefX = 25;
	
	// Method
	public void nextLocation(){
		intY = intY + intDefY;
		intX = intX + intDefX;
	}
	
	public void drawBall(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(intX, intY, 15, 15);
		this.nextLocation();
	}
	
	// Constructor
	public GetPongBall(){
	}
	
}
