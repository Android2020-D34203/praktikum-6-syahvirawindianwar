package id.ac.id.telkomuniversity.tass.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonPindah;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPindah = findViewById(R.id.button);
        editText = findViewById(R.id.tampil);

       buttonPindah.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(editText.getText().toString().length() ==0){
                   Toast.makeText(MainActivity.this,
                           "Input tidak boleh kosong !",Toast.LENGTH_LONG).show();
               }
               else {
                   final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                   builder.setTitle("Pindah Activty");
                   builder.setMessage("Apakah anda yakin ingin pindah?");
                   builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           int NOTIFICATION_ID = 214;
                           String CHANNEL_ID = "Syahvira's channel";
                           Intent intent = new Intent(MainActivity.this,SecondactivityActivity.class);
                           intent.putExtra("Text", editText.getText().toString());
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                           PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                               CharSequence name           = CHANNEL_ID;
                               String description          = CHANNEL_ID;
                               int importance              = NotificationManager.IMPORTANCE_DEFAULT;

                               NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                               channel.setDescription(description);
                               NotificationManager notificationManager = getSystemService(NotificationManager.class);
                               notificationManager.createNotificationChannel(channel);
                           }

                           NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                           builder.setSmallIcon(R.drawable.download);
                           builder.setContentTitle("Pindah Activity");
                           builder.setContentText("Datamu telah terkirim!");
                           builder.setStyle(new NotificationCompat.BigTextStyle()
                                   .bigText("Selamat, kamu sudah pindah ke halaman berikutnya dan datamu sudah terkirim!"));
                           builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                           builder.setContentIntent(pendingIntent);

                           NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

                           // notificationId is a unique int for each notification that you must define
                           notificationManager.notify(NOTIFICATION_ID, builder.build());
                           startActivity(intent);
                       }
                   });
                   builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           Toast.makeText(MainActivity.this, "Anda Tidak Jadi Pindah Ke Halaman Berikutnya", Toast.LENGTH_SHORT).show();
                       }
                   });
                   builder.show();


               }
           }
       });



    }

}