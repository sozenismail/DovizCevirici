package com.ismailsozen.dovizcevirici;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button buttonGetir;
    TextView textViewTry,textViewUsd,textViewJpy,textViewChf,textViewCad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCad=findViewById(R.id.textViewCad);
        textViewChf=findViewById(R.id.textViewChf);
        textViewJpy=findViewById(R.id.textViewJpy);
        textViewTry=findViewById(R.id.textViewTry);
        textViewUsd=findViewById(R.id.textViewUsd);

        DownloadData downloadData=new DownloadData();

        try {

            String url="http://data.fixer.io/api/latest?access_key=9c29afb3d8e5ff5c00f163b77445a2ce&format=1";
            downloadData.execute(url);


        }catch (Exception e)
        {


        }



    }

    private class DownloadData extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url=new URL(strings[0]);
                httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);


                int data=inputStream.read();


                // veri var ise
                while (data >0)
                {
                    char character= (char) data;
                    result +=character;

                    data=inputStreamReader.read();

                }



                return result;



            }
            catch (Exception e){

                return null;

            }





        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try
            {
                JSONObject jsonObject=new JSONObject(s);

                String rates=jsonObject.getString("rates");

                JSONObject jsonObject2=new JSONObject(rates);

                String tr=jsonObject2.getString("TRY");
                String chf=jsonObject2.getString("CHF");
                String usd=jsonObject2.getString("USD");
                String jpy=jsonObject2.getString("JPY");
                String cad=jsonObject2.getString("CAD");

                textViewTry.setText("TRY : "+tr);
                textViewCad.setText("CAD : "+cad);
                textViewChf.setText("CHF : "+chf);
                textViewUsd.setText("USD : "+usd);
                textViewJpy.setText("JPY : "+jpy);


            }
            catch (Exception e)
            {



            }

        }
    }

    public void getDeger(View view)
    {



    }

}
