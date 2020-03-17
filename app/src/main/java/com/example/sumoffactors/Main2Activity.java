/**
 * AUCSC 220
 * Assignment #2
 * sumOfAllFactors() Android App
 *
 * Main2Activity.java
 * (I couldn't think of a fitting name, and because the project is small enough I used the default)
 *
 * This activity is created by it's parent activity, mainActivity. It receives an integer from
 * mainActivity and computes it's sum of factors, then displays that in a textView
 *
 * Methods:
 *
 *  - onCreate(Bundle savedInstanceState) -> void
 *      All this activity does on creation is compute the sum of factors for the int received
 *      from mainActivity, and set that value in the only textView on screen
 *
 *  - sumOfAllFactors(int posInt) -> long
 *      Takes the provided positive integer and returns the sum of those factors as a long.
 *      Long is returned because for large ints, the sum of their factors can exceed java.MAX_INT
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
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int posInt = intent.getIntExtra("posInt", 0);
        long sumOfFactors = sumOfAllFactors(posInt);

        TextView sumOfFactorsResult = findViewById(R.id.sumOfFactorsResultTextView);
        sumOfFactorsResult.setText(
                "The sum of the factors for " + posInt + " is " + sumOfFactors);

    }//onCreate

    /**
     * This is the actual function that gets and sums all the factors of a
     * given positive integer
     *
     * This implementation is different from my original: it uses the more
     * efficient square root method
     * Used https://www.geeksforgeeks.org/sum-factors-number/ as a guide
     * Based on the structure of my code, it should never receive an int that is too big
     *
     * @param posInt - a positive integer
     * @return - the sum of all the factors for posInt
     */
    public static long sumOfAllFactors(int posInt){

        long sum = 0;

        for (int currentNum = 1; currentNum <= Math.sqrt(posInt); currentNum++){

            if (posInt % currentNum == 0) {

                // if both divisors are same
                // then add it once else add both
                if (currentNum == (posInt / currentNum)) {
                    sum += currentNum;
                }//if

                else {
                    //cast to longs because for posInt = 2,147,483,647
                    // 1 + 2,147,483,647 = -2,147,483,648 if left as ints
                    sum += (currentNum + ((long)posInt / (long)currentNum));
                }//else

            }//if

        }//for

        return sum;

    }//sumOfAllFactors

}//Main2Activity
