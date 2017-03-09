package com.arvention.lavish.model;

/**
 * Created by amcan on 3/9/2017.
 */

public class Feedback {

    private final int feedbackID;
    private final int toiletID;
    private final float rating;
    private final String content;

    public Feedback(int feedbackID,
                    int toiletID,
                    float rating,
                    String content) {

        this.feedbackID = feedbackID;
        this.toiletID = toiletID;
        this.rating = rating;
        this.content = content;

    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public int getToiletID() {
        return toiletID;
    }

    public float getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }
}
