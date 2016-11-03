package zw.co.jaynyak_e_solutions.toppaeazy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button regButton;
    EditText textFirstName;
    EditText textSurname;
    EditText textIdNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        regButton = (Button) findViewById(R.id.cornfirmRegistrationButton);
        textFirstName = (EditText) findViewById(R.id.firstNameEditText);
        textSurname = (EditText) findViewById(R.id.surnameEditText);
        textIdNo = (EditText) findViewById(R.id.idNumberEditText);

        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (textFirstName.getText().toString().equals("")
                        || textSurname.getText().toString().equals("")
                        || textIdNo.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Details.", Toast.LENGTH_LONG).show();
                    return;
                }

                String phoneNo = "180";
                String message = ("REG " + textFirstName.getText().toString() + " " + textSurname.getText().toString() + " " + textIdNo.getText().toString());
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);

                sendSMSMessage();

                try {
                    Toast.makeText(getApplicationContext(), "Registration request sent, Check SMS For PIN.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Registration request failed, Please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    protected void sendSMSMessage() {
        Log.i("Send SMS", "");


        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_recharge:
                Intent intent = new Intent(this, RechargeActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_register_with_hot:
                Intent intent0 = new Intent(this, RegisterActivity.class);
                this.startActivity(intent0);
                break;
            case R.id.action_ecoCash_topUp:
                Intent intent1 = new Intent(this, AddFundsActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.action_transfer_funds:
                Intent intent2 = new Intent(this, TransferActvity.class);
                this.startActivity(intent2);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }

}
