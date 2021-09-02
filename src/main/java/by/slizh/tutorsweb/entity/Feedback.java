package by.slizh.tutorsweb.entity;

import java.sql.Timestamp;

public class Feedback extends Entity {
    private int feedbackId;
    private String text;
    private Timestamp time;
    private int rating;
    private int userSenderId;
    private int userReceiverId;
}
