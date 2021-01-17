package com.example.se_implementation;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

public class OrderDetailsActivity extends AppCompatActivity {
    public static final int ADD_PART_REQUEST = 1;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "There should be given an option to add something", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(OrderDetailsActivity.this, AddPartActivity.class);
                startActivityForResult(intent, ADD_PART_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PART_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            String name = data.getStringExtra(AddPartActivity.EXTRA_NAME);
            String category = data.getStringExtra(AddPartActivity.EXTRA_CATEGORY);
            String producer = data.getStringExtra(AddPartActivity.EXTRA_PRODUCER);

            OrderDetailsParts parts = new OrderDetailsParts(name, category, producer);
//            OrderDetailsPartsViewModel.insert(parts);

            Toast.makeText(this, "Part added", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Part wasn't added", Toast.LENGTH_SHORT).show();
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(OrderDetailsActivity mainActivity, FragmentManager fm) { super(fm);}

        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new PartsFragment();
                    break;
                case 1:
                    fragment = new DeadlineFragment();
                    break;
                case 2:
                    fragment = new PersonalNoteFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Parts";
                case 1:
                    return "Deadline";
                case 2:
                    return "Personal Note";
            }
            return null;
        }
    }
}