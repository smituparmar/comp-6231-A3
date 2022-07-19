import java.io.File;
import java.io.FileInputStream;
import java.rmi.Naming;

public class RMIClient {
    static final int PORT = 6231;
//    public static void main(String[] args) {
////        try {
////            System.out.println("OK looking up");
////            APIInterface remoteapi = (APIInterface) Naming.lookup(RMIServer.getURI(MPJAndRMIDemo.PORT, "SampleAPI"));
//////            String eq = Problem.generateEq();
//////            int res = remoteapi.foo(eq);
////            System.out.println(String.format("OK called foo "+eq+ " through RMI, result: %d", res));
////        }
////        catch(Exception e){
////            System.out.println("ERR " + e.getMessage());
////            e.printStackTrace();
////        }
//    }

    public static void main(String[] args) {
        try{
            System.out.println("Looking up");
            APIInterface remoteapi = (APIInterface) Naming.lookup(RMIServer.getURI(PORT, "FileRepositoryAPI"));
            RemoteInputStream remoteInputStream = new RemoteInputStream(new FileInputStream(new File("C:/Users/syp98/OneDrive/Desktop/sample.txt")));
            remoteapi.upload("sample.txt", remoteInputStream);
        }
        catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }
    }
}