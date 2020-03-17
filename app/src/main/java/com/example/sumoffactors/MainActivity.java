/**
 * AUCSC 220
 * Assignment #2:
 * sumOfAllFactors() Android App
 *
 * MainActivity.java
 *
 * Android implementation of a previous Java exercise that
 * finds the sum of all factors of a given positive integer.
 * The main activity is where the user inputs their integer, which is then checked.
 * If the check is passed, the int is sent to the other activity, "main2activity".
 *
 * Methods:
 *
 *  - onCreate(Bundle) -> void
 *      I'm not sure what a bundle is, or what Bundle is, but all I know is
 *      that this function is called when the activity is first created and sets everything up
 *      to look and work as intended. Called at run time I think.
 *
 *  - intCheck(int) -> boolean
 *      Parses the user's input for a valid integer.
 *      If nothing entered, nothing happens -> returns false
 *      If int = 0, prompts user for larger int -> returns false
 *      If int > 2,147,483,647, informs user that the int is too big (and thus can't be
 *      parsed as an int) -> returns false
 *      Otherwise true is returned
 *
 *  - sendIntToOtherActivity(View) -> void
 *      Sends the user input to the other activity where it's sum of factors will be computed.
 *      Based on the structure of my code, the int is only sent if intCheck() on that int is true
 *
 * Started September 27, 2019
 * Completed October 4, 2019
 *
 * Keegan Petreman (petreman@ualberta.ca)
 * 1528679
 */

package com.example.sumoffactors;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button computeFactorSum = (Button) findViewById(R.id.computeSumOfFactorsButton);

        final EditText userInputInt = (EditText) findViewById(R.id.posIntEditText);

        // I got this setOnClickListener idea from following this guide a while ago:
        // https://developer.android.com/training/basics/firstapp
        // I like it a bit better than setting the onClick in the attributes of the button,
        // as you can do a bit more than just one function
        //
        // What's going on here is that the user input is checked by intCheck() on button click,
        // then if that returns true the int's factors can be summed
        computeFactorSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText numParse = (EditText) findViewById(R.id.posIntEditText);

                boolean goodInt = intCheck(v);

                if(goodInt){
                    sendIntToOtherActivity(v);
                }//if

            }//onClick

        });

        //I have this editText listener to detect when the editText has been modified.
        //If the the only thing in the edit text is "0", the editText is wiped clean.
        //So what this does is prevent the user from making 0 the first digit of their integer
        //
        //Resource on this implementation from https://stackoverflow.com/a/20248774
        //afterTextChanged and beforeTextChanged have to be here for some reason even though they
        //aren't used. Otherwise an error is given by the IDE
        userInputInt.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (userInputInt.getText().toString().matches("^0")){

                    userInputInt.setText("");

                }//if

            }//onTextChanged

        });

    }//onCreate

    /**
     * Checks the users input for valid int
     * If input is 0, greater than the max value for an int, or empty, intCheck fails and the
     * appropriate "Toast" message is given for feedback
     * Otherwise int is good and true is returned
     * I'm not sure why the view needs to be passed in, but this I saw this behaviour
     * from following this official guide: https://developer.android.com/training/basics/firstapp
     *
     * Complies with rule 5) Prevent errors, as incorrect behaviour is handled and the user will
     * know why through the use of toasts (little message bubbles)
     *
     * @param - a view
     * @return - true if valid integer is parsed, false otherwise
     */
    public boolean intCheck(View view){

        EditText numParse = (EditText) findViewById(R.id.posIntEditText);
        int intIn;

        //if nothing is in the editText but "Compute" is pressed
        if (numParse.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, this.getString(R.string.empty_in), Toast.LENGTH_LONG);
            toast.show();
            return false;
        }//if

        //try to parse integer, so that app doesn't crash if an int isn't actually entered
        try {
            intIn = Integer.parseInt(numParse.getText().toString());
        }//try

        catch (Exception NumberFormatException){
            Toast toast = Toast.makeText(this, this.getString(R.string.big_in), Toast.LENGTH_LONG);
            toast.show();
            return false;
        }//catch

        //mainly for if 0 is entered, as I made it so that negative numbers can't be entered
        if (intIn < 1){
            Toast toast = Toast.makeText(this, this.getString(R.string.neg_in), Toast.LENGTH_LONG);
            toast.show();
            return false;
        }//if

        return true;

    }//intCheck

    /**
     * Starts the other activity where the sum of user's int is displayed.
     * The int is sent to the other activity because that's where the summation will occur
     * @param view
     */
    public void sendIntToOtherActivity(View view) {

        EditText numParse = (EditText) findViewById(R.id.posIntEditText);
        int posIntIn = Integer.parseInt(numParse.getText().toString());
        Intent intent = new Intent(this, Main2Activity.class);

        intent.putExtra("posInt", posIntIn);
        startActivity(intent);

    }//sendIntToOtherActivity

}//MainActivity
