package br.unesp.rc.shhc.model;

public class Patient {
    
    private String firstName;
    private String lastName;
    private float height;
    private float weight;
    private int age;

    public Patient() {
    }

    public Patient(String firstName, String lastName, float height, float weight, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.age = age;
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
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", height=" + height + ", weight="
                + weight + ", age=" + age + "]";
    }

}
