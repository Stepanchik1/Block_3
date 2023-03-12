package pro.sky.recieptsapp.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Project {
  private String studentName;
  private String title;
  private final Date date;
  private String description;

    public Project(String studentName, String title) {
        this.studentName = studentName;
        this.title = title;
        this.description = "";
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        this.date=date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return "Имя ученика: " + studentName + ",\n" +
         "Название проекта: " + title + ",\n" +
         "Дата создания проекта: " + df.format(date) + ",\n" +
         "Описание: " + description;
    }
}
