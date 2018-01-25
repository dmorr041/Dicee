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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link layout elements
        rollButton = (Button) findViewById(R.id.rollButton);
        mDice_Type = (Spinner) findViewById(R.id.mDice_Type_Spinner);

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

        // Connect Image View of Dice
        //final ImageView leftDice = (ImageView) findViewById(R.id.image_leftDice);
        //final ImageView rightDice = (ImageView) findViewById(R.id.image_rightDice);


        final int[] diceArray = {
                        R.drawable.dice1,
                        R.drawable.dice2,
                        R.drawable.dice3,
                        R.drawable.dice4,
                        R.drawable.dice5,
                        R.drawable.dice6
        };

        // Images of D20 sides
        final int[] D20_Array = {
                R.drawable.D20_1,
                R.drawable.D20_3,
                R.drawable.D20_4,
                R.drawable.D20_5,
                R.drawable.D20_6,
                R.drawable.D20_7,
                R.drawable.D20_8,
                R.drawable.D20_9,
                R.drawable.D20_10,
                R.drawable.D20_11,
                R.drawable.D20_12,
                R.drawable.D20_13,
                R.drawable.D20_14,
                R.drawable.D20_15,
                R.drawable.D20_16,
                R.drawable.D20_17,
                R.drawable.D20_18,
                R.drawable.D20_19,
                R.drawable.D20_20,
        };

        final int[] D12_Array = {
                R.drawable.D12_1,
                R.drawable.D12_2,
                R.drawable.D12_3,
                R.drawable.D12_4,
                R.drawable.D12_5,
                R.drawable.D12_6,
                R.drawable.D12_7,
                R.drawable.D12_8,
                R.drawable.D12_9,
                R.drawable.D12_10,
                R.drawable.D12_11,
                R.drawable.D12_12
        };

        final int[] D10_Array = {
                R.drawable.D10_1,
                R.drawable.D10_2,
                R.drawable.D10_3,
                R.drawable.D10_4,
                R.drawable.D10_5,
                R.drawable.D10_6,
                R.drawable.D10_7,
                R.drawable.D10_8,
                R.drawable.D10_9,
                R.drawable.D10_10
        };

        final int[] D8_Array = {
                R.drawable.D8_1,
                R.drawable.D8_2,
                R.drawable.D8_3,
                R.drawable.D8_4,
                R.drawable.D8_5,
                R.drawable.D8_6,
                R.drawable.D8_7,
                R.drawable.D8_8
        };

        final int[] D6_Array = {
                R.drawable.D6_1,
                R.drawable.D6_2,
                R.drawable.D6_3,
                R.drawable.D6_4,
                R.drawable.D6_5,
                R.drawable.D6_6
        };

        final int[] D4_Array = {
                R.drawable.D4_1,
                R.drawable.D4_2,
                R.drawable.D4_3,
                R.drawable.D4_4
        };

        // When a user clicks the Roll button
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDice_type == D20){
                    generate_DiceFace(20);
                }
                else if(mDice_type == D12){
                    generate_DiceFace(12);
                }
                else if(mDice_type == D10){
                    generate_DiceFace(10);
                }
                else if(mDice_type == D10percent){
                    generate_DiceFace(10);
                    generate_DiceFace(10);
                }
                else if(mDice_type == D8){
                    generate_DiceFace(8);
                }
                else if(mDice_type == D6){
                    generate_DiceFace(6);
                }
                else if(mDice_type == D4){
                    generate_DiceFace(4);
                }
            }
        });
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
        if(mDice_type == D20){
            dice.setImageResource(D20_Array[number]);
        }
        else if(mDice_type == D12){
            dice.setImageResource(D12_Array[number]);
        }
        else if(mDice_type == D10){
            dice.setImageResource(D10_Array[number]);
        }
        else if(mDice_type == D10percent){
            dice.setImageResource(D10_Array[number]);
            dice2.setImageResource(D10_Array[number]);
        }
        else if(mDice_type == D8){
            dice.setImageResource(D8_Array[number]);
        }
        else if(mDice_type == D6){
            dice.setImageResource(D6_Array[number]);
        }
        else if(mDice_type == D4){
            dice.setImageResource(D4_Array[number]);
        }
    }
}
