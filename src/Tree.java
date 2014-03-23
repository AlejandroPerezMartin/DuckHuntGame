
import java.util.Random;

public class Tree implements FieldItem {

    private final HuntField huntField;
    private Position position;

    public Tree(HuntField huntField) {
        this.huntField = huntField;
        do {
            this.position = getRandomPosition();
        } while (huntField.setItem(this, position) == false);
    }

    private Position getRandomPosition() {
        Random random = new Random();
        return new Position(random.nextInt(huntField.getXLength()), random.nextInt(huntField.getYLength()));
    }

    @Override
    public boolean fired() {
        return false;
    }

    @Override
    public char getType() {
        return 'T';
    }
}
