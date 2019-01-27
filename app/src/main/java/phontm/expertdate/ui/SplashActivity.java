package phontm.expertdate.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import phontm.expertdate.R;
import phontm.expertdate.entities.ServerResponse;
import phontm.expertdate.utils.ServiceManager;

import static phontm.expertdate.utils.IContract.SERVER_ENDPOINT;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private boolean mTimeIsOut = false;
    private boolean outside;
    private String[] imgUrls;
    private String url;
    private Runnable mProceedRunnable = new Runnable() {
        @Override
        public void run() {
            mTimeIsOut = true;
            checkReadyToLaunch();
        }
    };

    OkHttpClient client = new OkHttpClient();

    String exec(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ServiceManager sm = new ServiceManager(this);
        if (!sm.isNetworkAvailable()) {
            Intent intent = new Intent(SplashActivity.this, NoInternetActivity.class);
            startActivity(intent);
            finish();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Random rand = new Random();
                    ServerResponse response = null;
                    try {
                        response = gson.fromJson(exec(SERVER_ENDPOINT
                                /*+ String.valueOf(rand.nextInt() % 2)*/), ServerResponse.class);
                        Log.wtf("RESPONSE", String.valueOf(response.isExternal()) + "||" + response.getImages().length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imgUrls = response.getImages();
                    outside = response.isExternal();
                    if (response.isExternal()) {
                        url = response.getUrl();
                    }
                    mHandler.postDelayed(mProceedRunnable, 2000);
                }
            }).start();
        }
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mProceedRunnable);

        super.onPause();
    }


   public  boolean isStoragePermissionGranted() {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   == PackageManager.PERMISSION_GRANTED) {
               Log.v("LAUNCH", "Permission is granted");
               return true;
           } else {

               Log.v("LAUNCH", "Permission is revoked");
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
               return false;
           }
       } else { //permission is automatically granted on sdk<23 upon installation
           Log.v("LAUNCH", "Permission is granted");
           return true;
       }
   }

    private synchronized void checkReadyToLaunch() {
        if (mTimeIsOut) {
            if (isStoragePermissionGranted()) {
                goToMainActivity();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("LAUNCH","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            goToMainActivity();
        }
    }


    private void goToMainActivity() {
        finish();

        if (outside) {
            Intent intent = new Intent(SplashActivity.this, WebActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("urls", imgUrls);
            startActivity(intent);
        }
    }
}
