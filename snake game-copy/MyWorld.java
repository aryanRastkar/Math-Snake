import greenfoot.*;

public class MyWorld extends World {
    private int score = 0;
    private int lives = 3;
    private int correctAnswer;
    private int questionsAnswered = 0;
    private int pauseTimer = 0;
    private boolean gameOver = false;

    private Snake snake;

    public MyWorld() {
        super(600, 400, 1);
        setBackground("space1.jpg");

        snake = new Snake();
        addObject(snake, 300, 200);

        updateDisplay();
        newQuestion();
    }

    public void act() {
        if (gameOver) {
            return;
        }

        if (pauseTimer > 0) {
            pauseTimer--;

            if (pauseTimer == 0) {
                showText("", 300, 200);
                newQuestion();
            }
        }
    }

    public boolean canMove() {
        return !gameOver && pauseTimer == 0;
    }

    private void updateDisplay() {
        showText("Score: " + score, 65, 20);
        showText("Lives: " + lives, 530, 20);
        showText("WASD: move | Eat the correct answer", 300, 380);
    }

    private void newQuestion() {
        removeObjects(getObjects(Answer.class));

        int a;
        int b;
        String question;

        if (score < 5) {
            a = MathHelper.randomNumber(10) + 1;
            b = MathHelper.randomNumber(10) + 1;

            if (MathHelper.randomNumber(2) == 0) {
                correctAnswer = a + b;
                question = a + " + " + b + " = ?";
            }
            else {
                int larger = Math.max(a, b);
                int smaller = Math.min(a, b);
                correctAnswer = larger - smaller;
                question = larger + " - " + smaller + " = ?";
            }
        }
        else if (score < 10) {
            a = MathHelper.randomNumber(10) + 1;
            b = MathHelper.randomNumber(10) + 1;
            correctAnswer = a * b;
            question = a + " x " + b + " = ?";
        }
        else {
            b = MathHelper.randomNumber(10) + 1;
            correctAnswer = MathHelper.randomNumber(10) + 1;
            a = b * correctAnswer;
            question = a + " / " + b + " = ?";
        }

        showText(question, 300, 55);

        int wrong1 = MathHelper.wrongAnswer(correctAnswer);
        int wrong2;

        do {
            wrong2 = MathHelper.wrongAnswer(correctAnswer);
        } while (wrong2 == wrong1);

        placeAnswer(new CorrectAnswer(correctAnswer));
        placeAnswer(new WrongAnswer(wrong1));
        placeAnswer(new WrongAnswer(wrong2));
    }

    private void placeAnswer(Answer answer) {
        // Use separate positions away from the snake.
        int start = MathHelper.randomNumber(15);

        for (int i = 0; i < 15; i++) {
            int position = (start + i) % 15;
            int x = 80 + (position % 5) * 110;
            int y = 120 + (position / 5) * 100;

            int dx = x - snake.getX();
            int dy = y - snake.getY();

            if (dx * dx + dy * dy < 90 * 90) {
                continue;
            }

            if (getObjectsAt(x, y, Answer.class).isEmpty()) {
                addObject(answer, x, y);
                return;
            }
        }
    }

    public void answerChosen(boolean correct) {
        if (!canMove()) {
            return;
        }

        questionsAnswered++;
        removeObjects(getObjects(Answer.class));

        if (correct) {
            score++;
            snake.grow();
            showText("Correct! +1 point", 300, 200);
        }
        else {
            lives--;
            showText(
                "The correct answer was " + correctAnswer,
                300, 200
            );
        }

        updateDisplay();

        if (lives == 0) {
            endGame();
        }
        else {
            // Pause movement so the player can read the feedback.
            pauseTimer = 90;
        }
    }

    public void endGame() {
        if (gameOver) {
            return;
        }

        gameOver = true;

        showText("GAME OVER — Score: " + score, 300, 160);
        showText(
            "Correct: " + score + " out of " + questionsAnswered,
            300, 240
        );
        showText("Click Reset to try again", 300, 280);

        Greenfoot.stop();
    }
}