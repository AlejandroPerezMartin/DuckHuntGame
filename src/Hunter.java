
public class Hunter implements FieldItem {

    private int hunted;

    public Hunter(HuntField huntField) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void start() {
    }

    @Override
    public boolean fired() {
        return true;
    }

    @Override
    public char getType() {
        return 'H';
    }

    public int hunted() {
        return hunted;
    }
}