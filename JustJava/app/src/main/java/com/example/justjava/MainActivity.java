package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);
        boolean HasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean HasChocolate = chocolate.isChecked();

        int price = calculatePrice();

        String message = createOrderSummary(name, price, HasWhippedCream, HasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, "Just Java order for "+message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);
        boolean HasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean HasChocolate = chocolate.isChecked();

        if (HasWhippedCream && !HasChocolate) {
            price = 6;
        } else {
            if (HasChocolate && !HasWhippedCream) {
                price = 7;
            } else {
                if (HasWhippedCream && true == true) {
                    price = 8;
                } else
                    price = 5;
            }
        }
        return (quantity * price);
    }


    private String createOrderSummary(String name, int number, boolean bo, boolean boo) {
        String summary = "Name: " + name + "\nAdd Whipped Cream? " + bo + "\nAdd Chocolate? " + boo + "\nQuantity: " + quantity + "\nTotal: $" + number + "\nThank You!";
        return summary;
    }
}