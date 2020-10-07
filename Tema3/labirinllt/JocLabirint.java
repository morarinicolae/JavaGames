import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


class Pozitie{
	public int i0,j0;
	public Pozitie(int i, int j){this.i0=i; this.j0=j;}
	}

class ZiduriLabirint{
public ArrayList ziduri;
public ZiduriLabirint(){ziduri=new ArrayList();}

public boolean contine (int i,int j)
{boolean gasit=false;
	for(int k=0;k<ziduri.size();k++)
		if(i==((Pozitie)ziduri.get(k)).i0&&(j==((Pozitie)ziduri.get(k)).j0)) gasit=true;
		
		return gasit;}

}


public class JocLabirint {
   JFrame f; 
   	Container cp;
   labirint bg;//background
   personaj om;
   char[][] schema_labirint=new char[17][17];    
   JLayeredPane lp; 
    
    //LISTA POZITIILOR DE ZID
    ZiduriLabirint zl=new ZiduriLabirint();
    
    public JocLabirint() {
    	
    	int i=-1,j=-1;
    	JFrame f=new JFrame("LEDIX");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cp=f.getContentPane();
        lp=new JLayeredPane();     
    	
    	
        lp.setPreferredSize(new Dimension(350, 350));
                    
        cp.add(lp, BorderLayout.CENTER);
    	
    	//SCHEMA LABIRINT CITIRE
    	String numefis="schema.txt";  
        
     
    try{
     BufferedReader in=new BufferedReader(new FileReader(numefis)) ;
         String s=new String(); 
         StringTokenizer st=new StringTokenizer(s," ");
          while((s=in.readLine())!=null)
            {i++;
             st=new StringTokenizer(s," ");j=-1;
             while (st.hasMoreTokens())
             {j++;
             schema_labirint[i][j]=st.nextToken().charAt(0);
             if(schema_labirint[i][j]=='0')zl.ziduri.add(new Pozitie(i,j)); }}
       }
    
    catch(IOException e) {System.out.println(e);}
    	
    	personaj om=new personaj(2,3, zl);
    	bg=new labirint(schema_labirint); 
    	
    	cp.setLayout(new BorderLayout());
    	cp.add(lp, BorderLayout.CENTER);
    	
    		
    	lp.add(bg, new Integer(0));
        bg.setBounds(0,0,350,350);bg.setOpaque(true);
        
            lp.add(om,new Integer(1));
            om.setBounds(om.c*50,om.l*50,50,50);om.setOpaque(true);   
       
        f.setSize(350,350);f.setLocationRelativeTo(null);f.show();
        
    }
     
         public static void main(String[] args) {
    	JocLabirint controler=new JocLabirint();
        }
    
}
    
    
 //CLASA LABIRINT   
    class labirint extends JLabel {
    int n=7;//nr de linii matrice
    int i=-1,j=-1;
    char[][] schema; personaj om;
    
    public labirint (char[][] schema)
    {//citeste schema labirint din fisier schema.txt din directorul (package) joc
     this.schema=schema;}
  
    
    public void paintComponent(Graphics g)
    {super.paintComponent(g);
     for(int i=0;i<7;i++) //linie
      for(int j=0;j<7;j++)  //coloana
         {g.drawRect(j*50,i*50,50,50);
          
          if(schema[i][j]=='1')g.setColor(Color.blue);
          if(schema[i][j]=='0')g.setColor(Color.gray);
          g.fillRect(j*50,i*50,50,50);
         }}
}
    


//CLASA ACTION
class MyAction extends AbstractAction{
    personaj om; int l,c;
    ZiduriLabirint pozitii_blocate;
    
public MyAction(personaj om,int c,int l, ZiduriLabirint pozitii_blocate)
   {this.om=om;this.l+=l; this.c+=c;this.pozitii_blocate=pozitii_blocate;}

public void actionPerformed (ActionEvent e)
{	if(!pozitii_blocate.contine(om.l+l,om.c+c))
		{om.l+=l; om.c+=c;
         om.setLocation(50*om.c, 50*om.l);}
    }
 
 }
    


    
//CLASA PERSONAJ    
    class personaj extends JLabel implements MouseMotionListener{
    Action actiune_stanga,actiune_dreapta,actiune_sus,actiune_jos;
    public int l, c;
    
    BufferedImage img = null;
    ZiduriLabirint ziduri;

    public personaj (int l0, int c0, ZiduriLabirint ziduri)
    {  
     l=l0;c=c0; this.ziduri=ziduri;
    
     actiune_stanga=new MyAction(this,-1,0,ziduri);
     actiune_dreapta=new MyAction(this,1,0,ziduri);
     actiune_sus=new MyAction(this,0,-1,ziduri);
     actiune_jos=new MyAction(this,0,1,ziduri);
     
     getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"mergi_stanga");
     getActionMap().put("mergi_stanga", actiune_stanga);
     
     getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"mergi_dreapta");
     getActionMap().put("mergi_dreapta", actiune_dreapta);
     
     getInputMap().put(KeyStroke.getKeyStroke("UP"),"mergi_sus");
     getActionMap().put("mergi_sus", actiune_sus);
     
     getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"mergi_jos");
     getActionMap().put("mergi_jos", actiune_jos);  
     	
     	//ASCULTATOR DE MISCARE DE MOUSE
           addMouseMotionListener(this);
     	
     }
 
 public void mouseDragged(MouseEvent e)
 { if(!ziduri.contine(l+e.getY()/50,c+e.getX()/50))
 {l+=e.getY()/50; c+=e.getX()/50;
   setLocation(c*50,l*50);}}
   
         
 public void mouseMoved(MouseEvent e){	if(!ziduri.contine(l+e.getY()/50,c+e.getX()/50))
 {l+=e.getY()/50; c+=e.getX()/50;
   setLocation(c*50,l*50);}}
 
 	
 
    public void paintComponent(Graphics g)
    {super.paintComponent(g);
	  
     BufferedImage im = null;
     try {im = ImageIO.read(new File("poza.gif"));} 
     catch (IOException e) {}
     
    g.drawImage(im, 0, 0, 50,50, null); 
    }


}



   
      

    
    
   