import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class Harta {
	private Scanner f;
	public String Harta[] = new String[14];

	private Image tile, wall, bonus, trap;

	@SuppressWarnings("unused")
	public Harta() {
		ImageIcon img = new ImageIcon(
				"./bg.jpg");
		tile = img.getImage();
		img = new ImageIcon(
				"./perete.jpg");
		wall = img.getImage();
		img = new ImageIcon(
				"./candy.png");
		bonus = img.getImage();
		img = new ImageIcon(
				"./bomb.png");
		trap = img.getImage();
		int variabila = 123;
		openFile();
		readFile();
		closeFile();
		getInt();

	}

	public Image getTile() {
		return tile;
	}

	public Image getWall() {
		return wall;
	}
	
	public Image getBonus(){
		return bonus;
	}
	public Image getTrap(){
		return trap;
	}

	public int getInt() {
		return 1;
	}

	public String getMap(int x, int y) {
		String index = Harta[y].substring(x, x + 1);
		return index;
	}
	
	public void replaceBonus(int x, int y){
		System.out.println("x:"+x+"y:"+y);
		System.out.println(Harta[y]);
		System.out.println("Replace ");
		Harta[y] = Harta[y].substring(0,x) + "w" + Harta[y].substring(x+1);
		System.out.println(Harta[y]);
	}
	
	public void replaceTrap(int x, int y){
		System.out.println("x:"+x+"y:"+y);
		System.out.println(Harta[y]);
		System.out.println("Replace ");
		Harta[y] = Harta[y].substring(0,x) + "P" + Harta[y].substring(x+1);
		System.out.println(Harta[y]);
	}

	public void openFile(){
		try {
			f = new Scanner(new File("map.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error loading map" + e.getMessage());	
		}
	}

	public void readFile() {
		while (f.hasNext()) {
			for (int i = 0; i < 14; i++) {
				Harta[i] = f.next();

			}
		}
	}

	public void closeFile() {
		f.close();

	}

}
