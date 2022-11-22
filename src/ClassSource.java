import java.util.ArrayList;

public class ClassSource {
    private static ArrayList<ClassBox> classSource;

    private ClassSource() {
    }

    public static ArrayList<ClassBox> getClassSource() {
        if (classSource == null) {
            classSource = new ArrayList<>();
        }

        return classSource;
    }

    public void setClassSource(ArrayList<ClassBox> classSource) {
        this.classSource = classSource;
    }

}
