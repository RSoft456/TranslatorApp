package com.example.api.ramsha.translator.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.avtivities.LanguagesList;
import com.example.api.ramsha.translator.avtivities.MainActivity;
import com.example.api.ramsha.translator.db.LanguageEntity;
import com.example.api.ramsha.translator.models.LanguageModel;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<LanguageEntity> TranslationList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items, parent, false);
        return new HistoryAdapter.historyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        LanguageEntity item = TranslationList.get(position);
        HistoryAdapter.historyViewHolder VH = (HistoryAdapter.historyViewHolder) holder;
        VH.translatedtext.setText(item.getOutputWord());
        VH.texttobetranslated.setText((item.getInputWord()));
        VH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VH.context, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ILC",item.getInputLanguageCode());
                bundle.putString("ILN",item.getInputLanguageName());
                bundle.putString("TLC",item.getTranslationLanguageCode());
                bundle.putString("TLN",item.getTranslationLanguageName());
                bundle.putString("OW",item.getOutputWord());
                bundle.putString("IW",item.getInputWord());
                bundle.putString("CHECK","true");
                i.putExtras(bundle);
                VH.context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return TranslationList.size();
    }

    public void setData(ArrayList<LanguageEntity> translationDataList) {
        this.TranslationList = translationDataList;
        notifyDataSetChanged();
    }


    public static class historyViewHolder extends RecyclerView.ViewHolder {
        TextView texttobetranslated,translatedtext;
        Context context;
        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            texttobetranslated = itemView.findViewById(R.id.translationFrom);
            translatedtext=itemView.findViewById(R.id.translationTo);
        }
    }
}
