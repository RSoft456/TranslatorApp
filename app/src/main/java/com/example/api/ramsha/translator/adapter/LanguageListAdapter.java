package com.example.api.ramsha.translator.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.avtivities.LanguagesList;
import com.example.api.ramsha.translator.avtivities.MainActivity;
import com.example.api.ramsha.translator.models.LanguageModel;

import java.util.ArrayList;

public class LanguageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<LanguageModel> LanguageList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_list_items, parent, false);
        return new LanguageListAdapter.languageViewHolder(view);


    }
    public void filterList(ArrayList<LanguageModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        LanguageList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        LanguageModel item = LanguageList.get(position);
        LanguageListAdapter.languageViewHolder VH = (LanguageListAdapter.languageViewHolder) holder;
        VH.languageName.setText(item.getLanguageName());
        VH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("country",item.getLanguageName());
                if(LanguagesList.resultCode==2)
                    ((LanguagesList)VH.context).setResult(2,intent);
                else
                    ((LanguagesList)VH.context).setResult(3,intent);
                ((LanguagesList)VH.context).finish();//finishing activity
            }
        });
    }


    @Override
    public int getItemCount() {
        return LanguageList.size();
    }

    public void setData(ArrayList<LanguageModel> studentDataList) {
        this.LanguageList = studentDataList;
        notifyDataSetChanged();
    }


    public static class languageViewHolder extends RecyclerView.ViewHolder {
        TextView languageName;
        Context context;
        public languageViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            languageName = itemView.findViewById(R.id.countryName);
        }
    }
}




