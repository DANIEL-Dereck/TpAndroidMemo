package com.example.dereck.memo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    public static int ACTIVITY_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = getIntent().getIntExtra(MainActivity.ITEM_POSITION, -1);
        String text = getIntent().getStringExtra(MainActivity.ITEM_TEXT);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailFragment fragment = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity.ITEM_POSITION, position);
        bundle.putString(MainActivity.ITEM_TEXT, text);
        fragment.setArguments(bundle);

        fragmentTransaction.add(R.id.content_fragment, fragment, "fragment_detail");
        fragmentTransaction.commit();
    }

    /*
    public void returnToMain(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    */
}
