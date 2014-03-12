package provider;

import blague.Blague;
import codebase.BlagueProviderInterface;
import exceptions.BlagueAbsenteException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class BlagueProvider implements BlagueProviderInterface
{
    public BlagueProvider()
    {
        
    }
    
    @Override
    public String[] getAllName() throws Exception, RemoteException
    {
        return null;
    }
    
    @Override
    public Blague getBlague(String n) throws BlagueAbsenteException, RemoteException
    {
        return null;
    }
    
    public static void main(String[] args)
    {
        try
        {
            // On recupere le nom de notre provider (args[0])
            String nom = args[0];
            ArrayList<String> autresProviders = new ArrayList<>();

            // On recupere le nom de tous les autres providers (args[1] à args[args.length-1])
            for (int i = 1 ; i < args.length ; i++)
            {
                autresProviders.add(args[i]);
            }

            BlagueProvider bp = new BlagueProvider();
        }
        catch (Exception e)
        {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
}
