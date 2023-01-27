package com.example.api.ramsha.translator.models;

public class ConversationModel {
    public static final int Sender_TYPE=0;
    public static final int Receiver_TYPE=1;

public int type;
    public String convoTextToBeTranslated;
    public String convoTranslatedText;

    public ConversationModel(int type, String text, String text2)
    {
        this.type=type;
        this.convoTextToBeTranslated=text;
        this.convoTranslatedText=text2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getConvoTextToBeTranslated() {
        return convoTextToBeTranslated;
    }

    public void setConvoTextToBeTranslated(String convoTextToBeTranslated) {
        this.convoTextToBeTranslated = convoTextToBeTranslated;
    }

    public String getConvoTranslatedText() {
        return convoTranslatedText;
    }

    public void setConvoTranslatedText(String convoTranslatedText) {
        this.convoTranslatedText = convoTranslatedText;
    }
}
