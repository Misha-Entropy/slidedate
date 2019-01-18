package phontm.slidedate.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import phontm.slidedate.R;

import static phontm.slidedate.utils.IContract.FINAL_ANSWERS;
import static phontm.slidedate.utils.IContract.QUESTION_1;
import static phontm.slidedate.utils.IContract.QUESTION_2;
import static phontm.slidedate.utils.IContract.QUESTION_3;

public class MainActivity extends AppCompatActivity implements ImagePagerAdapter.AdapterListener{

    private String[] imgUrls;

    private CustomViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        imgUrls = getIntent().getStringArrayExtra("urls");
        ImagePagerAdapter adapter = new ImagePagerAdapter(Arrays.asList(imgUrls), shuffleArray(QUESTION_1),
                shuffleArray(QUESTION_2), shuffleArray(QUESTION_3),  shuffleArray(FINAL_ANSWERS)[new Random().nextInt(3)],this);
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(adapter);
        mPager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.right);
    }

    private String[] shuffleArray(String[] ar)
    {
        Random rnd = new Random();
        if (ar.length > 4) {
            for (int i = ar.length - 2; i > 0; i--) {
                int index = rnd.nextInt(i + 1);
                // Simple swap
                String a = ar[index + 1];
                ar[index + 1] = ar[i+1];
                ar[i+1] = a;
            }
        } else {
            for (int i = ar.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i + 1);
                // Simple swap
                String a = ar[index];
                ar[index] = ar[i];
                ar[i] = a;
            }
        }
        return ar;
    }

    @Override
    public void onButtonClicked(boolean clicked) {
        mPager.setPagingEnabled(clicked);
    }

    @Override
    public void onFinish() {
        finish();
    }
}
