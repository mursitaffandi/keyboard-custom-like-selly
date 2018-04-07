package com.inspiraspace.jokulid.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mursitaffandi on 4/6/18.
 */

public class ListChatapp {
    public Chatapps objectchatapps;

    public ListChatapp() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        objectchatapps = gson.fromJson(Constant.JSON_CHATAPPS, Chatapps.class);
    }

    public class Chatapps {


        @SerializedName("chatapps")
        @Expose
        private List<com.inspiraspace.jokulid.model.ListChatapp.Chatapp_> chatapps = new ArrayList<com.inspiraspace.jokulid.model.ListChatapp.Chatapp_>();

        /**
         * No args constructor for use in serialization
         */
        public Chatapps() {

        }

        /**
         * @param chatapps
         */
        public Chatapps(List<com.inspiraspace.jokulid.model.ListChatapp.Chatapp_> chatapps) {
            super();
            this.chatapps = chatapps;
        }

        public List<com.inspiraspace.jokulid.model.ListChatapp.Chatapp_> getChatapps() {
            return chatapps;
        }

        public ArrayList<Chatapp_> getArryChatapps() {
            ArrayList<Chatapp_> cht = new ArrayList<>();
            for (int i = 0; i < chatapps.size(); i++) {
                cht.add(chatapps.get(i));
            }
            return cht;
        }

        public void setChatapps(List<com.inspiraspace.jokulid.model.ListChatapp.Chatapp_> chatapps) {
            this.chatapps = chatapps;
        }

    }

    public class Chatapp_ {

        @SerializedName("chattapp_id")
        @Expose
        private String chattappId;
        @SerializedName("chattapp_appname")
        @Expose
        private String chattappAppname;
        @SerializedName("chattapp_appfullname")
        @Expose
        private String chattappAppfullname;
        @SerializedName("chattapp_apppackage")
        @Expose
        private String chattappApppackage;

        /**
         * No args constructor for use in serialization
         */
        public Chatapp_() {
        }

        /**
         * @param chattappAppname
         * @param chattappApppackage
         * @param chattappAppfullname
         * @param chattappId
         */
        public Chatapp_(String chattappId, String chattappAppname, String chattappAppfullname, String chattappApppackage) {
            super();
            this.chattappId = chattappId;
            this.chattappAppname = chattappAppname;
            this.chattappAppfullname = chattappAppfullname;
            this.chattappApppackage = chattappApppackage;
        }

        public String getChattappId() {
            return chattappId;
        }

        public void setChattappId(String chattappId) {
            this.chattappId = chattappId;
        }

        public String getChattappAppname() {
            return chattappAppname;
        }

        public void setChattappAppname(String chattappAppname) {
            this.chattappAppname = chattappAppname;
        }

        public String getChattappAppfullname() {
            return chattappAppfullname;
        }

        public void setChattappAppfullname(String chattappAppfullname) {
            this.chattappAppfullname = chattappAppfullname;
        }

        public String getChattappApppackage() {
            return chattappApppackage;
        }

        public void setChattappApppackage(String chattappApppackage) {
            this.chattappApppackage = chattappApppackage;
        }

    }

}
