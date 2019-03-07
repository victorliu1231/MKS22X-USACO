import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO{

    //*************************************************************** */
    //WE ARE NOT WRITING TO A FILE. JUST RETURN AN INT AS THE SOLUTION.
    //*************************************************************** */

    public static int bronze(String filename){
        //For this one, here is how you interpret it.
        //The -- represents the water. So a 4 would be interpreted as 4 in below the water surface.
        //This means aggregated depth is adding all the different depths together (4 in below surface, 8 in below surface, etc).
        //Then you multiply that agg.depth by 72 in x 72 in b/c every square represented in the diagram is 6 ft x 6 ft, aka 72 in by 72 in.
        //Idk if we have to code in the bounds given by the problem.
        //Use the mini steps along the way in the prompt to help test. Use the files in the test folder
        //to test when you are finished with code (this means you need a Scanner!).
        //dw about file instructions cuz he's only inputting valid files
        try{
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
              Integer[] instructInts = new Integer[4];
              for (int i = 0; i < instructValues.length; i++){
                instructInts[i] = Integer.parseInt(instructValues[i]);
              }
              instructions.add(instructInts);
            }
            index++;
          }

          System.out.println("R: "+R+", C: "+C+", E: "+E+", N: "+N);
          System.out.println(printArray(grid));
          System.out.println(instructions);
        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        //reading in the file is an absolute mess...
        return -1; //dummy value
    }

    public static int silver(String filename){
        //Combinatorics with ability to repeat points lol
        //recursive backtracking is not a good idea here - takes too long
        //T is just the total # of seconds to reach the destination - each second, cow moves a square
        try{
          File file = new File(filename);
          Scanner s = new Scanner(file);
          int index = 0;
          int N = 0; //to initialize
          int M = 0;
          int T = 0;
          char[][] grid = new char[][]{};
          ArrayList<Integer[]> instructions = new ArrayList<>();
          while (s.hasNext()){
            if (index == 0){
              N = Integer.parseInt(s.next());
            }
            if (index == 1){
              M = Integer.parseInt(s.next());
            }
            if (index == 2){
              T = Integer.parseInt(s.next());
              grid = new char[N][M];
            }
            if (index > 2 && index <= 2 + N){
              String gridLine = s.next();
              for (int i = 0; i < gridLine.length(); i++){
                //System.out.println("r: "+(index-3)+", c: "+i);
                grid[index-3][i] = gridLine.charAt(i); //index-3 is to calibrate index in regards to grid index
              }
            }
            if (index >= 2 + N + 1){
              String[] instructValues = s.next().split(" ");
              Integer[] instructInts = new Integer[4];
              for (int i = 0; i < instructValues.length; i++){
                instructInts[i] = Integer.parseInt(instructValues[i]);
              }
              instructions.add(instructInts);
            }
            index++;
          }

          //System.out.println("N: "+N+", M: "+M+", T: "+T);
          System.out.println(printArray(grid));
          //System.out.println(instructions);
        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        return -1; //dummy value
    }

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
      return ans;
    }

}
