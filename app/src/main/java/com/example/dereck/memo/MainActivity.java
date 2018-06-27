package com.example.dereck.memo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dereck.memo.Adapter.MemoAdapter;
import com.example.dereck.memo.DAO.MemoDao;
import com.example.dereck.memo.Entity.Memo;
import com.example.dereck.memo.Helper.DatabaseHelper;
import com.example.dereck.memo.Helper.ItemTouchHelperCallback;
import com.example.dereck.memo.Manager.DatabaseManager;
import com.example.dereck.memo.WebService.WebServiceManager;
import com.loopj.android.http.RequestHandle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    public static String TAG = MainActivity.class.toString();
    public static int ACTIVITY_CODE = 100;
    public static String ITEM_POSITION = "ITEM_POSITION";
    public static String ITEM_TEXT = "ITEM_TEXT";
    private static Integer CREATE = 5;
    public static String EXAMPLE_URL = "http://httpbin.org/post";

    private RecyclerView recycler;
    private GestureDetector gestureDetector;
    private EditText editText;
    private MemoAdapter memoAdapter;
    private List<Memo> memos;
    private MemoDao memoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.memoDao = new MemoDao();
        this.memos = new ArrayList<>();

        this.recycler = (RecyclerView)findViewById(R.id.lst_items);
        this.editText = (EditText)findViewById(R.id.txt_add_item);

        initList();
        recycler.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        this.memoAdapter = new MemoAdapter(this.memos);
        recycler.setAdapter(memoAdapter);

        recycler.addOnItemTouchListener(this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelperCallback(memoAdapter));
        itemTouchHelper.attachToRecyclerView(recycler);

        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener()
                {
                    @Override
                    public boolean onSingleTapUp(MotionEvent event)
                    {
                        return true;
                    }
                });

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        int position = preferences.getInt(WebServiceManager.KEY_MEMO, -1);

        Toast.makeText(this,String.valueOf(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent)
    {
        if (gestureDetector.onTouchEvent(motionEvent))
        {
            View child = recyclerView.findChildViewUnder(motionEvent.getX(),
                    motionEvent.getY());

            if (child != null)
            {
                int position = recyclerView.getChildAdapterPosition(child);

                /* EXO 1 : display position into TOAST */
                Toast.makeText(this,String.valueOf(position),Toast.LENGTH_SHORT).show();

                /* EXO 2 : Use WS */
                /*
                WebServiceManager wsm = new WebServiceManager(EXAMPLE_URL);
                wsm.put("memo",position);
                wsm.call(this);
                */

                /* EXO 3 : Add position into sharedPreference */
                /*
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(WebServiceManager.KEY_MEMO, position);
                editor.apply();
                */

                /* EXO 4 : fragment */
                FrameLayout frameLayout = findViewById(R.id.container_fragment);
                if (frameLayout != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    DetailFragment fragment = new DetailFragment();

                    Bundle bundle = new Bundle();
                    bundle.putInt(MainActivity.ITEM_POSITION, position);
                    bundle.putString(MainActivity.ITEM_TEXT, this.memos.get(position).getText());
                    fragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.container_fragment, fragment, "fragment_detail");
                    fragmentTransaction.commit();
                } else {
                    Intent intent = new Intent(this, DetailActivity.class);
                    intent.putExtra(ITEM_POSITION, position);
                    intent.putExtra(ITEM_TEXT, this.memos.get(position).getText());
                    startActivity(intent);
                }

                memoAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public void addItem(View view)
    {
        if (this.editText.getText().toString() != null && this.editText.getText().toString() != "") {
            this.memos.add(new Memo(this.editText.getText().toString()));
            this.editText.setText("");
//            this.memoDao.insert(new Memo(this.editText.getText().toString()));
            this.memoAdapter.notifyItemInserted(0);
        } else {
            Toast.makeText(this,"Champ Vide",Toast.LENGTH_SHORT).show();
        }
    }

    private void initList()
    {
        for (int i = 0; i < CREATE; i++) {
//                this.memoDao.insert(new Memo("Memo" + i));
            this.memos.add(new Memo ("Memo" + i));
        }
    }
}
