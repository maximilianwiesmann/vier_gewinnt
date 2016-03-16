import java.lang.ref.WeakReference;
import java.util.Scanner;

public class Main {
    
    public final int gewinnLaenge = 4;
    
    private Scanner scanner;
    private Scanner scanner2;
    
    private GUI view;
    private Spielfeld spielFeld;
    private Spielfeld korrekterZug;
    private ComputerGegner com;
    
    public static void main(String[] args)
    {
        new Main();
    }
    
    public Main()
    {
        spielFeld = new Spielfeld();
        spielFeld.init();
        chooseGameMode();
        spielFeld.initZeichnen();
        com = new ComputerGegner();
        System.out.println("Initialisieren beendet!");
        
    }
    
    private void chooseGameMode()
    {
        System.out.println("1 für Spieler vs. Spieler, 2 für Spieler vs. Computer");
        scanner2 = new Scanner(System.in);
        
            if(scanner2.nextInt() == 1)
            {
                startGameSpielerVsSpieler();
            }
            else if(scanner2.nextInt() == 2)
            {
                startGameSpielerVsComputer();
            }
            else
            {
                scanner2.close();
                this.chooseGameMode();
            }
            
        scanner2.close();
    }
    
    private void startGameSpielerVsComputer()
    {
        spielFeld.spiel = true;
        scanner = new Scanner(System.in);
        view = new GUI(spielFeld.getSpielFeld());
        
        while(spielFeld.spiel)
        {
            naechsterSpielerVsComputer();
            view.repaint();
        }
    }
    
    private void naechsterSpielerVsComputer()
    {
        if(spielFeld.farbe == 1)
            spielFeld.farbe = 2;
        else if(spielFeld.farbe == 2)
            spielFeld.farbe = 1;
        else if(spielFeld.farbe == 0)
            spielFeld.farbe = 1;
                
            if(spielFeld.farbe == 1)
            {
                spielFeld.einwerfen(scanner.nextInt());
                System.out.println("Eingeworfen");
            }
            else if(spielFeld.farbe == 2)
            {
                com = new ComputerGegner();
                com.computerTacticMove();
                spielFeld.einwerfen(com.getMoveSpalte());
                System.out.println("Computer hat gespielt");
            }
            else
                System.out.println("Eingabe keine Zahl");

        spielFeld.checkeGewinn();
    }
    
    private void startGameSpielerVsSpieler()
    {
        spielFeld.spiel = true;
        scanner = new Scanner(System.in);
        view = new GUI(spielFeld.getSpielFeld());
        
        while(spielFeld.spiel)
        {
            naechsterSpielerVsSpieler();
            view.repaint();
            newGameSpielerVsSpieler();
        }
    }
    
    private void newGameSpielerVsSpieler()
    {
        if(spielFeld.spiel == false && scanner.hasNextInt())
        {   
            spielFeld.spiel = true;
            spielFeld.init();
            spielFeld.initZeichnen();
            System.out.println("Neues Spiel - Initialisieren beendet!");
            startGameSpielerVsSpieler();
        }
    }
    
    private void naechsterSpielerVsSpieler()
    {
        if(spielFeld.farbe == 1)
            spielFeld.farbe = 2;
        else if(spielFeld.farbe == 2)
            spielFeld.farbe = 1;
        else if(spielFeld.farbe == 0)
            spielFeld.farbe = 1;
                
        if(scanner.hasNextInt())
        {
            spielFeld.einwerfen(scanner.nextInt());
            System.out.println("Eingeworfen");
        }
        else
            System.out.println("Eingabe keine Zahl");

        spielFeld.checkeGewinn();
    }
    

}
