package EIProject;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String id;
    private String password;
    private List<Classroom> classrooms;

    public Student(String id, String password) {
        this.id = id;
        this.password = password;
        this.classrooms = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void enrollClassroom(Classroom classroom) {
        classrooms.add(classroom);
    }
}