package edu.orangecoastcollege.cs273.tipcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();
    //Associate the controller with needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView percentTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    //Associate the controller with needed model
    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Connect the controller with the widgets in our app
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        //Define a listener for the amountEditText (onTextChange)
    amountEditText.addTextChangedListener(amountTextChangedListener);

        //Define a listener for the percentSeekBar
    percentSeekBar.setOnSeekBarChangeListener(percentChangedListener);
    }



    private TextWatcher amountTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            try{
                double amount = Double.parseDouble(charSequence.toString()) / 100.0;
                currentBill.setAmount(amount);

            }
            catch (NumberFormatException e)
            {
                amountEditText.setText("");
            }

            //No exception, input is valid
            //1) Set the tip amount(tipTextView)
            //tipTextView.setText(currency.format(currentBill.getAmount());

            amountTextView.setText(currency.format(currentBill.getAmount()));
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        //Do nothing
                }
    };

    private SeekBar.OnSeekBarChangeListener percentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            currentBill.setTipPercent(i / 100.0);

            //Update the percentTextView
            percentTextView.setText(percent.format(currentBill.getTipPercent()));
            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
//do nothing
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
//do nothing
        }



    };
    private void updateViews()
    {
        tipTextView.setText(currency.format(currentBill.getTipAmount()));
        totalTextView.setText(currency.format(currentBill.getTotalAmount()));
    }

}
