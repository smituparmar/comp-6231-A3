import mpi.MPI;

import java.util.HashMap;

public class MasterNode {
    static final int PORT = 6231;
    static final int MASTER = 0;
    static final int DEST = 1;
    static final int WORKER = 3;

    static final int BUF_LENGTH_TEST = 100;

    public static void main(String[] args) {
        System.out.println("Master");
        System.out.println("TRY start listening");
        FileRepositoryAPI api = FileRepositoryAPI.getInstance();
        HashMap<String, String> map = new HashMap<>();

        try {
            RMIServer.start(PORT);
            RMIServer.register(api);
            System.out.println("we are in masterNode");
            /****/
//            S
//            for(int i = 0; i < NCLIENTS_TEST; i++) {
//                new Thread(() -> { RMIClient.main(null); }).start();
//            }
//            int[] buf = new int[BUF_LENGTH_TEST];
//            MPI.COMM_WORLD.Recv(buf, 0, buf.length, MPI.INT, DEST, 0);
        }
        catch(Exception e){
            System.out.println("ERR " + e.getMessage());
            e.printStackTrace();
        }
    }
}
