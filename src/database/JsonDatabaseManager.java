package database;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonDatabaseManager {

    private final String usersFile = "users.json";
    private final String coursesFile = "courses.json";

    public JsonDatabaseManager(String s, String s1) {
    }

    // ----------------- Utility Methods ---------------------

    private JSONArray readArray(String file) {
        try {
            File f = new File(file);
            if (!f.exists()) {
                return new JSONArray();
            }

            BufferedReader reader = new BufferedReader(new FileReader(f));
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray arr = new JSONArray(tokener);
            reader.close();
            return arr;

        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private void writeArray(String file, JSONArray array) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(array.toString(4)); // pretty print
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------- USER METHODS -----------------------

    public ArrayList<Student> viewStudents() {
        JSONArray arr = readArray(usersFile);
        ArrayList<Student> list = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            if (obj.getString("role").equalsIgnoreCase("student")) {
                Student s = new Student(
                        obj.getString("userId"),
                        obj.getString("role"),
                        obj.getString("username"),
                        obj.getString("email"),
                        obj.getString("passwordHash"),
                        new ArrayList<Course>(),             // enrolledCourses placeholder
                        obj.optString("progress", "")
                );
                list.add(s);
            }
        }
        return list;
    }

    public ArrayList<Instructor> viewInstructors() {
        JSONArray arr = readArray(usersFile);
        ArrayList<Instructor> list = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            if (obj.getString("role").equalsIgnoreCase("instructor")) {
                Instructor ins = new Instructor(
                        obj.getString("userId"),
                        obj.getString("role"),
                        obj.getString("username"),
                        obj.getString("email"),
                        obj.getString("passwordHash"),
                        new ArrayList<Course>()
                );
                list.add(ins);
            }
        }
        return list;
    }

    public void addUser(User user) {
        JSONArray arr = readArray(usersFile);

        JSONObject obj = new JSONObject();
        obj.put("userId", user.getUserId());
        obj.put("role", user.getRole());
        obj.put("username", user.getUsername());
        obj.put("email", user.getEmail());
        obj.put("passwordHash", user.getPasswordHash());

        if (user instanceof Student s) {
            obj.put("progress", s.getProgress());
        }

        arr.put(obj);
        writeArray(usersFile, arr);
    }

    // ------------------- COURSE METHODS -----------------------

    // Convert Course → JSONObject
    private JSONObject courseToJson(Course c) {
        JSONObject obj = new JSONObject();
        obj.put("courseID", c.getCourseID());
        obj.put("title", c.getTitle());
        obj.put("description", c.getDescription());
        obj.put("instructorID", c.getInstructorID());

        JSONArray lessonsArr = new JSONArray();
        if (c.getLessons() != null) {
            for (Lesson l : c.getLessons()) {
                JSONObject lObj = new JSONObject();
                lObj.put("lessonID", l.getLessonID());
                lObj.put("title", l.getTitle());
                lObj.put("content", l.getContent());
                lObj.put("isCompleted", l.isCompleted());
                lessonsArr.put(lObj);
            }
        }
        obj.put("lessons", lessonsArr);

        return obj;
    }

    public ArrayList<Course> viewCourses() {
        JSONArray arr = readArray(coursesFile);
        ArrayList<Course> list = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            List<Lesson> lessons = new ArrayList<>();
            JSONArray lesArr = obj.getJSONArray("lessons");
            for (int j = 0; j < lesArr.length(); j++) {
                JSONObject l = lesArr.getJSONObject(j);
                lessons.add(new Lesson(
                        l.getString("lessonID"),
                        l.getString("title"),
                        l.getString("content")
                ));
            }

            Course c = new Course(
                    obj.getString("courseID"),
                    obj.getString("title"),
                    obj.getString("description"),
                    obj.getString("instructorID"),
                    lessons
            );
            list.add(c);
        }
        return list;
    }

    public void addCourse(Course c) {
        JSONArray arr = readArray(coursesFile);
        arr.put(courseToJson(c));
        writeArray(coursesFile, arr);
    }

    public void editCourse(Course updated) {
        JSONArray arr = readArray(coursesFile);

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            if (obj.getString("courseID").equals(updated.getCourseID())) {
                arr.put(i, courseToJson(updated)); // replace object
                break;
            }
        }

        writeArray(coursesFile, arr);
    }
}
