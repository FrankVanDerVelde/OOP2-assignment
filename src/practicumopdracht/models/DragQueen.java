package practicumopdracht.models;

public class DragQueen {
    Show belongsTo;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Added a new Drag Queen with the values:");
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

    public String getDragName() {
        return dragName;
    }

    public String getRealName() {
        return realName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public double getSalary() {
        return salary;
    }

    public String getBio() {
        return bio;
    }

    public Show getBelongsTo() {
        return belongsTo;
    }

}
