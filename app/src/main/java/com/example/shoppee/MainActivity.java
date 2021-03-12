package com.example.shoppee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method is called when the order button is clicked.
    public void submitOrder(View view) {
        CheckBox checkwc = findViewById(R.id.wc);
        boolean haswc = checkwc.isChecked();

        CheckBox checkchoc = findViewById(R.id.choco);
        boolean haschoc = checkchoc.isChecked();

        EditText name = findViewById(R.id.naam);
        String text = name.getText().toString();

        int price = calculatePrice(haswc, haschoc);

        String priceMessage = createOrderSummary(price, haswc, haschoc, text);
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(Intent.EXTRA_SUBJECT, "CoffeeShoppee Order for " + text);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // This method displays the given quantity value on the screen.
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(number);
    }

    // This method displays the given text on the screen.
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public String createOrderSummary(int price, boolean bool1, boolean bool2, String name){
        String orderSummary = "Name: " + name + "\nQuantity: " + quantity + "\nWhipped Cream? " + bool1 + "\nChocolate? " + bool2 + "\nTotal: $" + price + "\nThankyou!";
        return orderSummary;
    }

    public void increment(View view){
        if (quantity == 100){
            return;
        } else {
            quantity = quantity + 1;
            display(quantity);
        }
    }

    public void decrement(View view){
        if (quantity == 1){
            return;
        }else {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    private int calculatePrice(boolean bool1, boolean bool2) {
        int price = 5;

        if (bool1 == true){
            price = price + 1;
        }

        if (bool2 == true){
            price = price + 2;
        }

        int totalprice = price * quantity;
        return totalprice;
    }
}
