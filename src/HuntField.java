
public class HuntField {

    private int[][] board;
    private final int rows;
    private final int columns;

    public HuntField(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new int[rows][columns];
    }

    public int getXLength() {
        return rows;
    }

    public int getYLength() {
        return columns;
    }

    public boolean setItem(HuntField huntField, Position position) {
        return false;
    }

    public boolean shot(Position position) {
        return false;
    }

    public boolean removeItem(Position position) {
        return false;
    }

    public char getItemType(Position position) {
        return 'X';
    }

    public boolean moveItem(Position oldPosition, Position newPosition) {
        return false;
    }

    public int getNumberOfItems(char itemType) {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}