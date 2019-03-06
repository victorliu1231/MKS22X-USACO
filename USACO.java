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
          int index = 0;
          int[][] board = new int[0][0]; //just to initialize
          String R = "";
          int[] values = new int[4];
          File file = new File(filename);
          Scanner s = new Scanner(file);
          while (s.hasNextLine()){
            if (index == 0){
              String firstLine = s.nextLine();
              String storer = "";
              int i = 0;
              while (i != firstLine.length()){
                //values[i/2] = Integer.parseInt(firstLine.substring(i,i+1));
                if (firstLine.charAt(i) == ' '){
                  storer = "";
                }
                storer+= firstLine.charAt(i);
                value.add(firstLine);
              }
              R = values.get(0);
              int C = values[1];
              int E = values[2];
              int N = values[3];
              board = new int[R][C];
            }
            if (index-1 < R){
              String boardLine = s.nextLine();
              for (int i = 0; i < boardLine.length(); i+=2){
                board[index-1][i/2] = Integer.parseInt(boardLine.substring(i,i+1));
              }
            }
            index++;
          }

          //testing
          System.out.println(print(values));
          System.out.println(print(board));
        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        return -1; //dummy value
    }

    public static int silver(String filename){
        //Combinatorics with ability to repeat points lol
        //recursive backtracking is not a good idea here - takes too long
        //T is just the total # of seconds to reach the destination - each second, cow moves a square
        try{
          File file = new File(filename);
          Scanner s = new Scanner(file);
          while (s.hasNextLine()){

          }


        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        return -1; //dummy value
    }

    public static String print(int[] ary){
      String ans = "[";
      for (int i = 0; i < ary.length; i++){
        ans+= ary[i];
        if (i != ary.length - 1){
          ans+= ",";
        }
      }
      return ans;
    }

    public static String print(int[][] ary){
      String ans = "[\n";
      for (int r = 0; r < ary.length; r++){
        ans+= " [";
        for (int c = 0; c < ary[r].length; c++){
          ans+= ary[r][c];
          if (c != ary[r].length-1){
            ans+=",";
          }
        }
        if (r != ary.length - 1){
          ans+= "]\n";
        }
      }
      return ans;
    }
}
