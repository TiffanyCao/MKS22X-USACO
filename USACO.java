import java.util.*;
import java.io.*;

public class USACO{

    private static int row = 0;
    private static int col = 0;
    private static int elevation = 0;
    private static int nStomp = 0;
    private static int[][] map;

    /**A method that solves Lake Making
    *the method takes in a file with a map of the pasture, the final elevation, and stomping instructions
    *it reads all of the instructions, performs them according to the rules, and "creates" the lake using the new map
    *@param String filename
    *@return int the volume of the newly made lake;
    */
    public static int bronze(String filename) throws FileNotFoundException{
      File test = new File(filename); //read in file
      Scanner read = new Scanner(test);
      String line = read.nextLine(); //read in first line
      Scanner readLine = new Scanner(line);
      for(int i = 0; i < 4; i++){
        int num = readLine.nextInt(); //get the next integer
        if(row == 0){ //fill in the row
          row = num;
        }else if(col == 0){ //fill in the column
          col = num;
        }else if(elevation == 0){ //fill in the elevation requirement
          elevation = num;
        }else if(nStomp == 0){ //fill in the number of stomps
          nStomp = num;
        }
      }
      //System.out.println(line);
      //System.out.println("" + row + " " + col + " " + elevation + " " + nStomp);
      map = new int[row][col]; //create map
      for(int i = 0; i < row; i++){ //fill in the 2D array with the integers in the map
        line = read.nextLine();
        readLine = new Scanner(line);
        for(int y = 0; y < col; y++){
          map[i][y] = readLine.nextInt();
        }
      }
      //System.out.println(map());
      for(int i = 0; i < nStomp; i++){ //for each stomp
        line = read.nextLine();
        readLine = new Scanner(line);
        int rowS = readLine.nextInt() - 1; //fill in the starting row (-1 because this starts at 0)
        //System.out.println(rowS);
        int colS = readLine.nextInt() - 1; //fill in the starting column (-1 because this starts at 0)
        //System.out.println(colS);
        int stompE = readLine.nextInt(); //fill in the stomping hardness
        //System.out.println(stompE);
        stomped(rowS, colS, stompE); //perform the stomps
        //System.out.println(map());
      }
      return 0;
    }

    /**A helper method that performs the stomps of the cows
    *@param int row is the given starting row of the 3x3 cow herd
    *@param int col is the given starting column of the 3x3 cow herd
    *@param int level is the given amount of inches to be stomped down
    */
    public static void stomped(int row, int col, int level){
      int times = level; //records number of inches to stomp
      while(times != 0){ //for each inch to stomp
        int largest = 0;
        for(int i = row; i < row + 3; i++){ //check for the greatest elevation
          for(int y = col; y < col + 3; y++){
            if(map[i][y] >= largest) largest = map[i][y];
          }
        }
        for(int i = row; i < row + 3; i++){
          for(int y = col; y < col + 3; y++){
            //if the elevation at this square is greater than or equal to the greatest elevation, stomp
            if(map[i][y] >= largest) map[i][y]--;
          }
        }
        times--; //an inch has been stomped
      }
    }

    /**A method used to print out the elevation map
    *@return String
    */
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
        //System.out.println(map());
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
