import greenfoot.*;

public class Snake extends Actor {
    private int speed = 3;
    private int size = 24;

    public Snake() {
        drawSnake();
    }

    public void act() {
        MyWorld world = (MyWorld) getWorld();

        if (!world.canMove()) {
            return;
        }

        handleKeyPress();
        move(speed);

        if (isAtEdge()) {
            world.endGame();
            return;
        }

        Answer answer =
            (Answer) getOneIntersectingObject(Answer.class);

        if (answer != null) {
            answer.collected();
        }
    }

    private void handleKeyPress() {
        if (Greenfoot.isKeyDown("w")) {
            setRotation(270);
        }
        else if (Greenfoot.isKeyDown("a")) {
            setRotation(180);
        }
        else if (Greenfoot.isKeyDown("s")) {
            setRotation(90);
        }
        else if (Greenfoot.isKeyDown("d")) {
            setRotation(0);
        }
    }

    public void grow() {
        // Limit the size so answer choices stay easy to select.
        size = Math.min(size + 2, 40);
        drawSnake();
    }

    private void drawSnake() {
        GreenfootImage image = new GreenfootImage(size, size);

        image.setColor(Color.GREEN);
        image.fillOval(0, 0, size - 1, size - 1);

        image.setColor(Color.BLACK);
        image.fillOval(size - 9, 5, 4, 4);
        image.fillOval(size - 9, size - 9, 4, 4);

        setImage(image);
    }
}