import java.util.*;
import java.io.*;

public class USACO{

    private static int row = 0;
    private static int col = 0;
    private static int elevation = 0;
    private static int nStomp = 0;
    private static int[][] map;

    public static int bronze(String filename) throws FileNotFoundException{
      File test = new File(filename);
      Scanner read = new Scanner(test);
      String line = read.nextLine();
      boolean temp = true;
      String num = "";
      System.out.println(line);
      for(int i = 0; i < line.length(); i++){
        if(line.charAt(i) == ' ') temp = false;
        if(temp){
          num+= line.charAt(i);
        }
        if(i == line.length() - 1) temp = false;
        if(!temp && row == 0){
          row = Integer.parseInt(num);
          num = "";
          temp = true;
        }else if(!temp && col == 0){
          col = Integer.parseInt(num);
          num = "";
          temp = true;
        }else if(!temp && elevation == 0){
          elevation = Integer.parseInt(num);
          num = "";
          temp = true;
        }else if(!temp && nStomp == 0){
          nStomp = Integer.parseInt(num);
          num = "";
          temp = true;
        }
      }
      temp = true;
      map = new int[row][col];
      for(int i = 0; i < row; i++){
        String temp = read.nextLine();
        for(int y = 0; y < col; y++){
          if()
        }
      }
      return 0;
    }

    public static void main(String[] args){
      try{
        bronze("makelake.1.in");
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
