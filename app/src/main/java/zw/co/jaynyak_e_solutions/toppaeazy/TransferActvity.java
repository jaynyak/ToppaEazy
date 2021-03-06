package zw.co.jaynyak_e_solutions.toppaeazy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
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

public class TransferActvity extends AppCompatActivity {

    private static final int SEND_SMS_PERMISSIONS_REQUEST = 1;
    Button transferButton;
    EditText transfer;
    EditText phone;
    EditText pin1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            getPermissionToSendSMS();
        } else {
        }

        transferButton = (Button) findViewById(R.id.transferButton);
        phone = (EditText) findViewById(R.id.numEditText);
        transfer = (EditText) findViewById(R.id.transferEditText);
        pin1 = (EditText) findViewById(R.id.pinEditText1);


        transferButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (transfer.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Details.", Toast.LENGTH_LONG).show();
                    return;
                }

                String phoneNo = "180";
                String message = ("TRANSFER " + transfer.getText().toString() + " " + phone.getText().toString() + " " + pin1.getText().toString());
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);

                sendSMSMessage();

                try {
                    Toast.makeText(getApplicationContext(), "Funds transfer request submitted, Check SMS for DETAILS .", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Airtime TopUp failed, Please try again.", Toast.LENGTH_LONG).show();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToSendSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.SEND_SMS)) {
                Toast.makeText(this, "Please allow ToppaEazy to send SMS!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == SEND_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Send SMS permission granted", Toast.LENGTH_SHORT).show();
                sendSMSMessage();
            } else {
                Toast.makeText(this, "Send SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
