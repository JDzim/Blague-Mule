package provider;

import java.util.ArrayList;

public class BlagueProvider 
{
    public static void main(String[] args)
    {
        // On recupere le nom de notre provider (args[0])
        String nom = args[0];
        ArrayList<String> autresProviders = new ArrayList<>();
        
        // On recupere le nom de tous les autres providers (args[1] à args[args.length-1])
        for (int i = 1 ; i < args.length ; i++)
        {
            autresProviders.add(args[i]);
        }
        
        
    }
}
