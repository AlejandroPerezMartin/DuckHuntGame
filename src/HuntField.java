
public class HuntField {

    private final int rows;
    private final int columns;
    private final FieldItem board[][];

    public HuntField(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new FieldItem[rows][columns];
        for (FieldItem[] fieldItems : board) {
            for (FieldItem fieldItem : fieldItems) {
                fieldItem = null;
            }
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
        if (board[position.getX()][position.getY()] != null) {
            return board[position.getX()][position.getY()].getType();
        }
        return ' ';
    }

    public boolean moveItem(Position oldPosition, Position newPosition) {
        return false;
    }

    public int getNumberOfItems(char itemType) {
        int count = 0;

        for (FieldItem[] fieldItems : board) {
            for (FieldItem fieldItem : fieldItems) {
                if (fieldItem.getType() == itemType) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public String toString() {
        String boardString = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == null) {
                    boardString += " ";
                } else {
                    boardString += board[i][j].getType();
                }
            }
            boardString += "\n";
        }

        return boardString;
    }
}
