package com.chuangjia.cyweather.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Melvin on 2017/1/19.
 */

public class Answer {
    public Content content;
    public IntentionVoice intentionVoice;
    public class Content {
        /* {"direction":"东风",
                 "description":"阴",
                 "power":"微风级",
                 "high":10,
                 "location":"上海",
                 "date":"2017-01-17",
                 "location_id":101020100,
                 "low":7},*/
        @SerializedName("direction")
        public String windDir;
        @SerializedName("description")
        public String descriptionS;
        @SerializedName("power")
        public String windPower;
        @SerializedName("high")
        public String tempeatureH;
        @SerializedName("location")
        public String loactionS;
        @SerializedName("date")
        public String dateS;
        @SerializedName("location_id")
        public String location_id;
        @SerializedName("low")
        public String temperatureL;

    }
    public class IntentionVoice {
        /*
        "intention":{"action":"query",
                                "city":"上海",
                                "domain":"weather",
                                "entry_score":5001200,
                                "place":"上海"}

        */
        @SerializedName("action")
        public String actionS;
        @SerializedName("city")
        public String cityS;
        @SerializedName("domain")
        public String domainS;
        @SerializedName("entry_score")
        public String entryCodeS;
        @SerializedName("place")
        public String placeS;
    }
}
