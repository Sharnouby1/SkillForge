package model;

import model.Course;
import model.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

// Certificate.java
public class Certificate {
    private String certificateId;
    private String studentId;
    private String courseId;
    private Date issueDate;
    private String certificateUrl;

    public Certificate(String certificateId, String studentId, String courseId) {
        this.certificateId = certificateId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = new Date();
    }

    // Getters
    public String getCertificateId() { return certificateId; }
    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public Date getIssueDate() { return issueDate; }
    public String getCertificateUrl() { return certificateUrl; }

    public void setCertificateUrl(String url) {
        this.certificateUrl = url;
    }
}

// CertificateGenerator.java
class CertificateGenerator {

    public Certificate generateCertificate(Student student, Course course) {
        String certificateId = "CERT_" + System.currentTimeMillis() + "_" + student.getUserId();
        Certificate certificate = new Certificate(certificateId, student.getUserId(), course.getCourseID());

        // Generate PDF certificate (simplified - you'd use a PDF library like iText)
        String pdfUrl = generatePDFCertificate(certificate, student, course);
        certificate.setCertificateUrl(pdfUrl);

        return certificate;
    }
    public void test() {
        Map<String, Object> certData = new LinkedHashMap<>();
        System.out.println("It works!");
    }

    private String generatePDFCertificate(Certificate cert, Student student, Course course) {
        return "/certificates/" + cert.getCertificateId() + ".pdf";
    }

    public Map<String, Object> generateCertificateData(Certificate cert, Student student, Course course) {
        Map<String, Object> certData = new LinkedHashMap<>();
        certData.put("certificateId", cert.getCertificateId());
        certData.put("studentName", student.getUsername());
        certData.put("studentId", student.getUserId());
        certData.put("courseTitle", course.getTitle());
        certData.put("courseId", course.getCourseID());
        certData.put("issueDate", cert.getIssueDate());
        certData.put("instructor", "Instructor Name");

        return certData;
    }


}
