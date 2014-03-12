
public class Duck implements FieldItem {

    public Duck(HuntField huntField) {
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
        return 'D';
    }
}