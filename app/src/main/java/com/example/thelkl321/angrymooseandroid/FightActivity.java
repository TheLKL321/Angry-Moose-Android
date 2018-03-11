package com.example.thelkl321.angrymooseandroid;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thelkl321.angrymooseandroid.SurrenderDialogFragment.SurrenderDialogListener;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class FightActivity extends AppCompatActivity implements SurrenderDialogListener{

    private ProgressBar mooseHpBar, playerHpBar;
    TextView middleText;
    TextView eventLog;
    private static int mooseHp;
    private static int startingMooseHp;
    private static int playerHp;
    private static int startingPlayerHp;
    static int turnCounter, time;
    private static HashMap<String, Integer> counters = new HashMap<>();
    private static HashMap<String, Button> moveButtons = new HashMap<>();
    private static String mooseMove;
    static String lastEvent;

    private FragmentManager fm;
    EndgameFragment endgameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // Get difficulty and gamemode info
        Intent intent = getIntent();
        startingMooseHp = intent.getIntExtra(MainActivity.MOOSE_KEY, 0);
        startingPlayerHp = intent.getIntExtra(MainActivity.PLAYER_KEY, 0);
        time = intent.getIntExtra(MainActivity.TIME_KEY, 0);

        // Assign views
        mooseHpBar = findViewById(R.id.mooseHealthBar);
        mooseHpBar.setMax(startingMooseHp);
        playerHpBar = findViewById(R.id.playerHealthBar);
        playerHpBar.setMax(startingPlayerHp);
        middleText = findViewById(R.id.middleText);

        // Assign move buttons
        moveButtons.put("throw", (Button) findViewById(R.id.throwButton));
        moveButtons.put("dodge", (Button) findViewById(R.id.dodgeButton));
        moveButtons.put("leap", (Button) findViewById(R.id.leapButton));
        moveButtons.put("kick", (Button) findViewById(R.id.kickButton));
        moveButtons.put("attack", (Button) findViewById(R.id.attackButton));

        // Create scrollable log
        eventLog = findViewById(R.id.logText);
        eventLog.setMovementMethod(new ScrollingMovementMethod());

        // Assign the fragment and hide it
        fm = getSupportFragmentManager();
        endgameFragment = (EndgameFragment) fm.findFragmentById(R.id.endgameFragment);
        hideFragment(endgameFragment);

        startgame();
    }

    abstract void startgame();

    static void clearDisabilities(){
        Button button = moveButtons.get("throw");
        button.setText("Throw dirt ");
        button.setClickable(true);

        button = moveButtons.get("dodge");
        button.setText("Dodge ");
        button.setClickable(true);

        button = moveButtons.get("leap");
        button.setText("Leap ");
        button.setClickable(true);

        button = moveButtons.get("kick");
        button.setText("Kick ");
        button.setClickable(true);

        button = moveButtons.get("attack");
        button.setText("Attack ");
        button.setClickable(true);
    }

    void assignTurnCounter(){
        turnCounter = 1;
        middleText = findViewById(R.id.middleText);
        middleText.setText(String.valueOf(turnCounter));
    }

    void applyDifficulty(){
        mooseHp = startingMooseHp;
        playerHp = startingPlayerHp;
        mooseHpBar.setProgress(mooseHp);
        playerHpBar.setProgress(playerHp);
    }

    void restartMoveCounters(){
        for (String move :
                new String[] {"throw", "dodge", "leap", "kick", "attack"}) {
            counters.put(move, 0);
        }
    }

    abstract void endgame(String outcome);

    private void changeHealth (String target, int amount){
        if (Objects.equals(target, "moose")) {
            if (mooseHp + amount <= mooseHpBar.getMax()) {
                mooseHp += amount;
                mooseHpBar.setProgress(mooseHp);
            }
        }
        else {
            if (playerHp + amount <= playerHpBar.getMax()) {
                playerHp += amount;
                playerHpBar.setProgress(playerHp);
            }
        }
    }

    // TEMPORARY
    private static void disable (String move, int duration){
        counters.put(move, counters.get(move) + duration);
        Button button = moveButtons.get(move);

        for (int i = 0; i < duration; i++){
            String text = String.valueOf(button.getText());
            button.setText(text + "X");
        }
        button.setClickable(false);
    }

    // TEMPORARY
    private static void enable (String move){
        Button button = moveButtons.get(move);
        String text = String.valueOf(button.getText());
        String ttext = text.substring(0, text.length() - 1);
        button.setText(ttext);
        button.setClickable(true);
    }

    private void checkEndgame (){
        if (mooseHp <= 0 || playerHp <= 0) {
            if (mooseHp >= 0) endgame("loss");
            else if (playerHp >= 0) endgame("win");
            else endgame("tie");
        }
    }

    static void updateCounters(){
        for (String move : counters.keySet()) {
            int count = counters.get(move);
            if (count > 1) {
                counters.put(move, count - 1);
                Button button = moveButtons.get(move);
                String text = String.valueOf(button.getText());
                String ttext = text.substring(0, text.length() - 1);
                button.setText(ttext);
            } else if (count == 1){
                counters.put(move, 0);
                enable(move);
            }
        }
    }

    private static int random() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }

    void mooseTurn(){
        int number = random();
        if (number <= 17) {
            mooseMove = "lower head";
            logEvent("The moose lowers it's head, pointing its antlers towards you");
        }
        else if (number <= 34) {
            mooseMove = "roar";
            logEvent("A terrifying roar spreads through the forest");
        }
        else if (number <= 51) {
            mooseMove = "step back";
            logEvent("The animal takes a step back");
        }
        else if (number <= 68) {
            mooseMove = "eat";
            logEvent("The moose found some fresh berries");
        }
        else if (number <= 85){
            mooseMove = "pick up"; // lol "picks up his leg"
            logEvent("The beast raises his leg");
        }
        else {
            mooseMove = "turn around";
            logEvent("The moose turns around");
        }
    }

    private void finishTurn (String playerMove){

        updateTurns();
        int number = random();

        switch (mooseMove){
            case "lower head":
                switch (playerMove){
                    case "throw":
                        if (number <=  20){
                            logEvent("The beast is blinded and misses its charge");
                        } else if (number <= 50){
                            changeHealth("moose", -2);
                            logEvent("The moose is blinded and hits a tree");
                        } else {
                            changeHealth("player", -2);
                            logEvent("The dirt fails to blind the moose. It charges at you");
                        }
                        break;

                    case "dodge":
                        if (number <= 50){
                            changeHealth("player", -2);
                            logEvent("The dodge failed and the charging moose smashes into you");
                        } else if (number <= 30){
                            logEvent("Your dodge was successful, the beast charges past you");
                        } else {
                            changeHealth("player", -1);
                            logEvent("The moose grazes you slightly as it charges beside you");
                        }
                        break;

                    case "leap":
                        if (number <= 5){
                            changeHealth("player", -1);
                            logEvent("You throw yourself on the ground but the moose tramples you anyway");
                        } else if (number <= 20){
                            disable("kick", 1);
                            logEvent("The enemy charges, you hit the ground and hurt your ankle");
                        } else if (number <= 35){
                            disable("attack", 1);
                            logEvent("The enemy charges, you hit the ground and hurt your arm");
                        } else if (number <= 90){
                            logEvent("You successfully evade the charging moose");
                        } else {
                            changeHealth("moose", -1);
                            logEvent("The charging moose hits a tree!");
                        }
                        break;

                    case "kick":
                        if (number <= 5){
                            changeHealth("moose", -1);
                            logEvent("You manage to kick the moose before its charge");
                        } else {
                            changeHealth("player", -2);
                            logEvent("The moose charges at you, striking your chest");
                        }
                        break;

                    case "attack":
                        if (number <= 5) {
                            changeHealth("moose", -1);
                            logEvent("Somehow, you managed to do some damage");
                        } else {
                            changeHealth("player", -2);
                            logEvent("The moose charges at you, striking your chest");
                        }
                        break;
                }
                break;

            case "roar":
                switch (playerMove){
                    case "throw":
                        if (number <= 60){
                            changeHealth("player", -1);
                            logEvent("Your throw is ineffective, the moose hits you anyway");
                        } else {
                            logEvent("The moose is distracted by the dirt, you escape any harm");
                        }
                        break;

                    case "dodge":
                        if (number <= 70){
                            logEvent("You dodge the animal's attack");
                        } else if (number <= 75){
                            changeHealth("player", -1);
                            logEvent("The moose grazed your shoulder");
                        } else {
                            disable("leap", 1);
                            logEvent("You dodge the moose's attack, but stumbled and hit your knee");
                        }
                        break;

                    case "leap":
                        if (number <= 60) {
                            changeHealth("player", -3);
                            disable("attack", 1);
                            disable("kick", 1);
                            disable("throw", 1);
                            logEvent("You fall to the ground and the animal mangles you terribly");
                        } else if (number <= 90){
                            changeHealth("player", -2);
                            disable("attack", 1);
                            logEvent("You fall to the ground needlessly and pay for it ");
                        } else {
                            changeHealth("player", -1);
                            logEvent("You fall to the ground, but manage to recover before the moose does much damage");
                        }
                        break;

                    case "kick":
                        if (number <= 5) {
                            changeHealth("player", -1);
                            logEvent("Your kick is too slow and the enemy attack lands");
                        } else {
                            changeHealth("moose", -1);
                            logEvent("Your kick is unexpected and so the moose fails to react");
                        }
                        break;

                    case "attack":
                        if (number <= 50){
                            changeHealth("player", -1);
                            logEvent("You land a punch before the animal attacks");
                        } else if (number <= 80){
                            logEvent("You both miss and stare at each other confused");
                        } else {
                            changeHealth("player", -1);
                            logEvent("Your hand gets stuck in antlers");
                        }
                        break;
                }
                break;

            case "step back":
                switch (playerMove){
                    case "throw":
                        if (number <= 80){
                            logEvent("You throw some dirt at the moose. It's not very effective...");
                        } else if (number <= 90) {
                            changeHealth("player", -2);
                            logEvent("Thanks to the defensive position, the moose foresaw your move");
                        } else {
                            changeHealth("moose", -2);
                            logEvent("Trying to shake off the dirt, the beast hits a tree");
                        }
                        break;

                    case "dodge":
                        logEvent("You both take a defensive stance. Awkward...");
                        break;

                    case "leap":
                        if (number <= 15){
                            disable("kick", 1);
                            logEvent("You hit the ground and hurt your ankle");
                        } else if (number <= 30){
                            disable("attack", 1);
                            logEvent("You hit the ground and hurt your arm");
                        } else {
                            logEvent("You hit the ground as the moose takes a defensive stance");
                        }
                        break;

                    case "kick":
                        if (number <= 50){
                            changeHealth("moose", -2);
                            logEvent("The moose's defensive stance fails to stop you");
                        } else {
                            changeHealth("player", -2);
                            logEvent("The moose's defensive stance allows him to counter");
                        }
                        break;

                    case "attack":
                        if (number <= 50){
                            changeHealth("moose", -1);
                            logEvent("The moose's defensive stance fails to stop you");
                        } else {
                            changeHealth("player", -1);
                            logEvent("The moose's defensive stance allows him to counter");
                        }
                        break;
                }
                break;

            case "eat":
                switch (playerMove){
                    case "throw":
                        if (number <= 30){
                            changeHealth("moose", 1);
                            logEvent("You throw dirt at an eating moose. Rude.");
                        } else {
                            logEvent("The dirt lands in the berries and destroys the meal. The moose will remember this");
                        }
                        break;

                    case "dodge":
                        if (number <= 90){
                            changeHealth("moose", 1);
                            logEvent("You step back, allowing the moose to eat peacefully. He smiles in gratitude");
                        } else {
                            changeHealth("moose", 2);
                            logEvent("You allow the animal to find even more berries. How nice!");
                        }
                        break;

                    case "leap":
                        if (number <= 50) {
                            changeHealth("moose", 1);
                            logEvent("You drop to the ground and allow the moose to eat his berries");
                        } else if (number <= 65){
                            changeHealth("moose", 1);
                            disable("kick", 1);
                            logEvent("You hit the ground, hurting your ankle as the moose eats");
                        } else if (number <= 80){
                            changeHealth("moose", 1);
                            disable("attack", 1);
                            logEvent("You hit the ground, hurting your arm as the moose eats");
                        } else {
                            changeHealth("moose", 2);
                            logEvent("You drop to the ground, which lets the animal find even more berries");
                        }
                        break;

                    case "kick":
                        if (number <= 5){
                            changeHealth("player", 1);
                            logEvent("You steal the berries and eat them");
                        } else if (number <= 60){
                            changeHealth("moose", -2);
                            logEvent("You take advantage of the distraction and land a blow");
                        } else if (number <= 70){
                            changeHealth("moose", 1);
                            logEvent("You miss and allow the moose to eat his berries");
                        } else if (number <= 95){
                            changeHealth("moose", -1);
                            logEvent("You manage to deny the meal");
                        } else {
                            changeHealth("player", -1);
                            disable("attack", 1);
                            disable("kick", 1);
                            logEvent("You try to take the berries, but that makes the animal very mad");
                        }
                        break;

                    case "attack":
                        if (number <= 30){
                            changeHealth("player", 1);
                            logEvent("You steal the berries and eat them");
                        } else if (number <= 40){
                            changeHealth("moose", 1);
                            logEvent("You miss and allow the moose to eat his berries");
                        } else if (number <= 95){
                            changeHealth("moose", -1);
                            logEvent("You take advantage of the situation, but the berries get lost in the tumble");
                        } else {
                            changeHealth("player", -1);
                            disable("attack", 1);
                            disable("kick", 1);
                            logEvent("You try to take the berries, but that makes the animal very mad");
                        }
                        break;
                }
                break;

            case "pick up":
                switch (playerMove){
                    case "throw":
                        if (number <= 30){
                            changeHealth("moose", -1);
                            logEvent("Confused moose hits his head on a branch");
                        } else if (number <= 60){
                            disable("attack", 1);
                            disable("throw", 1);
                            logEvent("The moose kicks dirt into your eyes, you can't see");
                        } else {
                            logEvent("The moose kicks dirt into your eyes as you throw some into his. You both take a moment");
                        }
                        break;

                    case "dodge":
                        if (number <= 50){
                            logEvent("The moose kicks dirt into the air, but you dodge it gracefully");
                        } else if (number <= 80){
                            disable("dodge", 1);
                            disable("kick", 1);
                            logEvent("The moose kicks dirt into your eyes, you stumble and fall");
                        } else {
                            disable("attack", 1);
                            disable("throw", 1);
                            logEvent("The moose kicks dirt into your eyes, you can't see");
                        }
                        break;

                    case "leap":
                        if (number <= 15){
                            disable("kick", 1);
                            logEvent("You dodge the attack, but hurt your ankle");
                        } else if (number <= 30){
                            disable("attack", 1);
                            logEvent("You dodge the attack, but hurt your arm");
                        } else {
                            logEvent("You hit the ground as the moose kicks dirt at you");
                        }
                        break;

                    case "kick":
                        if (number <= 70){
                            changeHealth("player", -1);
                            logEvent("The fiend kicks dirt at your eyes, and counters your attack");
                        } else {
                            changeHealth("moose", -1);
                            logEvent("Despite the dirt kicked at your eyes you manage to do some damage");
                        }
                        break;

                    case "attack":
                        if (number <= 90){
                            changeHealth("player", -1);
                            logEvent("The fiend kicks dirt at your eyes, and counters your attack");
                        } else {
                            changeHealth("moose", -1);
                            logEvent("Despite the dirt kicked at your eyes you manage to do some damage");
                        }
                        break;
                }
                break;

            case "turn around":
                switch (playerMove){
                    case "throw":
                        if (number <= 50){
                            changeHealth("player", -3);
                            disable("throw", 1);
                            logEvent("You throw dirt at its butt. It doesn't care and kicks your face");
                        } else if (number <= 75){
                            changeHealth("player", -2);
                            disable("leap", 1);
                            disable("dodge", 1);
                            logEvent("You throw dirt at its butt and get kicked in stomach");
                        } else {
                            changeHealth("player", 1);
                            disable("attack", 1);
                            logEvent("You throw dirt at its butt and get kicked in your arm");
                        }
                        break;

                    case "dodge":
                        if (number <= 40){
                            changeHealth("player", -3);
                            logEvent("Your dodge wasn't enough, you get kicked in chest");
                        } else if (number <= 70){
                            disable("dodge", 1);
                            disable("leap", 1);
                            logEvent("You stumbled and fell, but you dodged the kick");
                        } else {
                            logEvent("Somehow you manage to dodge the kick");
                        }
                        break;

                    case "leap":
                        if (number <= 60){
                            logEvent("You successfully evade the kick");
                        } else if (number <= 65){
                            changeHealth("player", -1);
                            logEvent("You evaded the kick, but hit a rock on the ground");
                        } else if (number <= 70){
                            changeHealth("moose", -1);
                            logEvent("The best misses and hits a rock");
                        } else if (number <= 15){
                            disable("kick", 1);
                            logEvent("You dodge the kick, but hurt your ankle");
                        } else {
                            disable("attack", 1);
                            logEvent("You dodge the kick, but hurt your arm");
                        }
                        break;

                    case "kick":
                        if (number <= 5){
                            changeHealth("moose", -1);
                            logEvent("Your unexpected assault brings results");
                        } else if (number <= 35){
                            changeHealth("player", -3);
                            logEvent("You try to kick the moose, but get stopped by a kick to the chest");
                        } else if (number <= 65){
                            changeHealth("player", -1);
                            logEvent("You try to kick the moose, but get stopped by a kick to the shoulder");
                        } else {
                            changeHealth("player", -1);
                            changeHealth("moose", -1);
                            logEvent("You land a kick as the beast's hoof grazes your shoulder");
                        }
                        break;

                    case "attack":
                        if (number <= 70){
                            changeHealth("player", -3);
                            disable("attack", 1);
                            disable("throw", 1);
                            logEvent("You try to punch the animal, but get kicked in the head instead");
                        } else {
                            changeHealth("moose", -1);
                            logEvent("You landed a fortunate blow, before the beast could react");
                        }
                        break;
                }
                break;

            default:
                throw new NullPointerException();
        }

        checkEndgame();
        mooseTurn();
    }

    void logEvent(String text){
        TextView logBox = findViewById(R.id.logText);
        logBox.append("\n" + text);
        lastEvent = text;
    }

    public void throwPressed (View view){
        finishTurn("throw");
    }

    public void dodgePressed (View view){
        finishTurn("dodge");
    }

    public void leapPressed (View view){
        finishTurn("leap");
    }

    public void kickPressed (View view){
        finishTurn("kick");
    }

    public void attackPressed (View view){
        finishTurn("attack");
    }

    public void backPressed (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void retryPressed (View view){
        startgame();
        hideFragment(endgameFragment);
    }

    public void sharePressed (View view){
        //TODO: social media integration
    }

    public void hideFragment (Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    public void showFragment (Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed (){
        if (endgameFragment.isVisible()) finish();
        else {
            DialogFragment dialog = new SurrenderDialogFragment();
            dialog.show(getFragmentManager(), "surrender");
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        endgame("surrender");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Do nothing
    }

    protected abstract void updateTurns();
}
