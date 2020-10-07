import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.ImageIcon;

@SuppressWarnings("unused")
public class Player {
	
	private static final String UNUSED = "unused";
	private int x,y, tileX, tileY;
	public Image player;
	ImageIcon img = new ImageIcon("../mario.png");

	
	
	public Player(){
		img = new ImageIcon("./mario.png");
		player = img.getImage();

		x = 32;
		y = 32;
		
		tileX = 1;
		tileY = 1;
		
	}
	
	public Image getPlayer(){
		return player;
	}

	public int getTileX(){
		return tileX;
	}
	
	public int getTileY(){
		return tileY;
	}
	
	public void move(int dx, int dy){
		tileX += dx;
		tileY += dy;
	}
	
	public void setImage(String x){
		img = new ImageIcon(x);
		player = img.getImage();
	}
	
	public void rotateLeft() {
		
	}
	
	public void rotateRight() {
		
	}

}
