package client;

import blague.Blague;
import codebase.BlagueProviderInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BlagueProviderClient 
{
    public static void main(String[] args)
    {
        try
        {
            String host = (args.length < 1) ? "localhost" : args[0];
            
            // 1 - Le client crée un objet de type BlagueProviderClient
            BlagueProviderClient client = new BlagueProviderClient();
            
            // 2 - On creé un proxy de l'objet Receiver
            //BlagueProviderInterface proxy = (BlagueProviderInterface)UnicastRemoteObject.exportObject(client,0);
            
            // 2 - On récupère la registry du serveur (lookup)
            Registry reg = LocateRegistry.getRegistry(host);
            BlagueProviderInterface proxy = (BlagueProviderInterface) reg.lookup("BlagueProvider");
            
            // 3 - On demande au proxy de nous fournir le tableau des blagues, on en choisit une, on demande au proxy la blague choisie, on l'affiche
            String[] annuaire = proxy.getAllName();
            
            for (String nom : annuaire) {
                System.out.println(nom);
            }
            
            String nom = annuaire[annuaire.length-2];
            
            System.out.println(nom);
            
            String s = "";
            s = proxy.getBlague(nom).getNom()+"\n"+proxy.getBlague(nom).getQuestion()+"\n"+proxy.getBlague(nom).getReponse();
            System.out.println(s);
            
            Blague blague = null;
            blague = (Blague)(proxy.getBlague(nom));
            
            System.out.println(blague.getNom());
            System.out.println("\n");
            System.out.println(blague.getQuestion());
            System.out.println("\n");
            System.out.println(blague.getReponse());
        }
        catch (RemoteException re)
        {
            System.out.println("ERREUR CLIENT - Main - RemoteException");
            re.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("ERREUR CLIENT - Main");
            e.printStackTrace();
        }
    }
}
