package codebase;

import blague.Blague;
import exceptions.BlagueAbsenteException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BlagueProviderInterface extends Remote
{
    public String[] getAllName() throws Exception, RemoteException;
    
    public Blague getBlague(String n) throws BlagueAbsenteException, RemoteException;
}
