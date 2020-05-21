// Recursive division method

package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final TETile WALL = Tileset.WALL;
    public static final TETile FLOOR = Tileset.FLOOR;
    public static final TETile LOCKED_DOOR = Tileset.LOCKED_DOOR;
    public static final TETile NOTHING = Tileset.NOTHING;

    private TETile[][] world;
    private LinkedList<Position> list;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        initWorld();
        fillWorld();
        drawWorld();
    }

    private void drawWorld() {
        ter.renderFrame(world);
    }

    private void initWorld() {
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if(x == 0 || y == 0 || x == (WIDTH - 1) || y == (HEIGHT - 1)) {
                    world[x][y] = WALL;
                } else {
                    world[x][y] = FLOOR;
                }
            }
        }
    }

    private void fillWorld() {
        divide(new Position(1, 1), new Position(WIDTH - 2, HEIGHT - 2));
    }

    public static int getRandom(int min, int max) {
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

    private void divide(Position left, Position right) {
        if(right.y - left.y < 1 || right.x - left.x < 1) {
            return;
        }

        int y = getRandom(left.y+1, right.y-1);
        int x = getRandom(left.x+1, right.x-1);

        // generate wall
        for (int i = left.x; i <= right.x; i++) {
           world[i][y] = WALL;
        }
        for (int i = left.y; i <= right.y; i++) {
            world[x][i] = WALL;
        }
        // generate hole in wall
        int holeIndex =  getRandom(1, 4);
        if(holeIndex != 1) {
            world[x][getRandom(left.y+1, y-1)] = FLOOR;
        }
        if(holeIndex != 2) {
            world[x][getRandom(y+1, right.y-1)] = FLOOR;
        }
        if(holeIndex != 3) {
            world[getRandom(left.x+1, x-1)][y] = FLOOR;
        }
        if(holeIndex != 4) {
            world[getRandom(x+1, right.x-1)][y] = FLOOR;
        }

        divide(new Position(left.x, left.y), new Position(x-1, y-1));
        divide(new Position(x+1, y+1), new Position(right.x, right.y));

        divide(new Position(left.x, y+1), new Position(x-1, right.y));
        divide(new Position(x+1, left.y), new Position(right.x, y-1));
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // process input e.g. "N3412S"
        long seed = processInput(input);
        return null;
    }

    private long processInput(String input) {
         String s = input.substring(1, input.length() - 1);
         return Long.getLong(s);
    }
}
