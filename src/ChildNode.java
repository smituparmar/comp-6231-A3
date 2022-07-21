import mpi.Status;
import mpi.MPI;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ChildNode {
    static final int PORT = 6231;
    static final int MASTER = 0;
    static final int DEST = 1;

    static final int NCLIENTS_TEST = 4;
    static final int BUF_LENGTH_TEST = 100;

    public static void main(String[] args) {
        byte[] data = new byte[1024];
        HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
        HashMap<String, Integer> lengthMap = new HashMap<>();
        char[] fileNameArray = new char[1024];
        while(true){
            try{
                MPI.COMM_WORLD.Recv(fileNameArray, 0, fileNameArray.length, MPI.CHAR, 0, 0);
                String[] fileNameAndAction = String.valueOf(fileNameArray).split("/");
                String fileName = fileNameAndAction[0];
                String action = fileNameAndAction[1];
                System.out.println("fileName:"+fileName+" action:"+ action);

                MPI.COMM_WORLD.Recv(data, 0, data.length, MPI.BYTE, 0, 1);
                String s = "";
                System.out.println("data in child node is here");
                for (int i = 0; i < data.length; i++) {
                    if (data[i] == 0) break;
                    s += (char)data[i];
                }
                System.out.println(lengthMap);
                if(!dataMap.containsKey(fileName)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(s);
                    dataMap.put(fileName, list);
                    lengthMap.put(fileName, 1);
                }
                else{
                    ArrayList<String> list = dataMap.get(fileName);
//                    System.out.println(s+" "+list);
                    list.add(s);
                    dataMap.put(fileName, list);
                    lengthMap.put(fileName, list.size());
                }
                System.out.println("we put string in childNode named:"+fileName);
                System.out.println(MPI.COMM_WORLD.Rank()+" "+lengthMap);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

