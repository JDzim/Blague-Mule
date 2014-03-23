package codebase;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public interface AnnuaireInterface extends Remote
{
    public BlagueProviderP2P[] register(BlagueProviderP2P ref) throws RemoteException;
    
    public void disconnect(BlagueProviderP2P ref) throws RemoteException;
}
