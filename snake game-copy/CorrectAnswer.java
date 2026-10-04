public class CorrectAnswer extends Answer {
    public CorrectAnswer(int value) {
        super(value);
    }

    @Override
    public void collected() {
        gameWorld().answerChosen(true);
    }
}