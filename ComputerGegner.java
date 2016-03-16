import java.lang.ref.WeakReference;
import java.util.*;

public class ComputerGegner
{
    private int moveSpalte;
    Spielfeld spielFeld;
    private boolean zug;
    private int[][] tempFeld;
    private int tempMove;
    private int tempMoveSpalte;
    private int[][] weightedFeld;

    public ComputerGegner()
    {
        spielFeld = new Spielfeld();
        moveSpalte = 0;
        tempMove = 0;
        tempMoveSpalte = 0;
        zug = false;
        tempFeld = new int[spielFeld.getFeldBreite()][spielFeld.getFeldHoehe()];
        weightedFeld = new int[spielFeld.getFeldBreite()][spielFeld.getFeldHoehe()];
    }

    public int computerRandomMove()
    {
        Random rand = new Random();
        moveSpalte = rand.nextInt(spielFeld.getFeldHoehe());
        System.out.println(moveSpalte);
        return moveSpalte;
    }
    
    public int computerTacticMove()
    {
        for(tempMove = 0; tempMove < spielFeld.getFeldBreite(); tempMove++)
        {
            tempFeld = spielFeld.getSpielFeld();
            simEinwerfen(tempMove);
            checkeGewinn();
            if(zug == true)
            {
                System.out.println(moveSpalte);
                return moveSpalte;
            }
        }
        
        Random rand = new Random();
        moveSpalte = rand.nextInt(spielFeld.getFeldHoehe());
        System.out.println(moveSpalte);
        return moveSpalte;
    }
    
    private void simEinwerfen(int tempMove)
    {
        for(int y = 0; y < spielFeld.getFeldHoehe(); y++)
        {
            if(tempFeld[tempMove][y]==1||tempFeld[tempMove][y]==2)
            {
                if(y == 0)
                {
                    break;
                }
                else
                {
                    tempFeld[tempMove][y-1] = spielFeld.farbe;
                    break;
                }
            }
            else if(y == tempFeld[0].length-1)
            {   
                tempFeld[tempMove][y] = spielFeld.farbe;
            }
        }
    }
    
    private void checkeGewinn()
    {
        for(int y = 0; y < tempFeld[0].length; y++)
        {
            for(int x = 0; x < tempFeld.length; x++)
            {
                if(horizontalGewinn1(x,y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(vertikalGewinn1(x,y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(diagonalObenLinksNachUntenRechtsGewinn1(x, y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(diagonalObenRechtsNachUntenLinksGewinn1(x, y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(horizontalGewinn2(x,y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(vertikalGewinn2(x,y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(diagonalObenLinksNachUntenRechtsGewinn2(x, y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
                if(diagonalObenRechtsNachUntenLinksGewinn2(x, y))
                    {
                        zug = true;
                        moveSpalte = tempMoveSpalte;
                        break;
                    }
            }
        }
    }
    
    private boolean horizontalGewinn1(int x, int y)
    {       
        if(x < tempFeld.length-spielFeld.gewinnLaenge)
        {
            for(int xIndex = 0; xIndex < spielFeld.gewinnLaenge; xIndex++)
            {
                if(!(tempFeld[xIndex+x][y] == 1))
                {
                    return false;
                }
                tempMoveSpalte = xIndex + x;
            }
            return true;
        }
        
        return false;
    }
    
    private boolean vertikalGewinn1(int x, int y)
    {
        if(y < tempFeld[0].length - spielFeld.gewinnLaenge)
        {
            for(int yIndex = 0; yIndex < spielFeld.gewinnLaenge; yIndex++)
            {
                if(!(tempFeld[x][yIndex+y] == 1))
                {
                    return false;
                }
                tempMoveSpalte = x;
            }
            
            return true;
        }
        return false;
    }
    
    private boolean diagonalObenLinksNachUntenRechtsGewinn1(int x, int y)
    {
        if(x <= tempFeld.length - spielFeld.gewinnLaenge && y <= tempFeld[0].length - spielFeld.gewinnLaenge)
        {
            for(int xyIndex = 0; xyIndex < spielFeld.gewinnLaenge; xyIndex++)
            {
                if(!(tempFeld[x+xyIndex][y+xyIndex] == 1))
                    return false;
                tempMoveSpalte = x + xyIndex;   
            }
            return true;
        }
        return false;
    }
    
    private boolean diagonalObenRechtsNachUntenLinksGewinn1(int x, int y)
    {
        if(x >= spielFeld.gewinnLaenge - 1 && y <= tempFeld[0].length - spielFeld.gewinnLaenge)
        {
            for(int xyIndex = 0; xyIndex < spielFeld.gewinnLaenge; xyIndex++)
            {
                if(!(tempFeld[x - xyIndex][y + xyIndex] == 1))
                    return false;
                tempMoveSpalte = x - xyIndex;
            }
            return true;
        }
        return false;
    }
    
    private boolean horizontalGewinn2(int x, int y)
    {       
        if(x < tempFeld.length-spielFeld.gewinnLaenge)
        {
            for(int xIndex = 0; xIndex < spielFeld.gewinnLaenge; xIndex++)
            {
                if(!(tempFeld[xIndex+x][y] == 2))
                {
                    return false;
                }
                tempMoveSpalte = xIndex + x;
            }
            return true;
        }
        
        return false;
    }
    
    private boolean vertikalGewinn2(int x, int y)
    {
        if(y < tempFeld[0].length - spielFeld.gewinnLaenge)
        {
            for(int yIndex = 0; yIndex < spielFeld.gewinnLaenge; yIndex++)
            {
                if(!(tempFeld[x][yIndex+y] == 2))
                {
                    return false;
                }
                tempMoveSpalte = x;
            }
            
            return true;
        }
        return false;
    }
    
    private boolean diagonalObenLinksNachUntenRechtsGewinn2(int x, int y)
    {
        if(x <= tempFeld.length - spielFeld.gewinnLaenge && y <= tempFeld[0].length - spielFeld.gewinnLaenge)
        {
            for(int xyIndex = 0; xyIndex < spielFeld.gewinnLaenge; xyIndex++)
            {
                if(!(tempFeld[x+xyIndex][y+xyIndex] == 2))
                    return false;
                tempMoveSpalte = x + xyIndex;   
            }
            return true;
        }
        return false;
    }
    
    private boolean diagonalObenRechtsNachUntenLinksGewinn2(int x, int y)
    {
        if(x >= spielFeld.gewinnLaenge - 1 && y <= tempFeld[0].length - spielFeld.gewinnLaenge)
        {
            for(int xyIndex = 0; xyIndex < spielFeld.gewinnLaenge; xyIndex++)
            {
                if(!(tempFeld[x - xyIndex][y + xyIndex] == 2))
                    return false;
                tempMoveSpalte = x - xyIndex;
            }
            return true;
        }
        return false;
    }
    
    public int getMoveSpalte()
    {
        return moveSpalte;
    }
    
}
