import mpi.Status;
import mpi.MPI;

public class ChildNode {
    static final int PORT = 6231;
    static final int MASTER = 0;
    static final int DEST = 1;

    static final int NCLIENTS_TEST = 4;
    static final int BUF_LENGTH_TEST = 100;
    public static void main(String[] args) {
        char[] buf = new char[10];
        int[] foo = new int[BUF_LENGTH_TEST];
        int tag = 0;
//        for(int i = 0; i < NCLIENTS_TEST; i++) {
//            Status s = MPI.COMM_WORLD.Recv(buf, 0, buf.length,MPI.CHAR,MASTER,MPI.ANY_TAG);
//
//        }
        // sending signal to master to terminate the simulation
        MPI.COMM_WORLD.Isend(foo, 0, foo.length, MPI.INT, MASTER, 0);
    }

}

