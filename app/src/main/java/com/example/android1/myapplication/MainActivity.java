package com.example.android1.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.android1.myapplication.Config.arrayurl;
import static com.example.android1.myapplication.Config.imageurl;
import static com.example.android1.myapplication.Config.objecturl;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.getobject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitObject();
            }
        });

        this.findViewById(R.id.getarray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitArray();
            }
        });

        this.findViewById(R.id.imagebutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitImage();
            }
        });
    }

    String TAG = "MainActivity";

    void getRetrofitObject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(objecturl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);
        Call<Student> call = service.getStudentDetails();

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                try {


                    Log.i(TAG, response.body().getStudentName());
                    Log.i(TAG, response.body().getStudentMarks());
                    Log.i(TAG, response.body().getStudentId());
                    Toast.makeText(getApplicationContext(),response.body().getStudentName()
                            +response.body().getStudentMarks()
                            +response.body().getStudentId(),Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                    Log.i(TAG, "OnResponse   " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i(TAG, "OnFailure    " + t.getMessage());
            }
        });

    }


    void getRetrofitArray() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(arrayurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

        Call<List<Student>> call = service.getStudentDetails();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Response<List<Student>> response, Retrofit retrofit) {

                try {

                    List<Student> StudentData = response.body();

                    //Log.i(TAG,"Response"+response.toString());

                    for (int i = 0; i < StudentData.size(); i++) {
                        Log.i(TAG, StudentData.get(i).getStudentId());
                        Log.i(TAG, StudentData.get(i).getStudentName());
                        Log.i(TAG, StudentData.get(i).getStudentMarks());

                        Toast.makeText(getApplicationContext(),StudentData.get(i).getStudentName()
                                +StudentData.get(i).getStudentMarks()
                                +StudentData.get(i).getStudentId(),Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    void getRetrofitImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(imageurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitImageAPI service = retrofit.create(RetrofitImageAPI.class);

        Call<ResponseBody> call = service.getImageDetails();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                Log.d(TAG, "Response came from server");
                DownloadImage(response.body());
                Log.d(TAG, "Download Complete");

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "Failure " + t.getMessage());
            }
        });


    }

    private boolean DownloadImage(ResponseBody body) {

        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                Log.d(TAG, getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
                out = new FileOutputStream(getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {
                Log.d("DownloadImage", e.toString());
                return false;
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            int width, height;
            ImageView image = (ImageView) findViewById(R.id.imagedownload);
            Bitmap bMap = BitmapFactory.decodeFile(getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
            width = 2 * bMap.getWidth();
            height = 6 * bMap.getHeight();
            Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false);
            image.setImageBitmap(bMap2);

            return true;

        } catch (IOException e) {
            Log.d("DownloadImage", e.toString());
            return false;
        }
    }


}
