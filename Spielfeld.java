import java.lang.ref.WeakReference;
import java.util.Scanner;

public class Spielfeld{
    
    private int[][] spielFeld;
    
    private final int feldHoehe = 6;
    private final int feldBreite = 7;
    
    public int farbe = 0;
    public boolean spiel = false;
    public final int gewinnLaenge = 4;
    
    private GUI view;
    
    private boolean korrekterZug = false;
    
    public Spielfeld()
    {
    }
    
    public void init()
    {
        spielFeld = new int[feldBreite][feldHoehe];
        
        for(int y = 0; y < spielFeld[0].length; y++)
        {
            for(int x = 0; x < spielFeld.length; x++)
            {
                spielFeld[x][y] = 0;
            }
        }
    }
    
    public void initZeichnen()
    {
        view = new GUI(spielFeld);
    }
    
    public boolean einwerfen(int spalte)
    {
        for(int y = 0; y < spielFeld[0].length; y++)
        {
            if(spielFeld[spalte][y]==1||spielFeld[spalte][y]==2)
            {
                if(y == 0)
                {
                    korrekterZug = false;
                    System.out.println("Ungültiger Zug!");
                    break;
                }
                else
                {
                    spielFeld[spalte][y-1] = farbe;
                    korrekterZug = true;
                    break;
                }
            }
            else if(y == spielFeld[0].length-1)
            {   
                spielFeld[spalte][y] = farbe;
                korrekterZug = true;
            }
        }
        return korrekterZug;
    }
    
    public void checkeGewinn()
    {
        for(int y = 0; y < spielFeld[0].length; y++)
        {
            for(int x = 0; x < spielFeld.length; x++)
            {
                if(horizontalGewinn(x,y))
                    spiel = false;
                if(vertikalGewinn(x,y))
                    spiel = false;
                if(diagonalObenLinksNachUntenRechtsGewinn(x, y))
                    spiel = false;
                if(diagonalObenRechtsNachUntenLinksGewinn(x, y))
                    spiel = false;
            }
        }
    }
    
    private boolean horizontalGewinn(int x, int y)
    {       
        if(x < spielFeld.length-gewinnLaenge)
        {
            for(int xIndex = 0; xIndex < gewinnLaenge; xIndex++)
            {
                if(!(spielFeld[xIndex+x][y] == farbe))
                {
                    return false;
                }
            }
            System.out.println("Horizontalgewinn von " + farbe);
            return true;
        }
        
        return false;
    }
    
    private boolean vertikalGewinn(int x, int y)
    {
        if(y < spielFeld[0].length - gewinnLaenge + 1)
        {
            for(int yIndex = 0; yIndex < gewinnLaenge; yIndex++)
            {
                if(!(spielFeld[x][yIndex+y] == farbe))
                {
                    return false;
                }
            }
            
            System.out.println("Vertikalgewinn von " + farbe);
            
            return true;
        }
        return false;
    }
    
    private boolean diagonalObenLinksNachUntenRechtsGewinn(int x, int y)
    {
        if(x <= spielFeld.length - gewinnLaenge && y <= spielFeld[0].length - gewinnLaenge)
        {
            for(int xyIndex = 0; xyIndex < gewinnLaenge; xyIndex++)
            {
                if(!(spielFeld[x+xyIndex][y+xyIndex] == farbe))
                    return false;
            }
            
            System.out.println("Spiel von oben Links nach unten Rechts beendet");
            return true;
        }
        return false;
    }
    
    private boolean diagonalObenRechtsNachUntenLinksGewinn(int x, int y)
    {
        if(x >= gewinnLaenge - 1 && y <= spielFeld[0].length - gewinnLaenge)
        {
            for(int xyIndex = 0; xyIndex < gewinnLaenge; xyIndex++)
            {
                if(!(spielFeld[x - xyIndex][y + xyIndex] == farbe))
                    return false;
            }
            System.out.println("Spiel von Oben Rechts nach unten Links beendet");
            return true;
        }
        return false;
    }
    
    public int[][] getSpielFeld()
    {
        return spielFeld;
    }
    
   public boolean getKorrekterZug()
   {
       return korrekterZug;
   }
   
   public boolean getSpiel()
   {
       return spiel;
    }
    
   public int getFeldBreite()
   {
       return feldBreite;
    }
    
   public int getFeldHoehe()
   {
       return feldHoehe;
    }
    
}