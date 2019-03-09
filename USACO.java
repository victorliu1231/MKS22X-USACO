import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO{

    //*************************************************************** */
    //WE ARE NOT WRITING TO A FILE. JUST RETURN AN INT AS THE SOLUTION.
    //cleanup redundant code later
    //*************************************************************** */

    public static int bronze(String filename){
        //For this one, here is how you interpret it.
        //The -- represents the water. So a 4 would be interpreted as 4 in below the water surface.
        //This means aggregated depth is adding all the different depths together (4 in below surface, 8 in below surface, etc).
        //Then you multiply that agg.depth by 72 in x 72 in b/c every square represented in the diagram is 6 ft x 6 ft, aka 72 in by 72 in.
        //Idk if we have to code in the bounds given by the problem.
        //Use the mini steps along the way in the prompt to help test. Use the files in the test folder
        try{
          //all to initialize the data structure
          File file = new File(filename);
          Scanner s = new Scanner(file);
          int index = 0;
          int R = 0; //to initialize
          int C = 0;
          int E = 0;
          int N = 0;
          int[][] grid = new int[][]{}; //to initialize
          ArrayList<Integer[]> instructions = new ArrayList<>();
          while (s.hasNextLine()){
            if (index == 0){
              String[] initializer = s.nextLine().split(" ");
              R = Integer.parseInt(initializer[0]);
              C = Integer.parseInt(initializer[1]);
              E = Integer.parseInt(initializer[2]);
              N = Integer.parseInt(initializer[3]);
              grid = new int[R][C];
            }
            if (index > 0 && index <= R){
              String[] gridLine = s.nextLine().split(" ");
              for (int i = 0; i < gridLine.length; i++){
                grid[index-1][i] = Integer.parseInt(gridLine[i]); //index-1 is to calibrate the index for grid
              }
            }
            if (index > R){
              String[] instructValues = s.nextLine().split(" ");
              Integer[] instructInts = new Integer[3];
              for (int i = 0; i < instructValues.length; i++){
                instructInts[i] = Integer.parseInt(instructValues[i]);
              }
              instructions.add(instructInts);
            }
            index++;
          }

          //System.out.println("R: "+R+", C: "+C+", E: "+E+", N: "+N);
          //System.out.println(printArray(grid));
          //System.out.println(printInstructions(instructions));

          //now to actually act on the instructions
          int[][] surroundings = new int[][]{
            {-1,-1},
            {-1,0},
            {-1,1},
            {0,-1},
            {0,1},
            {0,0},
            {1,-1},
            {1,0},
            {1,1}
          };
          for (int instruction = 0; instruction < instructions.size(); instruction++){
            int r = instructions.get(instruction)[0];
            int c = instructions.get(instruction)[1];
            int highestElev = grid[r][c];
            //finds the highest elevation in the 3x3 surroundings
            for (int square = 0; square < 9; square++){
              int squareR = r + surroundings[square][0];
              int squareC = c + surroundings[square][1];
              if (squareR >= 0 && squareR < R &&
                  squareC >= 0 && squareC < C &&
                  grid[squareR][squareC] > highestElev){
                    //grid[squareR][squareC]] is the square that is being parsed through
                    highestElev = grid[squareR][squareC];
              }
            }
            //now to stomp
            for (int i = 0; i < instructions.get(instruction)[2] && highestElev > 0; i++){ //instructions.get(instruction)[2] is the # of times to stomp
              for (int square = 0; square < 9; square++){
                int squareR = r + surroundings[square][0];
                int squareC = c + surroundings[square][1];
                if (squareR >= 0 && squareR < R &&
                    squareC >= 0 && squareC < C){
                      //grid[squareR][squareC] is the square that is being parsed through
                      if (grid[squareR][squareC] == highestElev){
                        grid[squareR][squareC] = grid[squareR][squareC] - 1;
                      }
                }
              }
              highestElev--;
            }
            //System.out.println(printArray(grid)); System.out.println("-------------");
          }

          //now to calculate depth
          //calculates aggregated depth
          int aggreDepth = 0;
          for (int r = 0; r < R; r++){
            for (int c = 0; c < C; c++){
              if (E - grid[r][c] > 0){ //if the lake actually will flood these squares
                aggreDepth+= E - grid[r][c];
              }
            }
          }
          return aggreDepth * 72 * 72; //this calculation is cuz each square represents a 6ft by 6ft plot, which is 72in by 72 in
        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        return -1; //just to compile, actually won't be reached
    }


    public static int silver(String filename){
        //Combinatorics with ability to repeat points lol
        //recursive backtracking is not a good idea here - takes too long
        //T is just the total # of seconds to reach the destination - each second, cow moves a square
        try{
          //all the initialize the data structure
          File file = new File(filename);
          Scanner s = new Scanner(file);
          int index = 0;
          int N = 0; //to initialize
          int M = 0;
          int T = 0;
          char[][] grid = new char[][]{};
          ArrayList<Integer[]> instructions = new ArrayList<>();
          while (s.hasNextLine()){
            if (index == 0){
              String[] initializer = s.nextLine().split(" ");
              N = Integer.parseInt(initializer[0]);
              M = Integer.parseInt(initializer[1]);
              T = Integer.parseInt(initializer[2]);
              grid = new char[N][M];
            }
            if (index > 0 && index <= N){
              String gridLine = s.nextLine();
              for (int i = 0; i < gridLine.length(); i++){
                grid[index-1][i] = gridLine.charAt(i); //index-1 is to calibrate index in regards to grid index
              }
            }
            if (index >= N + 1){
              String[] instructValues = s.nextLine().split(" ");
              Integer[] instructInts = new Integer[4];
              for (int i = 0; i < instructValues.length; i++){
                instructInts[i] = Integer.parseInt(instructValues[i]);
              }
              instructions.add(instructInts);
            }
            index++;
          }

          //System.out.println("R: "+R+", C: "+C+", E: "+E+", N: "+N);
          //System.out.println(printArray(grid));
          //System.out.println(printInstructions(instructions));


        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        return -1; //just to compile
    }



    //debugging purposes only
    public static String printArray(int[][] array){
      String ans = "";
      for (int r = 0; r < array.length; r++){
        for (int c = 0; c < array[r].length; c++){
          ans+= array[r][c];
          if (c != array[r].length - 1){
            ans+= " ";
          }
        }
        if (r != array.length - 1){
          ans+= "\n";
        }
      }
      return ans;
    }

    public static String printArray(char[][] array){
      String ans = "";
      for (int r = 0; r < array.length; r++){
        for (int c = 0; c < array[r].length; c++){
          ans+= array[r][c];
          if (c != array[r].length - 1){
            ans+= " ";
          }
        }
        if (r != array.length - 1){
          ans+= "\n";
        }
      }
      return ans;
    }

    public static String printInstructions(ArrayList<Integer[]> array){
      String ans = "";
      for (int r = 0; r < array.size(); r++){
        for (int c = 0; c < array.get(r).length; c++){
          ans+= ""+array.get(r)[c];
          if (c != array.get(r).length - 1){
            ans+= " ";
          }
        }
        if (r != array.size() - 1){
          ans+= "\n";
        }
      }
      return ans;
    }

}
