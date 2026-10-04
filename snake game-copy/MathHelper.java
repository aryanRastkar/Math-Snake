import greenfoot.*;

public class MathHelper {
    public static int randomNumber(int max) {
        return Greenfoot.getRandomNumber(max);
    }

    public static int wrongAnswer(int correctAnswer) {
        int answer;

        do {
            answer = correctAnswer + randomNumber(11) - 5;
        } while (answer < 0 || answer == correctAnswer);

        return answer;
    }
}