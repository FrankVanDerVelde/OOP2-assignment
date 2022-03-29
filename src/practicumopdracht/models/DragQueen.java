package practicumopdracht.models;

import java.io.Serializable;


/**
 * This model defines what a dragqueen looks like and what its data types are
 *
 * @author Frank van der Velde
 */
public class DragQueen implements Serializable {
    transient Show belongsTo;
    String dragName;
    String realName;
    int age;
    String gender;
    String homeTown;
    double salary;
    String bio;

    public DragQueen(Show belongsTo, String dragName, String realName, int age, String gender, String homeTown, double salary, String bio) {
        this.belongsTo = belongsTo;
        this.dragName = dragName;
        this.realName = realName;
        this.age = age;
        this.gender = gender;
        this.homeTown = homeTown;
        this.salary = salary;
        this.bio = bio;
    }

    public String getDragName() {
        return dragName;
    }

    public void setDragName(String dragName) {
        this.dragName = dragName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Show getBelongsTo() {
        return belongsTo;
    }

    // setters
    public void setBelongsTo(Show belongsTo) {
        this.belongsTo = belongsTo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Part of show: ").append(belongsTo.toString()).append('\n');
        sb.append("Drag name: ").append(dragName).append('\n');
        sb.append("Real name: ").append(realName).append('\n');
        sb.append("Age: ").append(age).append('\n');
        sb.append("Gender: ").append(gender).append('\n');
        sb.append("Home town: ").append(homeTown).append('\n');
        sb.append("Salary: ").append(salary).append('\n');
        sb.append("Bio: ").append(bio).append('\n');
        return sb.toString();
    }
}
