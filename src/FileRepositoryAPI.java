import mpi.MPI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
    public String list() throws RemoteException {
        char[] data = "send files/list".toCharArray();
        RMIServer.MPIProxy.Sendrecv(data, 0, data.length, MPI.CHAR, 0, 0, data, 0, data.length, MPI.BYTE, 0, 5);
        System.out.println("we got some nerves here");
        for(int i=0;i< data.length;i++){
            System.out.print((char)data[i]);
        }
        return String.valueOf(data);
    }

    @Override
    public void upload(String fileName, byte[] data) throws RemoteException, IOException {
        try{
            System.out.println("we are in RMI APIs right now");
            String[] fileNameArray = fileName.split("/");
            fileNameArray[fileNameArray.length-1]+="/upload";
            char[] charArray = fileNameArray[fileNameArray.length-1].toCharArray();
            System.out.println("ready to send data");
            RMIServer.MPIProxy.Send(charArray, 0, charArray.length, MPI.CHAR, 0, 0);
            RMIServer.MPIProxy.Send(data, 0, data.length, MPI.BYTE, 0, 1);
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
