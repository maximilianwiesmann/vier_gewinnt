import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame{
	
	private int[][] spielFeld;
	
	private final int frameBreite = 900;
	private final int frameHoehe = 600;
	private final int tileBreite = 30;
	private final int tileHoehe = 30;
	
	private final int xVerschiebung = 30;
	private final int yVerschiebung = 30;
	
	public GUI(int[][] spielFeld)
	{
		
		this.spielFeld = spielFeld;
		//tileBreite = frameBreite / spielFeld.length;
		//tileHoehe = frameHoehe / spielFeld[0].length;
		
		this.setSize(frameBreite, frameHoehe);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setResizable(true);
		
		this.add(new PaintHandler());
		this.setVisible(true);
	}
	
	private class PaintHandler extends JLabel
	{
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.green);
			
			for(int y = spielFeld[0].length - 1; y >= 0; y--)
			{
				g.setColor(Color.green);
				g.drawString(y + "", 10, y*tileHoehe + yVerschiebung + 10);
				
				for(int x = 0; x < spielFeld.length; x++)
				{
					g.setColor(Color.green);
					g.drawString(x + "", x*tileBreite + xVerschiebung, 20);
					g.setColor(zuFarbe(spielFeld[x][y]));
					g.fillRect(x * tileBreite + xVerschiebung, y * tileHoehe + yVerschiebung, tileBreite, tileHoehe);
					g.setColor(Color.WHITE);
					g.drawRect(x * tileBreite + xVerschiebung, y * tileHoehe + yVerschiebung, tileBreite, tileHoehe);
				}
			}
		}
		
		private Color zuFarbe(int id)
		{
			Color farbe = Color.gray;
			
			if(id == 1)
				farbe = Color.red;
			else if(id == 2)
				farbe = Color.yellow;
			
			return farbe;
		}
	}
}
