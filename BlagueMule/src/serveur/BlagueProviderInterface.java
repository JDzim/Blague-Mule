package serveur;

import blague.Blague;
import java.rmi.Remote;
import java.rmi.RemoteException;
import serveur.BlagueAbsenteException;

public interface BlagueProviderInterface extends Remote
{
    public String[] getAllName() throws Exception, RemoteException;
    
    public Blague getBlague(String n) throws BlagueAbsenteException, RemoteException;
}
