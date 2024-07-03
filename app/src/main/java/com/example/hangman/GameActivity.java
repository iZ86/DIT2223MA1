package com.example.hangman;



import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/** This class represents the game activity. */
public class GameActivity extends AppCompatActivity {

    /** The logic class of the game. */
    Model model;
    /** Resources used to find the components in the xml. */
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        res = getResources();
        model = new Model();
        model.newGame();
        updateWordsInputtedTextView();

    }

    /** Updates the wordsInputted text view. */
    private void updateWordsInputtedTextView() {
        TextView wordsInputtedTextView = (TextView) findViewById(R.id.wordsInputtedText);
        wordsInputtedTextView.setText(model.getWordInputted());

    }

    /** Updates the button of the character inputted,
     * checkmark appears if correct character.
     * Otherwise, a wrong mark will appear.
     */
    private void updateCharacterButtonView(char character) {

        int characterStatus = model.checkCharacterCorrect(character);
        ImageView imageView = null;
        if (characterStatus == 1) {
            int id = res.getIdentifier(character + "Checkmark", "id", getPackageName());
            imageView = (ImageView) findViewById(id);
        } else if (model.checkCharacterCorrect(character) == -1){
            int id = res.getIdentifier(character + "Wrongmark", "id", getPackageName());
            imageView = (ImageView) findViewById(id);
        }
        imageView.setVisibility(View.VISIBLE);

    }

    /** Updates the hang man view based on the number of mistakes there are. */
    public void updateMistakeView(int numberOfMistakes) {
        String nthID = null;
        if (numberOfMistakes == 1) {
            nthID = "first";
        } else if (numberOfMistakes == 2) {
            nthID = "second";
        } else if (numberOfMistakes == 3) {
            nthID = "third";
        } else if (numberOfMistakes == 4) {
            nthID = "fourth";
        } else if (numberOfMistakes == 5) {
            nthID = "fifth";
        } else if (numberOfMistakes == 6) {
            nthID = "sixth";
        } else if (numberOfMistakes == 7) {
            nthID = "seventh";
        }

        if (numberOfMistakes == 0) {

            ImageView firstErrorImage = (ImageView) findViewById(R.id.firstErrorImage);
            ImageView secondErrorImage = (ImageView) findViewById(R.id.secondErrorImage);
            ImageView thirdErrorImage = (ImageView) findViewById(R.id.thirdErrorImage);
            ImageView fourthErrorImage = (ImageView) findViewById(R.id.fourthErrorImage);
            ImageView fifthErrorImage = (ImageView) findViewById(R.id.fifthErrorImage);
            ImageView sixthErrorImage = (ImageView) findViewById(R.id.sixthErrorImage);
            ImageView seventhErrorImage = (ImageView) findViewById(R.id.seventhErrorImage);

            firstErrorImage.setVisibility(View.INVISIBLE);
            secondErrorImage.setVisibility(View.INVISIBLE);
            thirdErrorImage.setVisibility(View.INVISIBLE);
            fourthErrorImage.setVisibility(View.INVISIBLE);
            fifthErrorImage.setVisibility(View.INVISIBLE);
            sixthErrorImage.setVisibility(View.INVISIBLE);
            seventhErrorImage.setVisibility(View.INVISIBLE);

        } else if (numberOfMistakes > 0 && numberOfMistakes <= 7) {
            int id = res.getIdentifier(nthID + "ErrorImage", "id", getPackageName());
            ImageView imageView = (ImageView) findViewById(id);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    /** User inputs a character, this method will called. */
    public void userInput(View view) {
        if (!model.isPauseGame()) {
            String characterInputtedString = view.getTag().toString().toLowerCase();
            char characterInputted = characterInputtedString.charAt(0);
            model.userInput(characterInputted);
            updateWordsInputtedTextView();
            updateCharacterButtonView(characterInputted);


            int numberOfMistakes = model.getNumberOfMistakes();
            updateMistakeView(numberOfMistakes);
        }

        if (model.isNewRound()) {
            TextView correctWord = (TextView) findViewById(R.id.correctWordText);
            correctWord.setText(model.getCurrWord());
            displayWinMenu(true);
            disablePauseButton();
            disableCharacterButtons();
        } else if (model.isGameOver()) {
            TextView gameOverWord = (TextView) findViewById(R.id.gameOverWordText);
            gameOverWord.setText(model.getCurrWord());
            disableCharacterButtons();
            disablePauseButton();
            displayGameOverMenu(true);
        }
    }

    /** Game goes to the next round. */
    public void nextRound(View view) {
        model.nextRound();
        updateMistakeView(0);
        updateWordsInputtedTextView();
        updateRound();
        resetButtonAnswersView();
        enablePauseButton();
        enableCharacterButtons();
        displayWinMenu(false);
    }

    /** Restarts the game. */
    public void restartGame(View view) {
        model.newGame();
        enableCharacterButtons();
        enablePauseButton();
        updateRound();
        updateMistakeView(0);
        resetButtonAnswersView();
        updateWordsInputtedTextView();
        displayGameOverMenu(false);
        displayPauseMenu(false);
    }

    /** Return to main menu. */
    public void returnMainMenu(View view) {
        finish();
    }

    /** Reset character button view. */
    private void resetButtonAnswersView() {
        for (int i = 97; i <= 122; i++) {
            int checkmarkId = res.getIdentifier((char) (i) + "Checkmark", "id", getPackageName());
            int wrongmarkId = res.getIdentifier((char) (i) + "Wrongmark", "id", getPackageName());
            ImageView checkmarkImage = (ImageView) findViewById(checkmarkId);
            ImageView wrongmarkImage = (ImageView) findViewById(wrongmarkId);

            checkmarkImage.setVisibility(View.INVISIBLE);
            wrongmarkImage.setVisibility(View.INVISIBLE);
        }


    }

    /** Method is called when the pause button is pressed. */
    public void pauseGameButtonPressed(View view) {
        if (model.isPauseGame()) {
            resumeGame();
        } else {
            pauseGame();
        }

    }

    /** Method is called when the resume button is pressed. */
    public void resumeGameButtonPressed(View view) {
        resumeGame();
    }

    /** Pauses the game. */
    private void pauseGame() {
        model.pauseGame();
        displayPauseMenu(true);

        disableCharacterButtons();
    }

    /** Resumes game. */
    private void resumeGame() {
        model.resumeGame();
        displayPauseMenu(false);

        enableCharacterButtons();
    }
    /** Displays the pause menu, if display is true.
     * Otherwise, does not display.
     */
    private void displayPauseMenu(boolean display) {
        RelativeLayout pauseMenu = (RelativeLayout) findViewById(R.id.pauseMenu);
        if (display) {
            pauseMenu.setVisibility(View.VISIBLE);
        } else {

            pauseMenu.setVisibility(View.INVISIBLE);
        }

    }

    /** Displays the game over menu, if show is true.
     * Otherwise, does not display.
     */
    private void displayGameOverMenu(boolean display) {
        RelativeLayout gameOverMenu = findViewById(R.id.gameOverMenu);
        if (display) {
            gameOverMenu.setVisibility(View.VISIBLE);
        } else {
            gameOverMenu.setVisibility(View.INVISIBLE);
        }

    }

    /** Displays correct guess menu, if display is true.
     * Otherwise, does not display.
     */
    private void displayWinMenu(boolean display) {
        RelativeLayout winMenu = findViewById(R.id.winMenu);
        if (display) {
            winMenu.setVisibility(View.VISIBLE);
        } else {
            winMenu.setVisibility(View.INVISIBLE);
        }
    }

    /** Updates the round text view. */
    private void updateRound() {
        TextView roundText = (TextView) findViewById(R.id.roundText);
        roundText.setText("Round " + model.getRound());
    }

    /** Disables all character buttons. */
    private void disableCharacterButtons() {
        for (int i = 97; i <= 122; i++) {
            int id = res.getIdentifier((char) i + "Button", "id", getPackageName());
            Button alphabetButton = (Button) findViewById(id);
            alphabetButton.setEnabled(false);
        }
    }

    /** Enables all character buttons. */
    private void enableCharacterButtons() {
        for (int i = 97; i <= 122; i++) {
            int id = res.getIdentifier((char) i + "Button", "id", getPackageName());
            Button alphabetButton = (Button) findViewById(id);
            alphabetButton.setEnabled(true);
        }
    }

    /** Disables pause button. */
    private void disablePauseButton() {
        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setEnabled(false);
    }

    /** Enables pause button. */
    private void enablePauseButton() {
        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setEnabled(true);
    }
}
