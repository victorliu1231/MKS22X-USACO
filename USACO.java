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
          ArrayList<ArrayList<Integer>> board = new ArrayList<>(); //just to initialize
          String R = "";
          int C = 0;
          ArrayList<Integer> values = new ArrayList<>();
          ArrayList<ArrayList<Integer>> instructions = new ArrayList<>();
          File file = new File(filename);
          Scanner s = new Scanner(file);
          while (s.hasNextLine()){
            if (index == 0){
              String firstLine = s.nextLine();
              String storer = "";
              int i = 0;
              while (i != firstLine.length()){
                if (firstLine.charAt(i) == ' '){
                  values.add(Integer.parseInt(storer));
                  storer = "";
                } else {
                  storer+= firstLine.charAt(i);
                }
                if (i == firstLine.length() - 1){
                  values.add(Integer.parseInt(storer));
                }
                i++;
              }
              R = ""+values.get(0);
              C = values.get(1);
              int E = values.get(2);
              int N = values.get(3);
            }
            else if (index-1 < Integer.parseInt(R)){
              String boardLine = s.nextLine();
              board.add(new ArrayList<Integer>());
              String storer = "";
              int i = 0;
              while (i != boardLine.length()){
                if (boardLine.charAt(i) == ' '){
                  board.get(index-1).add(Integer.parseInt(storer));
                  storer = "";
                } else {
                  storer+= boardLine.charAt(i);
                }
                if (i == boardLine.length() - 1){
                  board.get(index-1).add(Integer.parseInt(storer));
                }
                i++;
              }
            }
            else {
              String instructLine = s.nextLine();
              instructions.add(new ArrayList<Integer>());
              String storer = "";
              int i = 0;
              while (i != instructLine.length()){
                if (instructLine.charAt(i) == ' '){
                  instructions.get(index-Integer.parseInt(R)-1).add(Integer.parseInt(storer));
                  storer = "";
                } else {
                  storer+= instructLine.charAt(i);
                }
                if (i == instructLine.length() - 1){
                  instructions.get(index-Integer.parseInt(R)-1).add(Integer.parseInt(storer));
                }
                i++;
              }
            }
            index++;
          }
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
          String[][] grid = new String[][]{};
          while (s.hasNext()){
            if (index == 0){
              N = s.next();
            }
            if (index == 1){
              M = s.next();
            }
            if (index == 2){
              T = s.next();
              grid = new String[N][M];
            }
            if (index > 2 && index <= 2 + N){
              grid[index-2] = s.next(); //index-2 is to calibrate index in regards to grid index
            }
            if (index == 2 + N + 1){
              
            }
            index++;
            System.out.println(s.next());
          }

          //System.out.println(values);
          //System.out.println(board);
          //System.out.println(instructions);
        } catch (FileNotFoundException e){
          System.out.println("Use a valid file!");
        }
        //reading in the file is an absolute mess...
        return -1; //dummy value
    }

}
