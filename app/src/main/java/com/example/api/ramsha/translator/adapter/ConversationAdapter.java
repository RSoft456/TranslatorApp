package com.example.api.ramsha.translator.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.ramsha.translator.R;
import com.example.api.ramsha.translator.avtivities.LanguagesList;
import com.example.api.ramsha.translator.models.ConversationModel;
import com.example.api.ramsha.translator.models.LanguageModel;

import java.util.ArrayList;

public class ConversationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<ConversationModel> conversationSet = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ConversationModel.Receiver_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_conversation_item, parent, false);
                return new receiverViewHolder(view);
            case ConversationModel.Sender_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_conversation_item, parent, false);
                return new senderViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ConversationModel item = conversationSet.get(position);

        if (item != null) {
            switch (item.type) {
                case ConversationModel.Receiver_TYPE:
                    ((receiverViewHolder) holder).receiverText.setText(item.getConvoTextToBeTranslated());
                    ((receiverViewHolder) holder).receiverTranslatedText.setText(item.getConvoTranslatedText());
                    break;
                case ConversationModel.Sender_TYPE:
                    ((senderViewHolder) holder).senderText.setText(item.getConvoTextToBeTranslated());
                    ((senderViewHolder) holder).senderTranslatedText.setText(item.getConvoTranslatedText());
                    break;
            }
        }



    }

    @Override
    public int getItemViewType(int position) {

        switch (conversationSet.get(position).type) {
            case 0:
                return ConversationModel.Sender_TYPE;
            case 1:
                return ConversationModel.Receiver_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return conversationSet.size();
    }
    public void setData(ArrayList<ConversationModel> conversationDataList) {
        this.conversationSet = conversationDataList;
        notifyDataSetChanged();
    }
    public static class senderViewHolder extends RecyclerView.ViewHolder {

        TextView senderText,senderTranslatedText;
        ImageView soundSender;

        public senderViewHolder(View itemView) {
            super(itemView);

            this.senderText = (TextView) itemView.findViewById(R.id.textInConvoSender);
            this.senderTranslatedText = (TextView) itemView.findViewById(R.id.translatedTextInConvoSender);
            this.soundSender = (ImageView) itemView.findViewById(R.id.volumeTextConvoSender);

        }
    }
    public static class receiverViewHolder extends RecyclerView.ViewHolder {

        TextView receiverText,receiverTranslatedText;
        ImageView soundReceiver;
        public receiverViewHolder(View itemView) {
            super(itemView);

            this.receiverText = (TextView) itemView.findViewById(R.id.textInConvoReceiver);
            this.receiverTranslatedText = (TextView) itemView.findViewById(R.id.translatedTextInConvoReceiver);
            this.soundReceiver = (ImageView) itemView.findViewById(R.id.volumeTextConvoReceiver);
        }
    }
}
