package edu.neu.info6205;

import java.io.*;

public class FileUtil {

//    public static void main(String args[])
//    {
//        String[] a={"10","20"};
//        String[] b={"10","30"};
//        writeData(a);
//        writeData(b);
//    }
    public static int readData(String matchCase,boolean fetchData)
    {
        String output="0";
        int beadCount=0;
        try(BufferedReader br= new BufferedReader(new FileReader("/Users/vk/Documents/TicTacToeDocs/testRecord.txt")))
        {
            String newLine=null;
            if(fetchData) {
                while ((newLine = br.readLine()) != null) {
                    String[] data = newLine.split(",");
                    if (data[0].length() > matchCase.length() && Integer.parseInt(data[0].substring(0, matchCase.length())) == Integer.parseInt(matchCase)) {
                        if (Integer.parseInt(data[1]) > beadCount) beadCount = Integer.parseInt(data[1]);
                        output = data[0].substring(matchCase.length(), matchCase.length() + 1);

                    }
                }
                return Integer.parseInt(output);
            }
            else
            {
                beadCount=0;
                while ((newLine = br.readLine()) != null) {
                    String[] data = newLine.split(",");
                    if (Integer.parseInt(data[0])== Integer.parseInt(matchCase)) {
                        if (Integer.parseInt(data[1]) > beadCount) beadCount = Integer.parseInt(data[1]);
                    }
                }
                return beadCount;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    return 0;
    }

    public static void writeData(String[] input)
    {
        int beadCount=readData(input[0],false);
        try(BufferedWriter bw= new BufferedWriter(new FileWriter("/Users/vk/Documents/TicTacToeDocs/testRecord.txt",true)))
        {
            if(beadCount==0) {
                bw.write(input[0]+","+input[1]);
                bw.newLine();
            }
            else
            {
                input[1]=String.valueOf(beadCount+Integer.parseInt(input[1]));
                bw.write(input[0]+","+input[1]);
                bw.newLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
