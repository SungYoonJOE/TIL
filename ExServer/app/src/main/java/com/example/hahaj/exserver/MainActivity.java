package com.example.hahaj.exserver;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView)findViewById(R.id.textView);

        Button btn = (Button)findViewById(R.id.httpTest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://url입력/post"); //AsyncTsk시작
            }
        });
    }

    public class JSONTask extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... urls) {

            try{
                JSONObject jsonObject = new JSONObject(); //JSONObject를 만들고 key, value형식으로 값을 저장
                jsonObject.accumulate("user_id", "android&node Test");
                jsonObject.accumulate("name", "sy");

                HttpURLConnection con = null;
                BufferedReader bufferedReader = null; //받기용 버퍼 생성

                try{
                    URL url = new URL(urls[0]);
                    con = (HttpURLConnection)url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outputstream으로 post 데이터를 넘겨줌
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답받음
                    con.connect();

                    //안드 -> 서버  (서버로 보내기 위해 스트림 만듦)
                    OutputStream outputStream = con.getOutputStream();
                    //보내기용 버퍼를 만들고 넣는다.
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                    writer.write(jsonObject.toString());
                    writer.flush();//전송 후 버퍼를 비운다.
                    writer.close();//버퍼 닫는다.

                    //안드 <- 서버  (서버로부터 데이터를 받음)
                    InputStream inputStream = con.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuffer = new StringBuffer();

                    String line = "";
                    while((line=bufferedReader.readLine())!=null){
                        stringBuffer.append(line);
                    }

                    return stringBuffer.toString();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect(); //연결 해제
                    }
                    try{
                        if(bufferedReader != null){
                            bufferedReader.close(); //버퍼를 닫아줌
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvData.setText(s); //서버로부터 받은 값을 출력해줌
        }
    }
}
