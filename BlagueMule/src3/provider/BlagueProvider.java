package provider;

import blague.Blague;
import codebase.BlagueProviderP2P;
import codebase.BlagueAbsenteException;
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
public class BlagueProvider implements BlagueProviderP2P
{
    
    //ATTRIBUTS
    private final String nom;
    private final HashMap<String, BlagueProviderP2P> repertoireProxy;
    private final HashMap<String, Blague> listeRef;
    
    
    //CONSTRUCTEURS
    public BlagueProvider(String nom)
    {
        this.nom = nom;
        this.repertoireProxy = new HashMap<>();
        this.listeRef = new HashMap<>();
    }
    
    public BlagueProvider(String nom, HashMap<String,Blague> listeRef)
    {
        this.nom = nom;
        this.repertoireProxy = new HashMap<>();
        this.listeRef = listeRef;
    }
    
    
    //METHODES
    @Override
    public String getNom() throws RemoteException
    {
        return nom;
    }
    
    @Override
    public String[] getAllName() throws BlagueAbsenteException
    {
        String[] blagues = new String[0];
        ArrayList<String> al_blagues = new ArrayList();
        
        
        for (Map.Entry<String,Blague> e : listeRef.entrySet())
        {
            al_blagues.add(e.getKey());
        }

        blagues = al_blagues.toArray(blagues);
        
        
        if (blagues != null)
            return blagues;
        else
            throw new BlagueAbsenteException("BLAGUE_ABSENTE_EXCEPTION - GetAllName");
    }
    
    @Override
    public Blague getBlague(String n) throws BlagueAbsenteException
    {
        Blague blague;
        
        blague = listeRef.get((String)n);
        
        if (blague != null) {
            System.out.println(blague.getNom()+"\n"+blague.getQuestion()+"\n"+blague.getReponse());
            return blague;
        } else {
            throw new BlagueAbsenteException("BLAGUE_ABSENTE_EXCEPTION - GetBlague");
        }
    }
    
    public void addBlague(Blague blague)
    {
        listeRef.put(blague.getNom(), blague);
    }
    
    public void addProxy(String nomProxy, BlagueProviderP2P proxy)
    {
        repertoireProxy.put(nomProxy, proxy);
    }
    
    public void telechargerBlague(String nomBlague, BlagueProvider proxy) throws BlagueAbsenteException
    {
        listeRef.put(nomBlague, proxy.getBlague(nomBlague));
    }
    
    @Override
    public void notify(BlagueProviderP2P ref) {
        
    }
    
    @Override
    public void notifyDeconnect(BlagueProviderP2P ref) {
        
    }
    
    
    //MAIN
    // A TESTER !!
    public static void main(String[] args)
    {
        try
        {    
            // On initialise une liste de blagues par defaut
            
            String titre1 = "MonTitreDeBlague1";
            String titre2 = "MonTitreDeBlague2";
            String titre3 = "MonTitreDeBlague3";
            if (args[1].compareTo("azer") == 0) {
                titre1 = "MonTitreDeBlague4";
                titre2 = "MonTitreDeBlague5";
                titre3 = "MonTitreDeBlague6";
            }
            
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
            
            reg.rebind(provider.getNom(),proxy);
            
            // ------------------
            // ----- CLIENT -----
            // ------------------
            
            // Pour chacun des autres noms passes en parametres, on cree un proxy
            System.in.read();
            for (String nomAutreProvider : autresNomsProviders)
            {
                System.out.println(nomAutreProvider);
                BlagueProviderP2P autreProxy = (BlagueProviderP2P) reg.lookup(nomAutreProvider);
                provider.addProxy(nomAutreProvider, autreProxy);
            }
            
            // --------------------------------
            // ----- ACTIONS DIVERSES P2P -----
            // --------------------------------
            
            // On demande au proxy de nous fournir les blagues de notre client P2P
            
            System.out.println("Mon repertoire de blagues (interne)");
            String[] repertoireInterne = provider.getAllName();
            
            // On affiche nos blagues internes
            
            for (String name : repertoireInterne)
            {
                System.out.println(name);
            }
            
            // On demande au proxy etranger de nous fournir les blagues d'un autre client P2P
            
            System.out.println("Un autre repertoire de blagues (externe)");
            String[] repertoireExterne = provider.repertoireProxy.get(args[1]).getAllName();
            
            // On affiche les blagues externes
            
            for (String name : repertoireExterne)
            {
                System.out.println(name);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
