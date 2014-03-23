package server;

import codebase.BlagueProviderP2P;
import java.util.ArrayList;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class BlagueServer {
    
    //ATTRIBUTS
    private ArrayList<BlagueProviderP2P> annuaire;
    
    
    //CONSTRUCTEUR
    public BlagueServer() {
        annuaire = new ArrayList<>();
    }
}
