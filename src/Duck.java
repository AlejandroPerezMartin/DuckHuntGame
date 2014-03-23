
import java.util.Random;

public class Duck extends Thread implements FieldItem {

    private final int hunted;
    private final boolean dead;
    private Position position;
    private final HuntField huntField;

    public Duck(HuntField huntField) {
        this.hunted = 0;
        this.dead = true;
        this.huntField = huntField;
        do {
            this.position = getRandomPosition();
        } while (huntField.setItem(this, position) == false);
    }

    private Position getRandomPosition() {
        Random random = new Random();
        return new Position(random.nextInt(huntField.getXLength()), random.nextInt(huntField.getYLength()));
    }

    public boolean isDead() {
        return dead;
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
