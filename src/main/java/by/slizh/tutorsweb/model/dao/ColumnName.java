package by.slizh.tutorsweb.model.dao;

public class ColumnName {

    private ColumnName() {
    }

    //users table
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    public static final String CITY = "city";
    public static final String PHOTO = "photo";

    //feedback table
    public static final String FEEDBACK_ID = "feedback_id";
    public static final String TEXT = "text";
    public static final String TIME = "time";
    public static final String RATING = "rating";
    public static final String USER_SENDER_ID = "user_sender_id";
    public static final String USER_RECEIVER_ID = "user_receiver_id";

    //tutors table
    public static final String TUTOR_ID = "tutor_id";
    public static final String EDUCATION = "education";
    public static final String INFO = "info";
    public static final String PRICE_PER_HOUR = "price_per_hour";
    public static final String REJECTION_MESSAGE = "rejection_message";

    //subject table
    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "subject_name";
    public static final String type = "type";

    //subject_type table
    public static final String SUBJECT_TYPE_ID = "subject_type_id";
    public static final String SUBJECT_TYPE_NAME = "subject_type_name";

    //tutor_education_images table
    public static final String TUTOR_EDUCATION_IMAGE_ID = "tutor_education_image_id";
    public static final String EDUCATION_IMAGE = "education_image";

    //role table
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";

    //status table
    public static final String STATUS_ID = "status_id";
    public static final String STATUS_NAME = "status_name";

}

