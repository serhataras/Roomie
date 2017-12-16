package com.threek.roomie.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.threek.roomie.R;

import org.w3c.dom.Text;

import src.Enums.OptionType;
import src.FoodItem;
import src.Game;
import src.QuizQuestion;
import src.School;

/**
 * Created by serhat on 13.12.2017.
 */

public class SchoolActivity extends AppCompatActivity{

    private Game game;
    private TextView question;
    private Button ans1,ans2,ans3,ans4;
    private String answer;
    private QuizQuestion quizQuestion;
    private String[] answerOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        //Singleton Attributes
        game = Game.getInstance();

        quizQuestion = game.sendQuizQuestion();
        answerOptions = quizQuestion.getOptions();

        //View Binding for Android
        question = (TextView) findViewById(R.id.question_TextView);

        ans1 = (Button) findViewById(R.id.answerButton1);
        ans2 = (Button) findViewById(R.id.answerButton2);
        ans3 = (Button) findViewById(R.id.answerButton3);
        ans4 = (Button) findViewById(R.id.answerButton4);

        //TODO QUESTION AND ANSWERWS Text's Update
        question.setText(quizQuestion.getQuestion());

        ans1.setText(answerOptions[0]);
        ans2.setText(answerOptions[1]);
        ans3.setText(answerOptions[2]);
        ans4.setText(answerOptions[3]);

        //Listeners for each button
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer= ans1.getText().toString();
                changeGameInstance(answer);
                finish();
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer= ans2.getText().toString();
                changeGameInstance(answer);
                finish();
            }
        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer= ans3.getText().toString();
                changeGameInstance(answer);
                finish();
            }
        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer= ans4.getText().toString();
                changeGameInstance(answer);
                finish();
            }
        });
    }

    public void changeGameInstance(String answer){
        ((School) game.getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION)).setSelectedAnswer(answer);
        ((School) game.getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION)).updateChallengeSuccess();
        Log.e("CORR", ((School)game.getGameEnvironment().getOutdoorEnvironment(OptionType.SCHOOL_OPTION)).getSelectedAnswer());
        Log.e("ANS", answer);
    }
}


