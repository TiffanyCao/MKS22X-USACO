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
      row = readLine.nextInt(); //fill in the row
      col = readLine.nextInt(); //fill in the column
      elevation = readLine.nextInt(); //fill in the elevation requirement
      nStomp = readLine.nextInt(); //fill in the number of stomps
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
      return solve();
    }

    /**A helper method that performs the stomps of the cows
    *@param int row is the given starting row of the 3x3 cow herd
    *@param int col is the given starting column of the 3x3 cow herd
    *@param int level is the given amount of inches to be stomped down
    */
    public static void stomped(int row, int col, int level){
        int largest = 0;
        for(int i = row; i < row + 3; i++){ //check for the greatest elevation
          for(int y = col; y < col + 3; y++){
            if(map[i][y] >= largest) largest = map[i][y];
          }
        }
        largest = largest - level; //subtract the depth
        for(int i = row; i < row + 3; i++){
          for(int y = col; y < col + 3; y++){
            //if the elevation at this square is greater than or equal to the greatest elevation, stomp
            if(map[i][y] >= largest) map[i][y] = largest;
          }
        }
    }

    /**A helper method that takes the final water level and goes through the map for the aggregated depth
    *the depth is taken by subtracting the each square's elevation from the final water level and adding them
    *negative depths are not counted (i.e. if the elevation is higher than the final water level)
    *the aggregated depth is multiplied to 72 * 72 for the final volume in cubic inches
    *@return int the volume of the lake in cubic inches
    */
    public static int solve(){
      int sum = 0; //aggregated depth
      for(int i = 0; i < map.length; i++){
        for(int y = 0; y < map[i].length; y++){
          map[i][y] = elevation - map[i][y]; //subtract elevation from final water level
          if(map[i][y] >= 0) sum+= map[i][y]; //if it's greater than 0, add to depth count
        }
      }
      clear();
      return sum * 72 * 72; //mutiply to find answer in cubic inches
    }

    /**A method that clears the variables so that multiple tests can be made
    */
    private static void clear(){
      row = 0;
      col = 0;
      elevation = 0;
      nStomp = 0;
      time = 0;
    }

    /**A method used to print out the elevation map
    *@return String
    */
    public static String bronzeMap(){
      String result = "";
      for(int i = 0; i < map.length; i++){
        for(int y = 0; y < map[i].length; y++){
          if(map[i][y] < 0) result += "-- ";
          if(map[i][y] < 10 && map[i][y] >= 0) result += " " + map[i][y] + " ";
          if(map[i][y] >= 10) result += map[i][y] + " ";
          if(y == map[i].length - 1) result += "\n";
        }
      }
      return result;
    }

    private static int time = 0;
    private static int[] start = new int[2];
    private static int[] end = new int[2];
    private static char[][] pasture;
    private static int[][] moves = {{-1, 0},
                                    {0, -1},
                                    {1, 0},
                                    {0, 1}};
    private static int[][] movement;
    private static int[][] tempM;

    public static int silver(String filename) throws FileNotFoundException{
      File test = new File(filename); //read in file
      Scanner read = new Scanner(test);
      String line = read.nextLine(); //read in first line
      Scanner readLine = new Scanner(line);
      row = readLine.nextInt(); //fill in the row
      col = readLine.nextInt(); //fill in the column
      time = readLine.nextInt(); //fill in the time; also the number of steps
      pasture = new char[row][col]; //a map of the pasture
      movement = new int[row][col]; //a map recording the number of possible ways to get to each sqaure given the time and start coordinate
      for(int i = 0; i < row; i++){ //fill in the pasture and movement boards with the integers in the map
        line = read.nextLine();
        for(int y = 0; y < col; y++){
          pasture[i][y] = line.charAt(y);
          if(pasture[i][y] != '*'){ //fill in the trees
            movement[i][y] = 0;
          }else movement[i][y] = -1;
        }
      }
      line = read.nextLine(); //line with the start and end coordinates
      readLine = new Scanner(line);
      start[0] = readLine.nextInt() - 1; //fill in the starting row (-1 because this starts at 0)
      start[1] = readLine.nextInt() - 1; //fill in the starting column (-1 because this starts at 0)
      end[0] = readLine.nextInt() - 1; //fill in the ending row (-1 because this starts at 0)
      end[1] = readLine.nextInt() - 1; //fill in the ending column (-1 because this starts at 0)
      //System.out.println("" + row + " " + col + " " + time + " " + start[0] + " " + start[1] + " " + end[0] + " " + end[1]);
      return silverSolve(start[0], start[1], time); //helper method
      //return -1;
    }

    /**A method used to print out the pasture map
    *@return String
    */
    public static String silverMap(){
      String result = "";
      for(int i = 0; i < pasture.length; i++){
        for(int y = 0; y < pasture[i].length; y++){
          result += "" + pasture[i][y];
          if(y == pasture[i].length - 1) result += "\n";
        }
      }
      return result;
    }

    /**A method used to print out the movements map
    *The movements map tells us the number of possible ways to get to each sqaure in the given amount of time from the given start coordinate
    *@return String
    */
    public static String silverMap2(){
      String result = "";
      for(int i = 0; i < movement.length; i++){
        for(int y = 0; y < movement[i].length; y++){
          result += "" + movement[i][y] + " ";
          if(y == movement[i].length - 1) result += "\n";
        }
      }
      return result;
    }

    /**A method that checks if a coordinate in within bounds of the map and if the coordinate is empty and can be moved on
    *@param int row
    *@param int col
    *@return boolean
    */
    public static boolean inBounds(int row, int col){
      if(row >= 0 && row < pasture.length && col >= 0 && col < pasture[0].length && pasture[row][col] != '*'){
        return true;
      }
      return false;
    }

    /**A recursive helper method that tries all possible moves that can be made in the given time and records the ending positions of each pathway
    *@param int row
    *@param int col
    *@param int count, this variable keeps track of the time that has passed
    *@return int the number of possible ways to get to the given end coordinates in the given time
    */
    /*public static int silverSolve(int row, int col, int count){
      if(count == time){ //if time is up, record the move on the board of movements
        movement[row][col]++;
      }else{ //if time isn't up and moves can still be made
        for(int i = 0; i < 4; i++){ //loop through all possible moves
          if(inBounds(row + moves[i][0], col + moves[i][1])){ //check if the move is valid and the sqaure is empty
            silverSolve(row + moves[i][0], col + moves[i][1], count+1); //recursive call on the new spot, with the time adding up
          }
        }
      }
      return movement[end[0]][end[1]]; //return the number of possible ways to get to given end coordinate
    }*/

    /**A helper method that finds the number of possible moves that can be made in the given time and records the ending positions of each pathway
    *@param int row
    *@param int col
    *@param int count, this variable keeps track of the number of moves
    *@return int the number of possible ways to get to the given end coordinates in the given time
    */
    public static int silverSolve(int row, int col, int count){
      movement[row][col] = 1; //start
      for(int t = 0; t < count; t++){ //count the time
        tempM = new int[pasture.length][pasture[0].length];
        for(int i = 0; i < movement.length; i++){
          for(int y = 0; y < movement[i].length; y++){
            if(pasture[i][y] != '*'){
              for(int x = 0; x < 4; x++){ //otherwise find the sum of the surrounding squares
                if(inBounds(i + moves[x][0], y + moves[x][1])){ //don't count the -1
                  tempM[i][y] += movement[i + moves[x][0]][y + moves[x][1]];
                }
              }
            }
          }
        }
        movement = tempM; //update the movement board
      }
      return movement[end[0]][end[1]]; //return the number of possible ways to get to given end coordinate
    }

    public static void main(String[] args){
      try{

        System.out.println(bronze("makelake.1.in")); //342144
        System.out.println(bronze("makelake.2.in")); //102762432
        System.out.println(bronze("makelake.3.in")); //1058992704
        System.out.println(bronze("makelake.4.in")); //753121152
        System.out.println(bronze("makelake.5.in")); //1028282688


        System.out.println(silver("ctravel.1.in")); //1
        System.out.println(silver("ctravel.2.in")); //74
        System.out.println(silver("ctravel.3.in")); //6435
        System.out.println(silver("ctravel.4.in")); //339246
        System.out.println(silver("ctravel.5.in")); //0
      }catch(FileNotFoundException e){
        System.out.println("File not found");
      }
    }
}
