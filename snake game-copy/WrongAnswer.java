public class WrongAnswer extends Answer {
    public WrongAnswer(int value) {
        super(value);
    }

    @Override
    public void collected() {
        gameWorld().answerChosen(false);
    }
}