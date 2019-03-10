import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class USACO{
    public static int bronze(String filename){
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
        return -1; //when invalid files are inputted
    }


    public static int silver(String filename){
        try{
          //all the initialize the data structure
          File file = new File(filename);
          Scanner s = new Scanner(file);
          int index = 0;
          int N = 0; //to initialize
          int M = 0;
          int T = 0;
          int startR = 0;
          int startC = 0;
          int endR = 0;
          int endC = 0;
          char[][] grid = new char[][]{};
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
              String[] coordinates = s.nextLine().split(" ");
              startR = Integer.parseInt(coordinates[0]) - 1; //-1's are to calibrate coordinates in test file with array notation
              startC = Integer.parseInt(coordinates[1]) - 1;
              endR = Integer.parseInt(coordinates[2]) - 1;
              endC = Integer.parseInt(coordinates[3]) - 1;
            }
            index++;
          }

          //System.out.println("N: "+N+", M: "+M+", T: "+T);
          //System.out.println(printArray(grid));
          //System.out.println("startR: "+startR+", startC: "+startC+", endR: "+endR+", endC: "+endC);

          int[][] paths = new int[N][M];
          int[][] storage = new int[N][M]; //used for storing values when building the paths board so as to not interfere with path board
          int[][] surroundings = new int[][]{
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
          };
          for (int t = 1; t <= T; t++){
            //puts first spot on the board
            if (t == 1){
              for (int i = 0; i < 4; i++){
                 //surroundings[i][0] is the row incrememt. surroundings[i][1] is the col increment
                if (startR + surroundings[i][0] >= 0 && startR + surroundings[i][0] < N &&
                    startC + surroundings[i][1] >= 0 && startC + surroundings[i][1] < M &&
                    grid[startR + surroundings[i][0]][startC + surroundings[i][1]] != '*'){
                      paths[startR + surroundings[i][0]][startC + surroundings[i][1]] = 1;
                      storage[startR + surroundings[i][0]][startC + surroundings[i][1]] = 1;
                }
              }
            }
            else {
              //stores the path values in storage board
              for (int r = 0; r < N; r++){
                for (int c = 0; c < M; c++){
                  int sumOfSurroundings = 0;
                  for (int i = 0; i < 4; i++){
                    if (r + surroundings[i][0] >= 0 && r + surroundings[i][0] < N &&
                        c + surroundings[i][1] >= 0 && c + surroundings[i][1] < M){ //surroundings[i][0] is the row incrememt. surroundings[i][1] is the col increment
                      sumOfSurroundings+= paths[r + surroundings[i][0]][c + surroundings[i][1]];
                    }
                  }
                  if (grid[r][c] != '*'){
                    storage[r][c] = sumOfSurroundings;
                  }
                }
              }
              //assigns the path values from storage board in path board
              for (int r = 0; r < N; r++){
                for (int c = 0; c < M; c++){
                  paths[r][c] = storage[r][c];
                }
              }
            }
            //System.out.println("t: "+t);
            //System.out.println(printArray(storage)); System.out.println("---------------");
          }

          //System.out.println("*************************");
          //System.out.println(printArray(grid));
          return paths[endR][endC];

        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        return -1; //when invalid files are inputted
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
