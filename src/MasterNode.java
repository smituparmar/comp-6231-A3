import mpi.MPI;
import mpi.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class MasterNode {
    static final int PORT = 6231;
    static final int WORKER = 3;

    static final int BUF_LENGTH_TEST = 1024;

    public static void main(String[] args) {
        System.out.println("Master");
        System.out.println("TRY start listening");
        FileRepositoryAPI api = FileRepositoryAPI.getInstance();
        HashMap<String, ArrayList<Integer>> fileMap = new HashMap<>();
        HashMap<String, String> nameMap = new HashMap<>();

        try {
            RMIServer.start(PORT);
            RMIServer.register(api);
            System.out.println("we are in masterNode");
            try{
                while(true) {
                    //uplod section
                    byte[] data = new byte[1024];
                    char[] fileNameArray = new char[1024];
                    System.out.println("---------upload-----------");
                    MPI.COMM_WORLD.Recv(fileNameArray, 0, fileNameArray.length, MPI.CHAR, 0, 0);
                    String[] fileNameAndAction = String.valueOf(fileNameArray).split("/");
                    String fileName = fileNameAndAction[0];
                    String action = fileNameAndAction[1];
                    if(fileName!="send files"){
                        nameMap.put(fileName, fileName);
                    }
                    System.out.println("fileName:"+fileName+" action:"+ action);
                    System.out.println("we are now uploading");

                    if(action=="upload"){
                        MPI.COMM_WORLD.Recv(data, 0, data.length, MPI.BYTE, 0, 1);
                        System.out.println("data in master node is here");
//                    for (int i = 0; i < data.length; i++) {
//                        if (data[i] == 0) break;
//                        System.out.print((char) data[i]);
//                    }
                        Random random = new Random();
                        int node = random.nextInt(14) + 1;
                        System.out.println(node);
                        if(fileMap.containsKey(fileName)){
                            ArrayList<Integer> list = fileMap.get(fileName);
                            list.add(node);
                            fileMap.put(fileName, list);
                        }
                        else{
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(node);
                            fileMap.put(fileName, list);
                        }
                        MPI.COMM_WORLD.Send(fileNameArray, 0, fileNameArray.length, MPI.CHAR, node, 0);
                        MPI.COMM_WORLD.Send(data, 0, data.length, MPI.BYTE, node, 1);

                    }

                    if(action=="list"){
                        System.out.println("---------read-----------");
                        Set<String> fileList = nameMap.keySet();
                        String files = "";
                        for(String file:fileList){
                            files += file+"\n";
                        }
                        byte[] fileListBytes = files.getBytes();
                        System.out.println("files List:"+files);
                    }




//                    Status s = MPI.COMM_WORLD.Recv(data, 0, data.length, MPI.BYTE, 0, 4);
//                    System.out.println("---------read-----------");
//                    System.out.println("we are in read:"+s.countInBytes+" "+s);
//                    Set<String> fileList = nameMap.keySet();
//                    String files = "";
//                    for(String file:fileList){
//                        files += file+"\n";
//                    }
//                    byte[] fileListBytes = files.getBytes();
//                    System.out.println("files List:"+files);
//                    if(files!=""){
//                        MPI.COMM_WORLD.Send(fileListBytes, 0, fileListBytes.length, MPI.BYTE, s.source, 5);
//                    }


//
//                    byte[] con = new byte[128];
//
//                    MPI.COMM_WORLD.Send(con, 0, con.length, MPI.BYTE, 0, 100);
//                    String conString = String.valueOf(con);
//                    if(conString=="continue"){
//                        continue;
//                    }
//

                }

            }
            catch (Exception e){
                System.out.println("error in master node:"+e.getMessage());
                e.printStackTrace();
            }
        }
        catch(Exception e){
            System.out.println("ERR " + e.getMessage());
            e.printStackTrace();
        }
    }
}
