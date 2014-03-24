package annuaire;

import codebase.AnnuaireInterface;
import codebase.BlagueProviderP2P;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class Annuaire implements AnnuaireInterface
{
    
    //ATTRIBUTS
    private final HashMap<String, BlagueProviderP2P> listeRef;
    
    
    //CONSTRUCTEUR
    public Annuaire()
    {
        this.listeRef = new HashMap<>();
    }
    
    
    //METHODES
    @Override
    public BlagueProviderP2P[] register(BlagueProviderP2P ref) throws RemoteException
    {
        String[] names = ref.getAllName();
        ArrayList<BlagueProviderP2P> res = new ArrayList<>();
        BlagueProviderP2P bp;
        for (String name : names) {
            bp = listeRef.get(name);
            if (bp != null) {
                res.add(bp);
            }
        }
        for (Map.Entry<String, BlagueProviderP2P> entry : listeRef.entrySet()) {
            entry.getValue().notify(ref);
        }
        listeRef.put(ref.getNom(), ref);
        return (BlagueProviderP2P[]) res.toArray();
    }
    
    @Override
    public void disconnect(BlagueProviderP2P ref) throws RemoteException
    {
        for (Map.Entry<String, BlagueProviderP2P> entry : listeRef.entrySet()) {
            entry.getValue().notifyDeconnect(ref);
        }
        listeRef.remove(ref.getNom());
    }
    
    //MAIN
    public static void main(String[] args) {
        
        try {
            Annuaire annuaire = new Annuaire();
            AnnuaireInterface proxy = (AnnuaireInterface) UnicastRemoteObject.exportObject(annuaire,0);
            Registry regAnnuaire = LocateRegistry.getRegistry();
            regAnnuaire.rebind("annuaire", proxy);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }
}
