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
      Scanner readLine = new Scanner(line);
      for(int i = 0; i < 4; i++){
        int num = readLine.nextInt();
        if(row == 0){
          row = num;
        }else if(col == 0){
          col = num;
        }else if(elevation == 0){
          elevation = num;
        }else if(nStomp == 0){
          nStomp = num;
        }
      }
      System.out.println(line);
      System.out.println("" + row + " " + col + " " + elevation + " " + nStomp);
      map = new int[row][col];
      for(int i = 0; i < row; i++){
      String temp = read.nextLine();
      readLine = new Scanner(temp);
        for(int y = 0; y < col; y++){
          map[i][y] = readLine.nextInt();
        }
      }
      return 0;
    }

    public static String map(){
      String result = "";
      for(int i = 0; i < map.length; i++){
        for(int y = 0; y < map[i].length; y++){
          if(map[i][y] < 10) result += " " + map[i][y] + " ";
          if(map[i][y] >= 10) result += map[i][y] + " ";
          if(y == map[i].length - 1) result += "\n";
        }
      }
      return result;
    }

    public static void main(String[] args){
      try{
        bronze("makelake.1.in");
        System.out.println(map());
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
