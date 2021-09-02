package by.slizh.tutorsweb.entity;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Tutor extends User {
    private int tutorId;
    private String phone;
    private String education;
    private String info;
    private BigDecimal pricePerHour;
    private String rejectionMessage;

    public Tutor(){}

    public Tutor(int userId, String firstName, String lastName, String email, String city, InputStream photo, Role role, Status status, int tutorId, String phone, String education, String info, BigDecimal pricePerHour, String rejectionMessage) {
        super(userId, firstName, lastName, email, city, photo, role, status);
        this.tutorId = tutorId;
        this.phone = phone;
        this.education = education;
        this.info = info;
        this.pricePerHour = pricePerHour;
        this.rejectionMessage = rejectionMessage;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getRejectionMessage() {
        return rejectionMessage;
    }

    public void setRejectionMessage(String rejectionMessage) {
        this.rejectionMessage = rejectionMessage;
    }

    public static class TutorBuilder{

        Tutor tutor;

        public TutorBuilder(){
            tutor = new Tutor();
        }

        public TutorBuilder setUserId(int userId) {
            tutor.setUserId(userId);
            return this;
        }

        public TutorBuilder setFirstName(String firstName) {
            tutor.setFirstName(firstName);
            return this;
        }

        public TutorBuilder setLastName(String lastName) {
            tutor.setLastName(lastName);
            return this;
        }

        public TutorBuilder setEmail(String email) {

            tutor.setEmail(email);
            return this;
        }

        public TutorBuilder setCity(String city) {
            tutor.setCity(city);
            return this;
        }

        public TutorBuilder setPhoto(InputStream photo) {
            tutor.setPhoto(photo);
            return this;
        }

        public TutorBuilder setRole(Role role) {
            tutor.setRole(role);
            return this;
        }

        public TutorBuilder setStatus(Status status) {
            tutor.setStatus(status);
            return this;
        }

        public TutorBuilder setTutorId(int tutorId) {
            tutor.setTutorId(tutorId);
            return this;
        }

        public TutorBuilder setPhone(String phone) {
            tutor.setPhone(phone);
            return this;
        }

        public TutorBuilder setEducation(String education) {
            tutor.setEducation(education);
            return this;
        }

        public TutorBuilder setInfo(String info) {
            tutor.setInfo(info);
            return this;
        }

        public TutorBuilder setPricePerHour(BigDecimal pricePerHour) {
            tutor.setPricePerHour(pricePerHour);
            return this;
        }

        public TutorBuilder setRejectionMessage(String rejectionMessage) {
            tutor.setRejectionMessage(rejectionMessage);
            return this;
        }

        public Tutor createTutor() {
            return tutor;
        }
    }
}
