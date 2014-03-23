package codebase;

import blague.Blague;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public interface BlagueProviderP2P extends Remote
{
    public void addBlague(Blague blague);
    
    public void addProxy(String nomProxy, BlagueProviderP2P proxy);
    
    public String getNom() throws RemoteException;
    
    public String[] getAllName() throws Exception, RemoteException;
    
    public Blague getBlague(String nom) throws BlagueAbsenteException, RemoteException;
    
    public void notify(BlagueProviderP2P ref);
    
    public void notifyDeconnect(BlagueProviderP2P ref);
}
