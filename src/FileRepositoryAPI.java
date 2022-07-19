import java.io.IOException;
import java.rmi.RemoteException;

public final class FileRepositoryAPI implements APIInterface{

    private static FileRepositoryAPI api;

    private FileRepositoryAPI(){

    }

    public static FileRepositoryAPI getInstance(){
        if(api==null){
            api = new FileRepositoryAPI();
        }
        return api;
    }

    @Override
    public void list() throws RemoteException {

    }

    @Override
    public void upload(String fileName, RemoteInputStream input) throws RemoteException, IOException {
        try{
            String fileData = "";
            while(input.available()!=-1){
                fileData+=(char)input.read();
            }
            System.out.println(fileData);


        }
        catch(Exception e){
            System.out.println("Error in reading file");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String url) throws RemoteException {

    }

    @Override
    public void downloadFile(String url) throws RemoteException {

    }
}
