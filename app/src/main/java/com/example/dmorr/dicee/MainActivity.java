package com.example.dmorr.dicee;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String mDice_type;  // The type of die (D20, D12, D10, D10percent, D8, D6, or D4)
    private ImageView dice;     // The die being rolled
    private ImageView dice2;    // A second die being rolled (in special cases)
    private Spinner mDice_Type; // Spinner to select the dice type
    private Button rollButton;  // Roll button


    final int[] D20_Array = {
            R.drawable.d20side1,
            R.drawable.d20side2,
            R.drawable.d20side3,
            R.drawable.d20side4,
            R.drawable.d20side5,
            R.drawable.d20side6,
            R.drawable.d20side7,
            R.drawable.d20side8,
            R.drawable.d20side9,
            R.drawable.d20side10,
            R.drawable.d20side11,
            R.drawable.d20side12,
            R.drawable.d20side13,
            R.drawable.d20side14,
            R.drawable.d20side15,
            R.drawable.d20side16,
            R.drawable.d20side17,
            R.drawable.d20side18,
            R.drawable.d20side19,
            R.drawable.d20side20
    };

    final int[] D12_Array = {
            R.drawable.d12side1,
            R.drawable.d12side2,
            R.drawable.d12side3,
            R.drawable.d12side4,
            R.drawable.d12side5,
            R.drawable.d12side6,
            R.drawable.d12side7,
            R.drawable.d12side8,
            R.drawable.d12side9,
            R.drawable.d12side10,
            R.drawable.d12side11,
            R.drawable.d12side12
    };

    final int[] D10_Array = {
            R.drawable.d10side1,
            R.drawable.d10side2,
            R.drawable.d10side3,
            R.drawable.d10side4,
            R.drawable.d10side5,
            R.drawable.d10side6,
            R.drawable.d10side7,
            R.drawable.d10side8,
            R.drawable.d10side9,
            R.drawable.d10side10
    };

    final int[] D8_Array = {
            R.drawable.d8side1v2,
            R.drawable.d8side2v2,
            R.drawable.d8side3v2,
            R.drawable.d8side4v2,
            R.drawable.d8side5v2,
            R.drawable.d8side6v2,
            R.drawable.d8side7v2,
            R.drawable.d8side8v2
    };

    final int[] D6_Array = {
            R.drawable.d6side1,
            R.drawable.d6side2,
            R.drawable.d6side3,
            R.drawable.d6side4,
            R.drawable.d6side5,
            R.drawable.d6side6
    };

    final int[] D4_Array = {
            R.drawable.d4side1,
            R.drawable.d4side2,
            R.drawable.d4side3,
            R.drawable.d4side4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link layout elements
        rollButton = (Button) findViewById(R.id.rollButton);
        mDice_Type = (Spinner) findViewById(R.id.mDice_Type_Spinner);

        // ************************* SPINNER LOGIC ************************************************

        // Spinner Drop down elements
        List<String> modes = new ArrayList<>();
        modes.add("D20");
        modes.add("D12");
        modes.add("D10");
        modes.add("D10+");
        modes.add("D8");
        modes.add("D6");
        modes.add("D4");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching data adapter to spinner
        mDice_Type.setAdapter(dataAdapter);

        // Spinner click listener
        mDice_Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null) {
                    // Handle when user clicks something in spinner
                    mDice_type = item.toString();

                    Toast.makeText(MainActivity.this, item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Selected",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when user clicks nothing in s
            }
        });
        // ************************* SPINNER LOGIC END *********************************************

        // Connect Image View of Dice
        //final ImageView leftDice = (ImageView) findViewById(R.id.image_leftDice);
        //final ImageView rightDice = (ImageView) findViewById(R.id.image_rightDice);


//        final int[] diceArray = {
//                        R.drawable.dice1,
//                        R.drawable.dice2,
//                        R.drawable.dice3,
//                        R.drawable.dice4,
//                        R.drawable.dice5,
//                        R.drawable.dice6
//        };


        // ********************************* ROLL BUTTON LOGIC ************************************

        // When a user clicks the Roll button
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDice_type.equals("D20")){
                    generate_DiceFace(20);
                }
                else if(mDice_type.equals("D12")){
                    generate_DiceFace(12);
                }
                else if(mDice_type.equals("D10")){
                    generate_DiceFace(10);
                }
                else if(mDice_type.equals("D10+")){
                    generate_DiceFace(10);
                    generate_DiceFace(10);
                }
                else if(mDice_type.equals("D8")){
                    generate_DiceFace(8);
                }
                else if(mDice_type.equals("D6")){
                    generate_DiceFace(6);
                }
                else if(mDice_type.equals("D4")){
                    generate_DiceFace(4);
                }
            }
        });
        // ********************************* ROLL BUTTON LOGIC END *********************************
    }

    /**
     * Helper function to generate the dice faces for each type of die
     * @param limit - The limit for the random number (dependent on dice type)
     */
    private void generate_DiceFace(int limit){

        // Generate the random number to set dice ImageView faces accordingly
        Random randomNumberGenerator = new Random();
        int number = randomNumberGenerator.nextInt(limit);

        // Set the dice ImageView faces accordingly
        if(mDice_type.equals("D20")){
            dice.setImageResource(D20_Array[number]);
        }
        else if(mDice_type.equals("D12")){
            dice.setImageResource(D12_Array[number]);
        }
        else if(mDice_type.equals("D10")){
            dice.setImageResource(D10_Array[number]);
        }
        else if(mDice_type.equals("D10+")){
            dice.setImageResource(D10_Array[number]);
            dice2.setImageResource(D10_Array[number]);
        }
        else if(mDice_type.equals("D8")){
            dice.setImageResource(D8_Array[number]);
        }
        else if(mDice_type.equals("D6")){
            dice.setImageResource(D6_Array[number]);
        }
        else if(mDice_type.equals("D4")){
            dice.setImageResource(D4_Array[number]);
        }
    }
}
