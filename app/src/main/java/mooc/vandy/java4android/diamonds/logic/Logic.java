package mooc.vandy.java4android.diamonds.logic;

import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */

    // main code
    public void process(int size) {
        int diamond_height = (size * 2) + 1;
        int diamond_width = (size * 2) + 2;
        int level_count = -(size+1);

        for(int i=1;i<=diamond_height;i++){
            level_count++;
            for (int j=1;j<=diamond_width;j++) {
                if((i == 1 || i == diamond_height) && (j == 1 || j == diamond_width))
                    mOut.print("+");
                else if((i == 1 || i == diamond_height) && !(j == 1 || j == diamond_width))
                    mOut.print("-");
                else if(!(i == 1 || i == diamond_height) && (j == 1 || j == diamond_width))
                    mOut.print("|");
                else {
                    drawingASCIIDiamond(size, i, j, level_count);    // helper method is called here
                }
            }
            mOut.print("\n");
        }

    }

    // helper method
    public void drawingASCIIDiamond(int size, int i, int j, int level_count){
        int diamondRowWidth;
        if (level_count <= 0){
            diamondRowWidth = i*2-2;
        } else {
            diamondRowWidth = (i-level_count*2)*2-2;
        }
        int diamondCenter = size + 1;
        int diamondLeftArea = diamondCenter - (diamondRowWidth/2-1);
        int diamondRightArea = diamondCenter + (diamondRowWidth/2);
        int diamondTopArea = 1;
        int diamondBottomArea = size * 2 + 1;

        if (j >= diamondLeftArea && j <= diamondRightArea) {
            if (j == diamondLeftArea || j == diamondRightArea) {
                if (i < diamondCenter && i > diamondTopArea) {
                    if (j == diamondLeftArea) {
                        mOut.print("/");
                    } else {
                        mOut.print("\\");
                    }
                } else if (i == diamondCenter) {
                    if (j == diamondLeftArea) {
                        mOut.print("<");
                    } else {
                        mOut.print(">");
                    }
                } else if (i > diamondCenter && i < diamondBottomArea) {
                    if (j == diamondLeftArea) {
                        mOut.print("\\");
                    } else {
                        mOut.print("/");
                    }
                }
            } else {
                if (i % 2 == 0) {
                    mOut.print("=");
                } else {
                    mOut.print("-");
                }
            }
        } else {
            mOut.print(" ");
        }
    }

    
}
