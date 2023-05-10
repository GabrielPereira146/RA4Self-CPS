package main.java.br.unesp.rc.shhc.model;

public class Patient {

    private String firstName;
    private String lastName;
    private float Height;
    private float Weight;
    private int age;
    
    public Patient(String firstName, String lastName, float height, float weight, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        Height = height;
        Weight = weight;
        this.age = age;
    }

    public Patient() {
    }

    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public float getHeight() {
        return Height;
    }
    public void setHeight(float height) {
        Height = height;
    }
    public float getWeight() {
        return Weight;
    }
    public void setWeight(float weight) {
        Weight = weight;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", Height=" + Height + ", Weight="
                + Weight + ", age=" + age + "]";
    }

}
