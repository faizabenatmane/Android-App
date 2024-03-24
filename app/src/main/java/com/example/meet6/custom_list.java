package com.example.meet6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class custom_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list);

        EditText name=findViewById(R.id.editname);
        EditText age=findViewById(R.id.editage);
        Button btn=findViewById(R.id.btn_add);
        ListView liste = findViewById(R.id.lisview);


        ArrayList<person> personArrayList = new ArrayList<>();
        personArrayList.add(new person(R.drawable.img_3, "faiza", " 21 yo"));
        personArrayList.add(new person(R.drawable.img_3, "malek", " 20 yo"));
        personArrayList.add(new person(R.drawable.img_3, "sarah", " 19 yo"));
        personArrayList.add(new person(R.drawable.img_3, "feriel", " 19 yo"));

        adapter_person adapterPerson = new adapter_person(this, personArrayList);
        liste.setAdapter(adapterPerson);


        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), " short Toast message", Toast.LENGTH_SHORT).show();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                personArrayList.add(new person(R.drawable.img_3,name.getText().toString(),age.getText().toString()));
//
//                // Notify the adapter that the data set has changed
//                adapterPerson.notifyDataSetChanged();
//
//                // Display a toast message to indicate that a person has been added
//                Toast.makeText(getApplicationContext(), "Person added successfully", Toast.LENGTH_SHORT).show();
//
//                // Clear the EditText fields after adding a person
//                name.setText("");
//                age.setText("");

                AlertDialog.Builder builder = new AlertDialog.Builder(custom_list.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_layout, null);

                final EditText editTextQuestion = dialogView.findViewById(R.id.editTextQuestion);

                builder.setTitle("Ask a Question")
                        .setView(dialogView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle positive button click (OK)
                                String question = editTextQuestion.getText().toString();
                                if (!question.isEmpty()) {
                                    Toast.makeText(custom_list.this, "your question has been sent", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(custom_list.this, "Please enter a question", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle negative button click (Cancel) or dismiss the dialog
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });




    }






    // Define a method to create notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "This is my notification channel";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, NotificationManager.IMPORTANCE_DEFAULT);
            //channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Create and display a simple notification
    private void showNotification() {
        // Create an intent to open the activity when notification is clicked
//        Intent intent = new Intent(this, custom_list.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_notification) // Set small icon
                .setContentTitle("My Notification") // Set title
                .setContentText("This is a notification example") // Set content text
                //.setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set priority
                //.setContentIntent(pendingIntent) // Set intent to be triggered when notification is clicked
                .setAutoCancel(true); // Automatically remove the notification when user taps on it

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build()); // 1 is the notification ID, it should be unique for each notification
    }


}