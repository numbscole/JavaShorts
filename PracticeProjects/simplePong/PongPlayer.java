package simplePong;

import javafx.scene.shape.Rectangle;

public class PongPlayer {
	
	private Integer points = 0;
	private Double speed, maxHeight, collisionBuffer = 2.0, startX, startY;
	private Rectangle rec;
	private Integer width = 8, height = 60;
	
	public PongPlayer(Double BoardHeight, Double BoardWidth, Double movementSpeed, boolean leftStart) {

		speed = movementSpeed;
		maxHeight = BoardHeight;
		startY = BoardHeight/2.0;
		if(leftStart)
			startX = 0.0;
		else
			startX = BoardWidth - width;
		
		rec = new Rectangle();
		rec.setWidth(width);
		rec.setHeight(height);
		
		rec.setY(startY);
		rec.setX(startX);
	}
	
	public void resetPosition() {
		rec.setY(startY);
		rec.setX(startX);
	}
	
	public Rectangle getRec() {
		return rec;
	}
	
	public void addPoint(){
		points += 1;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public void moveUp() {
		if( !(rec.getY() <= 0.0) )
			rec.setY(rec.getY() - speed);
	}
	
	public void moveDown() {
		if( !(rec.getY()+height >= maxHeight) )
			rec.setY(rec.getY() + speed);
	}
	
	public boolean checkCollision(Double ballX, Double ballY) {
		boolean intersectY = false, intersectX = false, collision = false;
		
		Double y = rec.getY();
		// center point of the bar
		Double x = rec.getX() + width/2.0;
		
		if (y + height + collisionBuffer >= ballY && y - collisionBuffer <= ballY)
			intersectY = true;
		if (x + collisionBuffer >= ballX && x - collisionBuffer <= ballX)
			intersectX = true;
		if(intersectY && intersectX)
			collision = true;
		
		return collision;
	}

}
