package br.unesp.rc.shhc.SHHCPacientModel.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;


public class Patient {
    
    private int idPaciente;
    private String firstName;
    private String lastName;
    private float height;
    private float weight;
    private int age;
    private String port;
    private List<LineChart<String, Number>> listCharts = new ArrayList<>();
    private List<Pane> listPane = new ArrayList<>();

    public Patient() {
        
    }


    public Patient(String firstName, String lastName, float height, float weight, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
     public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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
        return "Patient [idPaciente=" + idPaciente + ", firstName=" + firstName + ", lastName=" + lastName + ", height="
                + height + ", weight=" + weight + ", age=" + age + ", port=" + port + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + Float.floatToIntBits(height);
        result = prime * result + Float.floatToIntBits(weight);
        result = prime * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Patient other = (Patient) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
            return false;
        if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
            return false;
        if (age != other.age)
            return false;
        return true;
    }

    public List<LineChart<String,Number>> getListCharts() {
        return listCharts;
    }

    public void setListCharts(List<LineChart<String, Number>> listCharts) {
        this.listCharts = listCharts;
    }


    public List<Pane> getListPane() {
        return listPane;
    }


    public void setListPane(List<Pane> listPane) {
        this.listPane = listPane;
    }
    


}
