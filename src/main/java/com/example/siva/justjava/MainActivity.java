package com.example.siva.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 1;

    public int calculatePrice(boolean addhaswhipped, boolean addhasChocolate)
    {
        int price = 5;
        if(addhaswhipped==true)
        {
            price = price + 1;
        }
        if(addhasChocolate==true)
        {
            price = price + 2;
        }
        return quantity*price;
    }

    public void submitOrder(View view)
    {
        String quantityMessage = createOrderSummary(quantity);
        displayMessage(quantityMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    public int increment(View view)
    {
        if(quantity==100)
        {
            Context context = getApplicationContext();
            CharSequence text = "Cannot have more than 100 coffees";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return 1;
        }
        quantity = quantity + 1;
        display(quantity);
        return 0;
    }
    public int decrement(View view)
    {
        if(quantity==1)
        {
            Context context = getApplicationContext();
            CharSequence text = "Cannot have less than one coffee";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return 1;
        }
        quantity = quantity - 1;
        display(quantity);
        return 0;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message)
    {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
    private String createOrderSummary(int number)
    {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.toppings);
        boolean haswhipped = whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.toppings1);
        boolean hasChocolate = chocolate.isChecked();
        EditText Name = (EditText) findViewById(R.id.name_text_view);
        String nameText = Name.getText().toString();
        String msg = "Name: "+nameText+"\nQuantity :"+quantity+"\nHas Whipped Cream :"+haswhipped+"\nHas Chocolate :"+hasChocolate+"\nTotal cost :"+calculatePrice(haswhipped, hasChocolate)+"\nThank you";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "sivaperumal644@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for "+nameText);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        return msg;
    }
}