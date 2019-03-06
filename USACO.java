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
    }

    public static int silver(String filename){
        //Combinatorics with ability to repeat points lol
        //recursive backtracking is not a good idea here - takes too long
        //T is just the total # of seconds to reach the destination - each second, cow moves a square
    }
}