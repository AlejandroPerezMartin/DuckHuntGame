
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hunter extends Thread implements FieldItem {

    private int hunted;
    private boolean alive;
    private Position position;
    private final HuntField huntField;

    public Hunter(HuntField huntField) {
        this.hunted = 0;
        this.huntField = huntField;
        this.alive = true;
        do {
            this.position = getRandomPosition();
        } while (huntField.setItem(this, position) == false);
    }

    private Position getRandomPosition() {
        Random random = new Random();
        return new Position(random.nextInt(huntField.getXLength()), random.nextInt(huntField.getYLength()));
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
    public char getType() {
        return 'H';
    }

    public int hunted() {
        return hunted;
    }

    @Override
    public void run() {
        int shotDirection = 0;
        Position newShotPosition = null;
        Random random = new Random();

        while (alive && huntField.getNumberOfItems('D') > 0) {
            
            try {
                Thread.sleep(random.nextInt(101));
            } catch (InterruptedException ex) {
                Logger.getLogger(Duck.class.getName()).log(Level.SEVERE, null, ex);
            }

            switch (shotDirection) {
                case 0:
                    // Shoot up
                    newShotPosition = new Position(position.getX(), position.getY() - 1);
                    break;
                case 1:
                    // Shoot right
                    newShotPosition = new Position(position.getX() + 1, position.getY());
                    break;
                case 2:
                    // Shoot down
                    newShotPosition = new Position(position.getX(), position.getY() + 1);
                    break;
                case 3:
                    // Shoot left
                    newShotPosition = new Position(position.getX() - 1, position.getY());
                    break;
            }

            shotDirection++;
            if (shotDirection == 4) {
                shotDirection = 0;
            }

            huntField.shot(newShotPosition);

            if (huntField.getItemType(newShotPosition) == 'D') {
                hunted++;
                if (huntField.moveItem(this, position, newShotPosition)) {
                    position = newShotPosition;
                }
            }
            
        }
    }

}
