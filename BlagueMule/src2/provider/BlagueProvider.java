package provider;

import blague.Blague;
import codebase.BlagueProviderP2P;
import exceptions.BlagueAbsenteException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import serveur.BlagueProviderInterface;

public class BlagueProvider implements BlagueProviderP2P
{
    private String nom;
    private ArrayList<BlagueProviderP2P> repertoireProxy;
    private HashMap<String,Blague> repertoireBlagues;
    
    public BlagueProvider(String n)
    {
        nom = n;
        repertoireProxy = new ArrayList();
        repertoireBlagues = new HashMap();
    }
    
    public BlagueProvider(String n, HashMap<String,Blague> hm)
    {
        nom = n;
        repertoireProxy = new ArrayList();
        repertoireBlagues = hm;
    }
    
    @Override
    public void addBlague(Blague b)
    {
        repertoireBlagues.put(b.getNom(), b);
    }
    
    @Override
    public void addProxy(BlagueProviderP2P proxy)
    {
        repertoireProxy.add(proxy);
    }
    
    @Override
    public String getProvidersName()
    {
        return nom;
    }
    
    @Override
    public String[] getAllName() throws Exception
    {
        String[] blagues = new String[0];
        ArrayList<String> al_blagues = new ArrayList();
        
        try
        {
            for (Map.Entry<String,Blague> e : repertoireBlagues.entrySet())
            {
                al_blagues.add(e.getKey());
            }
            
            blagues = al_blagues.toArray(blagues);
        }
        catch (Exception e)
        {
            System.out.println("EXCEPTION - GetAllName");
            e.printStackTrace();
        }
        
        if (blagues != null)
            return blagues;
        else
            throw new BlagueAbsenteException("BLAGUE_ABSENTE_EXCEPTION - GetAllName");
    }
    
    @Override
    public Blague getBlague(String n) throws BlagueAbsenteException
    {
        Blague blague = null;
        
        try
        {
            blague = repertoireBlagues.get((String)n);
            System.out.println(blague.getNom()+"\n"+blague.getQuestion()+"\n"+blague.getReponse());
        }
        catch (Exception e)
        {
            System.out.println("EXCEPTION - GetBlague");
            e.printStackTrace();
        }
        
        if (blague != null)
            return blague;
        else
            throw new BlagueAbsenteException("BLAGUE_ABSENTE_EXCEPTION - GetBlague");
    }
    
    // A TESTER !!
    public static void main(String[] args)
    {
        try
        {                        
            // On initialise une liste de blagues par defaut
            
            String titre1 = "MonTitreDeBlague1";
            String titre2 = "MonTitreDeBlague2";
            String titre3 = "MonTitreDeBlague3";
            
            HashMap<String,Blague> listeBlagues = new HashMap();
            listeBlagues.put(titre1, new Blague(titre1,"Qui suis-je ?","Une 1ere blague !"));
            listeBlagues.put(titre2, new Blague(titre2,"Qui suis-je ?","Une 2nde blague !"));
            listeBlagues.put(titre3, new Blague(titre3,"Qui suis-je ?","Une 3eme blague !"));            

            // On recupere le nom de notre provider (args[0])
            
            String nom = args[0];
            ArrayList<String> autresNomsProviders = new ArrayList<>();

            // On recupere le nom de tous les autres providers (args[1] à args[args.length-1])
            
            for (int i = 1 ; i < args.length ; i++)
            {
                autresNomsProviders.add(args[i]);
            }

            // On cree un objet de type BlagueProvider qui servira de client et de serveur auquel on passe la liste par defaut
            
            BlagueProvider provider = new BlagueProvider(nom, listeBlagues);
            
            // On exporte une reference distante de type BlagueProviderP2P
            
            BlagueProviderP2P proxy = (BlagueProviderP2P) UnicastRemoteObject.exportObject(provider,0);

            // -------------------
            // ----- SERVEUR -----
            // -------------------
            
            // On recupere une reference sur la RMI Registry
            
            Registry reg = LocateRegistry.getRegistry();
            
            // On enregistre cette référence distante dans la RMI Registry sous le nom passe en parametre
            
            reg.rebind(provider.getProvidersName(),proxy);
            
            // ------------------
            // ----- CLIENT -----
            // ------------------
            
            // Pour chacun des autres noms passes en parametres, on cree un proxy

            Iterator it = autresNomsProviders.iterator();
            
            while (it.hasNext())
            {
                String nomAutreProvider = (String) it.next();
                BlagueProviderP2P autreProxy = (BlagueProviderP2P) reg.lookup(nomAutreProvider);
                provider.addProxy(autreProxy);
            }
            
            // --------------------------------
            // ----- ACTIONS DIVERSES P2P -----
            // --------------------------------
            
            // On demande au proxy de nous fournir les blagues de notre client P2P
            
            System.out.println("Mon repertoire de blagues (interne)");
            String[] repertoireInterne = provider.getAllName();
            
            // On affiche nos blagues internes
            
            for (int i = 0 ; i < repertoireInterne.length ; i++)
            {
                System.out.println(repertoireInterne[i]);
            }
            
            // On demande au proxy etranger de nous fournir les blagues d'un autre client P2P
            
            System.out.println("Un autre repertoire de blagues (externe)");
            String[] repertoireExterne = provider.repertoireProxy.get(1).getAllName();
            
            // On affiche les blagues externes
            
            for (int i = 0 ; i < repertoireExterne.length ; i++)
            {
                System.out.println(repertoireExterne[i]);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
