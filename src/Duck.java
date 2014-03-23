
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duck extends Thread implements FieldItem {

    private boolean alive;
    private Position position;
    private final HuntField huntField;

    public Duck(HuntField huntField) {
        this.alive = true;
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
    public char getType() {
        return 'D';
    }

    public boolean alive() {
        return alive;
    }

    @Override
    public boolean fired() {
        if (alive()) {
            alive = false;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        Position newPosition = null;
        int randomMove = 0;
        Random random = new Random();

        while (alive) {
            try {
                Thread.sleep(random.nextInt(301));
            } catch (InterruptedException ex) {
                Logger.getLogger(Duck.class.getName()).log(Level.SEVERE, null, ex);
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

            if (huntField.moveItem(this, position, newPosition)) {
                position = newPosition;
            }
        }
    }

}
