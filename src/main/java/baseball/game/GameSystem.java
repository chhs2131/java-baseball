package baseball.game;

import baseball.domain.BaseballNumberBundle;
import baseball.domain.GameProgress;
import baseball.domain.GameResult;
import baseball.domain.GameState;

public class GameSystem {
    Computer computer;
    Player player;

    public GameSystem(Computer computer, Player player) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        this.computer = computer;
        this.player = player;
    }
    public void initialize() {
        computer.setRandomVictoryNumbers();
    }
    public GameResult playGame() {
        BaseballNumberBundle pickedNumbers = player.inputBaseballNumbers();
        return computer.checkAnswer(pickedNumbers);
    }
    public boolean receivedCorrectAnswer(GameResult result) {
        GameState state = result.getState();
        return state == GameState.CORRECT;
    }
    public String printResult(GameResult result) {
        String sentence = "";
        GameState state = result.getState();
        switch (state) {
            case NOTHING:
                sentence = "낫싱";
                break;
            case GOOD:
                sentence = makeStrikeBallSentence(result.getStrike(), result.getBall());
                break;
            case CORRECT:
                sentence = "3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료";
                break;
        }
        System.out.println(sentence);
        return sentence;
    }
    private String makeStrikeBallSentence(int strike, int ball) {
        String sentence = "";
        if(strike == 0) {
            sentence = ball + "볼";
        } else if(ball == 0) {
            sentence = strike + "스트라이크";
        } else {
            sentence = ball + "볼 " + strike + "스트라이크";
        }
        return sentence;
    }
    public boolean continueGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        GameProgress gameProgress = player.inputGameProgress();
        if(gameProgress == GameProgress.CONTINUE) {
            return true;
        } else if(gameProgress == GameProgress.STOP) {
            System.out.println("게임종료");
            return false;
        }
        return false;
    }
}
