package com.example.abhi.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhi.myapplication.model.Person;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddContact extends AppCompatActivity {

    TextView etName,etNumber ,etLog;
    Button btSave;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);
        btSave = (Button) findViewById(R.id.btSave);
        etLog = (EditText) findViewById(R.id.etLog);
       // realm = Realm.getDefaultInstance();
    }
    public void btSaveonClick(View view){

        save_into_database(etName.getText().toString(), Integer.parseInt(etNumber.getText().toString()));

        refresh_database();


    }

    private void refresh_database() {
        // Build the query looking at all users:

        RealmResults<Person> r = realm.where(Person.class).findAll();
        String Output="";
        for (Person person: r){
            Output += person.toString();

        }

        etLog.setText(Output);


    }

    private void save_into_database(final String name, final int number) {
        realm.executeTransaction(new Realm.Transaction() {

            public void execute(Realm realm) {
                Person per = realm.createObject(Person.class);
                per.setPersonName(name);
                per.setNumber(number);
            }
        });
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm bgRealm) {
//                Person person = bgRealm.createObject(Person.class);
//                person.setPersonName(name);
//                person.setNumber(number);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//
//                Toast.makeText(AddContact.this, "Contact added", Toast.LENGTH_SHORT).show();
//                // Transaction was a success.
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//
//                Toast.makeText(AddContact.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                // Transaction failed and was automatically canceled.
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

}
