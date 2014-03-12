package serveur;

import blague.Blague;
import client.BlagueAbsenteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlagueProviderServeur implements BlagueProviderInterface
{
    private HashMap<String,Blague> annuaire;
    
    public BlagueProviderServeur()
    {
        annuaire = new HashMap();
    }
    
    public BlagueProviderServeur(HashMap<String,Blague> hm)
    {
        annuaire = hm;
    }
    
    @Override
    public String[] getAllName() throws Exception
    {
        String[] blagues = new String[0];
        ArrayList<String> al_blagues = new ArrayList();
        
        try
        {
            for (Map.Entry<String,Blague> e : annuaire.entrySet())
            {
                al_blagues.add(e.getKey());
            }
            
            blagues = al_blagues.toArray(blagues);
        }
        catch (Exception e)
        {
            System.out.println("ERREUR SERVEUR - GetAllName");
            e.printStackTrace();
        }
        
        if (blagues != null)
            return blagues;
        else
            throw new Exception("ERREUR SERVEUR - GetAllName");
    }
    
    @Override
    public Blague getBlague(String n) throws BlagueAbsenteException
    {
        Blague blague = null;
        
        try
        {
            blague = annuaire.get((String)n);
            System.out.println(blague.getNom()+"\n"+blague.getQuestion()+"\n"+blague.getReponse());
        }
        catch (Exception e)
        {
//            System.out.println("ERREUR SERVEUR - GetBlague");
//            e.printStackTrace();
            throw e;
        }
        
        if (blague != null)
            return blague;
        else
            throw new BlagueAbsenteException("ERREUR SERVEUR - GetBlague");
    }
    
    public static void main(String[] args)
    {
        try
        {
            String host = (args.length < 1) ? "localhost" : args[0];
            
            Registry r = LocateRegistry.getRegistry(host);
            
            String nom1 = "MonTitreDeBlague1";
            String nom2 = "MonTitreDeBlague2";
            String nom3 = "MonTitreDeBlague3";
            
            HashMap<String,Blague> hm = new HashMap();
            hm.put(nom1, new Blague(nom1,"Qui suis-je ?","Une 1ere blague !"));
            hm.put(nom2, new Blague(nom2,"Qui suis-je ?","Une 2nde blague !"));
            hm.put(nom3, new Blague(nom3,"Qui suis-je ?","Une 3eme blague !"));
            
            // 1 - Le serveur crée un objet de type BlagueProviderServeur
            BlagueProviderServeur serveur = new BlagueProviderServeur(hm);
            
            // 2 - Il en exporte une référence distante de type BlagueProviderInterface
            BlagueProviderInterface proxy = (BlagueProviderInterface) UnicastRemoteObject.exportObject(serveur,0);
            
            // 3 - On recupere une reference sur la RMI Registry
            Registry reg = LocateRegistry.getRegistry();
            
            // 4 - On enregistre cette référence distante dans la RMI Registry
            reg.rebind("BlagueProvider",proxy);
        }
        catch (Exception e)
        {
            System.out.println("ERREUR SERVEUR - Main");
            e.printStackTrace();
        }
    }
}
