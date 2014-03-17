package codebase;

import blague.Blague;
import exceptions.BlagueAbsenteException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BlagueProviderP2P extends Remote
{
    //public void addBlague(Blague b);
    
    //public void addProxy(String nomProxy, BlagueProviderP2P proxy);
    
    public String getNom();
    
    public String[] getAllName() throws Exception, RemoteException;
    
    public Blague getBlague(String n) throws BlagueAbsenteException, RemoteException;
}
