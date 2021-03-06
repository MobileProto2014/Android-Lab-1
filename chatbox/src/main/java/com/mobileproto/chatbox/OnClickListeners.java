package com.mobileproto.chatbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by clee2 on 5/26/2014.
 */
public class OnClickListeners {
    /**
     * MainActivity OnClickListeners
     */

    public static View.OnClickListener sendButtonListener(final Activity activity, final AdapterChat adapter){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = ((EditText) activity.findViewById(R.id.main_chat_input));
                if (input.getText().toString().equals("")){
                    Toast.makeText(activity, "You didn't type anything in!", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapter.addChat(new ModelChat(MainActivity.username, input.getText().toString(), MainActivity.userId));
                input.setText("");
            };
        };
    }

    public static void changeUsernameListener(final Activity activity){
        final EditText input = new EditText(activity);
        new AlertDialog.Builder(activity)
                .setTitle("Change Username")
                .setMessage("This is how you will show up to others.")
                .setView(input)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newName = input.getText().toString();
                        if (newName.equals("")) {
                            Toast.makeText(activity, "Your username can't be blank!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        MainActivity.username = newName;
                        Toast.makeText(activity, "Your username is now " + newName, Toast.LENGTH_SHORT).show();
                        if (!MainActivity.userColors.containsKey(newName)){
                            MainActivity.addNewUser(newName);
                        }
                        activity.findViewById(R.id.main_chat_button).setBackgroundResource(MainActivity.userColors.get(MainActivity.username));
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}
