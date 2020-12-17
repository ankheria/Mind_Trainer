package com.example.mindtrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView goText;
    ConstraintLayout gameView;
    TextView questionText;
    TextView resultText;
    TextView scoreText;
    TextView timerText;

    Button button00;
    Button button01;
    Button button02;
    Button button03;

    int ansPosition;
    int no_of_question;
    int total_score;
    Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=(Button)findViewById(R.id.gobutton);
        goText=(TextView)findViewById(R.id.goTextView);
        goButton.setVisibility(View.VISIBLE);
        goText.setVisibility(View.VISIBLE);
        gameView=(ConstraintLayout)findViewById(R.id.mainGameView);
        gameView.setVisibility(View.INVISIBLE);
        questionText=(TextView)findViewById(R.id.questiontextview);
        button00=(Button)findViewById(R.id.button0);
        button01=(Button)findViewById(R.id.button1);
        button02=(Button)findViewById(R.id.button2);
        button03=(Button)findViewById(R.id.button3);
        resultText=(TextView)findViewById(R.id.resultTextView);
        scoreText=(TextView)findViewById(R.id.scoreTextView);
        timerText=(TextView)findViewById(R.id.timertextview);

        no_of_question=0;
        total_score=0;




    }

    public void startGame(View view){
        goButton.setVisibility(View.INVISIBLE);
        goText.setVisibility(View.INVISIBLE);
        gameView.setVisibility(View.VISIBLE);
        flag=true;
        timerFunction();
        playGame();

    }

    public void clickAnswer(View view){
        if(flag) {
            Button answerButton = (Button) view;
            int tag = Integer.parseInt(answerButton.getTag().toString());
            no_of_question++;
            if (tag == ansPosition) {
                resultText.setText("Correct :)");
                total_score++;
            } else {
                resultText.setText("Wrong (:");
            }
            scoreText.setText(total_score + "/" + no_of_question);

            playGame();
        }
    }

    public void playGame(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        questionText.setText(a+" + "+b);
        ansPosition=rand.nextInt(4);
        int bText[]=new int[4];
        int ans=a+b;
        for(int i=0;i<4;i++){
            do{
                bText[i]=rand.nextInt(41);
            }while (bText[i]==ans);
        }
        bText[ansPosition]=ans;

        button00.setText(Integer.toString(bText[0]));
        button01.setText(Integer.toString(bText[1]));
        button02.setText(Integer.toString(bText[2]));
        button03.setText(Integer.toString(bText[3]));

    }

    public void timerFunction(){

        new CountDownTimer(20000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft= millisUntilFinished/1000;
                timerText.setText(timeLeft+"s");
            }

            @Override
            public void onFinish() {
                flag=false;
                timerText.setText("0s");
                resultText.setText("Time Over");
            }
        }.start();
    }
}