import greenfoot.*;

public abstract class Answer extends Actor {
    private int value;

    public Answer(int value) {
        this.value = value;

        GreenfootImage image = new GreenfootImage(64, 42);

        image.setColor(Color.WHITE);
        image.fillRect(0, 0, 64, 42);

        image.setColor(Color.BLACK);
        image.drawRect(0, 0, 63, 41);

        GreenfootImage label = new GreenfootImage(
            Integer.toString(value),
            26,
            Color.BLACK,
            Color.WHITE
        );

        image.drawImage(
            label,
            (64 - label.getWidth()) / 2,
            (42 - label.getHeight()) / 2
        );

        setImage(image);
    }

    public int getValue() {
        return value;
    }

    protected MyWorld gameWorld() {
        return (MyWorld) getWorld();
    }

    public abstract void collected();
}