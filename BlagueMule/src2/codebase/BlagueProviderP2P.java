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
    public void addBlague(Blague b) throws RemoteException;
    
    public void addProxy(String nomProxy, BlagueProviderP2P proxy) throws RemoteException;
    
    public String getNom() throws RemoteException;
    
    public String[] getAllName() throws RemoteException;
    
    public Blague getBlague(String n) throws BlagueAbsenteException, RemoteException;
}
