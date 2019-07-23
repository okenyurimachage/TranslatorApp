package com.example.englishswahilitranslater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button_translate;
    Button button_exit;
    RecyclerView recyclerView;
    List<String> wordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.enterWord_et);
        button_translate=findViewById(R.id.translate);
        button_exit=findViewById(R.id.exit);

        wordList = new ArrayList<>();

        recyclerView=findViewById(R.id.translateRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final WordAdapter wordAdapter=new WordAdapter(this,wordList);
        recyclerView.setAdapter(wordAdapter);

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromEnglish=editText.getText().toString();

                if (TextUtils.isEmpty(fromEnglish)){
                    Toast.makeText(MainActivity.this,"Please enter a word",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<String> listOfWords=searchForTransaltion(fromEnglish);
                wordAdapter.addWord(listOfWords);
            }
        });

    }

    private List<String> searchForTransaltion(String word) {

        String line = "";
        List<String> wordList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("db.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((line = bufferedReader.readLine()) != null) {
//                Log.d(TAG, "searchForTransaltion: "+ line);
                List<String> words = Arrays.asList(line.split(","));
//                Log.d(TAG, "searchForTransaltion: " + words);

//
                if (words.size() == 2) {
                    if (word.toLowerCase().equals(words.get(1).toLowerCase())) {
                        wordList.add(words.get(0));
                    }

                }

                if (wordList.size() == 10) {
                    break;
                }

            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordList;
    }
}
