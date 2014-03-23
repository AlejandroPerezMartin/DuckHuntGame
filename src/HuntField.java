
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
        if (!checkLimits(position) || board[position.getX()][position.getY()] != null) {
            return false;
        }
        board[position.getX()][position.getY()] = fieldItem;
        return true;
    }

    public boolean checkLimits(Position position) {
        return (position.getX() >= 0 && position.getX() < rows && position.getY() >= 0 && position.getY() < columns);
    }

    public boolean shot(Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] != null) {
            return board[position.getX()][position.getY()].fired();
        }
        return false;
    }

    public boolean removeItem(Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] != null) {
            board[position.getX()][position.getY()] = null;
            return true;
        }
        return false;
    }

    public char getItemType(Position position) {
        if (board[position.getX()][position.getY()] != null) {
            return board[position.getX()][position.getY()].getType();
        }
        return ' ';
    }

    public boolean moveItem(FieldItem fieldItem, Position oldPosition, Position newPosition) {
        if (!checkLimits(newPosition) || !checkLimits(oldPosition) || board[newPosition.getX()][newPosition.getY()] != null) {
            return false;
        }

        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[newPosition.getX()][newPosition.getY()] = fieldItem;
        return true;
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
