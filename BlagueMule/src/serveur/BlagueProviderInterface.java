package serveur;

import blague.Blague;
import client.BlagueAbsenteException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BlagueProviderInterface extends Remote
{
    public String[] getAllName() throws Exception, RemoteException;
    
    public Blague getBlague(String n) throws BlagueAbsenteException, RemoteException;
}
