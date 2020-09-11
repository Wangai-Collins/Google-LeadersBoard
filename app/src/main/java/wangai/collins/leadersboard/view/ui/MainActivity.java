package wangai.collins.leadersboard.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import wangai.collins.leadersboard.R;
import wangai.collins.leadersboard.view.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ToolBarSetup();

    }

    public void ToolBarSetup(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = findViewById(R.id.tab_layout);
// Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.hours_fragment));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.skillIq_fragment));
// Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
// Use PagerAdapter to manage page views in fragments.


        // Use PagerAdapter to manage page views in fragments.
// Each page is represented by its own fragment.
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
// Setting a listener for clicks.



        // Setting a listener for clicks.
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                           viewPager.setCurrentItem(tab.getPosition());
                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {
                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {
                                                       }
                                                   });
    }



    public void toSubmitAction(View view) {
        Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
        startActivity(intent);
    }
}
