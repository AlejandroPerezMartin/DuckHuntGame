
import java.util.Random;

public class Duck extends Thread implements FieldItem {

    private boolean alive;
    private Position position;
    private final HuntField huntField;

    public Duck(HuntField huntField) {
        this.alive = true;
        this.huntField = huntField;

        // Do-while avoids accessing an invalid position
        do {
            this.position = getRandomPosition();
        } while (huntField.setItem(this, position) == false);
    }

    @Override
    public char getType() {
        return 'D';
    }

    @Override
    public boolean fired() {
        if (alive()) {
            alive = false;
            return true;
        }
        return false;
    }

    public boolean alive() {
        return alive;
    }

    private Position getRandomPosition() {
        Random random = new Random();
        return new Position(random.nextInt(huntField.getXLength()), random.nextInt(huntField.getYLength()));
    }

    @Override
    public void run() {
        int randomMove = 0;
        Position newPosition = null;
        Random random = new Random();

        while (alive) {

            try {
                Thread.sleep(random.nextInt(301));
            } catch (InterruptedException exc) {
            }

            randomMove = random.nextInt(4);

            switch (randomMove) {
                case 0:
                    // Move up
                    newPosition = new Position(position.getX(), position.getY() - 1);
                    break;
                case 1:
                    // Move riht
                    newPosition = new Position(position.getX() + 1, position.getY());
                    break;
                case 2:
                    // Move down
                    newPosition = new Position(position.getX(), position.getY() + 1);
                    break;
                case 3:
                    // Move left
                    newPosition = new Position(position.getX() - 1, position.getY());
                    break;
            }

            // Duck moves if new surrounding position is empty
            if (huntField.moveItem(this, position, newPosition)) {
                position = newPosition;
            }
        }
        // Remove duck if fired
        huntField.removeItem(this, position);
    }
}
