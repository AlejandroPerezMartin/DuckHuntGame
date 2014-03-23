
public class HuntField {

    private final int rows;
    private final int columns;
    private final FieldItem board[][];

    public HuntField(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new FieldItem[rows][columns];
        for (FieldItem[] fieldItems : board) {
            fieldItems = null;
        }
    }

    public int getXLength() {
        return rows;
    }

    public int getYLength() {
        return columns;
    }

    public boolean setItem(FieldItem fieldItem, Position position) {
        if (checkLimits(position) || board[position.getX()][position.getY()] != null) {
            return false;
        }
        board[position.getX()][position.getY()] = fieldItem;
        return true;
    }

    public boolean checkLimits(Position position) {
        return (position.getX() >= 0 && position.getX() < getXLength() && position.getY() >= 0 && position.getY() < getYLength());
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
