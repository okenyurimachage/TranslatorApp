package com.example.englishswahilitranslater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {


    private Context mcontext;
    private List<String> wordList;

    public WordAdapter(Context mcontext, List<String> wordList) {
        this.mcontext = mcontext;
        this.wordList = wordList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.words_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

       myViewHolder.words.setText(wordList.get(position));

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView words;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            words = itemView.findViewById(R.id.word);


        }

    }

    public void addWord(List<String> wordList){
        this.wordList=wordList;
        notifyDataSetChanged();
    }



}
