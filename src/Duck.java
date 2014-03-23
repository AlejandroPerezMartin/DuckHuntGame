
import java.util.Random;

public class Duck implements FieldItem {

    private final int hunted;
    private final boolean alive;
    private Position position;
    private final HuntField huntField;

    public Duck(HuntField huntField) {
        this.hunted = 0;
        this.alive = true;
        this.huntField = huntField;
        while (this.huntField.setItem(this, position) == false) {
            this.position = getRandomPosition();
        }
    }

    private Position getRandomPosition() {
        Random random = new Random();
        return new Position(random.nextInt(huntField.getXLength()), random.nextInt(huntField.getYLength()));
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean fired() {
        return true;
    }

    @Override
    public char getType() {
        return 'D';
    }

    public int hunted() {
        return hunted;
    }
}
