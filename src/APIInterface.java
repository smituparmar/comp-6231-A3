import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface APIInterface extends Remote {
    public void list() throws RemoteException;
    public void upload(String fileName, RemoteInputStream input) throws RemoteException, IOException;
    public void deleteFile(String url) throws RemoteException;
    public void downloadFile(String url) throws RemoteException;
}
