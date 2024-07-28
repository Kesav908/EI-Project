package EIProject;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String details;
    private List<String> submissions;

    public Assignment(String details) {
        this.details = details;
        this.submissions = new ArrayList<>();
    }

    public String getDetails() {
        return details;
    }

    public List<String> getSubmissions() {
        return submissions;
    }

    public void submit(String studentId) {
        submissions.add(studentId);
    }
}
