public class HuntField {
    
    private int[][] board;
    private final int rows;
    private final int columns;
    
    public HuntField(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.board = new int[rows][columns];
    }
    
    public int getXLength(){
        return rows;
    }
    
    public int getYLength(){
        return columns;
    }
    
    public int getNumberOfItems(String char){
        for(int i = 0; i < rows; i++){
            for(int j = 0; i < columns; j++){
                if(board[i][j].equals(char)){
                    return board[i][j];
                }
            }
        }
        return " ";
    }
    
}