package models;
import java.util.ArrayList;

public class Job{
    private String title;
    private String description;
    private String contactInfo;
    private int id;
    private static ArrayList<Job> instances = new ArrayList<Job>();

    public Job(String title, String description, String contactInfo){
        this.title = title;
        this.description = description;
        this.contactInfo = contactInfo;
        instances.add(this);
        this.id = instances.size();
    }
    public String getTitle() {return this.title;}
    public String getDescription() {return this.description;}
    public String getContactInfo() {return this.contactInfo;}
    public int getId() {return this.id;}
    public static ArrayList getAll() {return instances;}
    public static void clearAll() {instances.clear();}
    public static Job findById(int id) {return instances.get(id-1);}
    public void deleteJob(){
        instances.remove(id-1);
    }
}