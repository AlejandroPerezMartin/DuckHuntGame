
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

    public synchronized boolean setItem(FieldItem fieldItem, Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] == null) {
            board[position.getX()][position.getY()] = fieldItem;
            return true;
        }

        return false;
    }

    public synchronized boolean shot(Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] != null) {
            return board[position.getX()][position.getY()].fired();
        }
        return false;
    }

    public synchronized boolean removeItem(FieldItem fieldItem, Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] == fieldItem) {
            board[position.getX()][position.getY()] = null;
            return true;
        }
        return false;
    }

    public char getItemType(Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] != null) {
            return board[position.getX()][position.getY()].getType();
        }
        return ' ';
    }

    public synchronized boolean moveItem(FieldItem fieldItem, Position oldPosition, Position newPosition) {

        if (!checkLimits(newPosition) || !checkLimits(oldPosition) || board[oldPosition.getX()][oldPosition.getY()] != fieldItem) {
            return false;
        }

        while (board[newPosition.getX()][newPosition.getY()] != null) {
            try {
                wait(1000);
            }
            catch (InterruptedException exc) {
            }
        }
        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[newPosition.getX()][newPosition.getY()] = fieldItem;

        notify();
        return true;
    }

    public int getNumberOfItems(char itemType) {
        int count = 0;

        for (FieldItem[] fieldItems : board) {
            for (FieldItem fieldItem : fieldItems) {
                if (fieldItem != null && fieldItem.getType() == itemType) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean checkLimits(Position position) {
        return (position.getX() >= 0 && position.getX() < rows && position.getY() >= 0 && position.getY() < columns);
    }

    @Override
    public String toString() {
        String boardString = "";

        for (FieldItem[] fieldItems : board) {
            for (FieldItem fieldItem : fieldItems) {
                if (fieldItem == null) {
                    boardString += " ";
                } else {
                    boardString += fieldItem.getType();
                }
            }
            boardString += "\n";
        }
        return boardString;
    }
}
