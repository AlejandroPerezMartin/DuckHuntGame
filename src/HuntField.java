
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

            notifyAll();

            return true;
        }

        return false;
    }

    public synchronized boolean shot(Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] != null) {
            notifyAll();
            return board[position.getX()][position.getY()].fired();
        }
        return false;
    }

    public synchronized boolean removeItem(FieldItem fieldItem, Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] == fieldItem) {
            board[position.getX()][position.getY()] = null;

            notifyAll();

            return true;
        }
        return false;
    }

    public synchronized char getItemType(Position position) {
        if (checkLimits(position) && board[position.getX()][position.getY()] != null) {
            return board[position.getX()][position.getY()].getType();
        }
        return ' ';
    }

    public synchronized boolean moveItem(FieldItem fieldItem, Position oldPosition, Position newPosition) {

        if (!checkLimits(newPosition) || !checkLimits(oldPosition) || board[oldPosition.getX()][oldPosition.getY()] != fieldItem) {
            return false;
        }

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        while ((endTime - startTime <= 6000) && board[newPosition.getX()][newPosition.getY()] != null) {
            endTime = System.currentTimeMillis();

            try {
                wait(200);
            } catch (InterruptedException exc) {
            }
        }

        if (endTime - startTime > 6000) {
            return false;
        }

        board[oldPosition.getX()][oldPosition.getY()] = null;
        board[newPosition.getX()][newPosition.getY()] = fieldItem;

        notifyAll();

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
    public synchronized String toString() {
        String boardString = "";

        for (FieldItem[] fieldItems : board) {
            for (FieldItem fieldItem : fieldItems) {
                if (fieldItem == null) {
                    boardString += " ";
                }
                else {
                    boardString += fieldItem.getType();
                }
            }
            boardString += "\n";
        }

        return boardString;
    }
}
