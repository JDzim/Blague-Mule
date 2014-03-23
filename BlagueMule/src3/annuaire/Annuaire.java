package annuaire;

import codebase.AnnuaireInterface;
import codebase.BlagueProviderP2P;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class Annuaire implements AnnuaireInterface
{
    
    //ATTRIBUTS
    private ArrayList<BlagueProviderP2P> listeRef;
    
    
    //CONSTRUCTEUR
    public Annuaire()
    {
        this.listeRef = new ArrayList<>();
    }
    
    
    //METHODES
    @Override
    public BlagueProviderP2P[] register(BlagueProviderP2P ref) throws RemoteException
    {
        return null;
    }
    
    @Override
    public void disconnect(BlagueProviderP2P ref) throws RemoteException
    {
        
    }
}
