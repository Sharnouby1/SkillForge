package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private String generatePDFCertificate(Certificate cert, Student student, Course course) {
        // This would generate an actual PDF file
        // For now, return a placeholder URL
        return "/certificates/" + cert.getCertificateId() + ".pdf";
    }

    public String generateJSONCertificate(Certificate cert, Student student, Course course) {
        Map<String, Object> certData = new HashMap<>();
        certData.put("certificateId", cert.getCertificateId());
        certData.put("studentName", student.getUsername());
        certData.put("studentId", student.getUserId());
        certData.put("courseTitle", course.getTitle());
        certData.put("courseId", course.getCourseID());
        certData.put("issueDate", cert.getIssueDate().toString());
        certData.put("instructor", "Instructor Name"); // You'd fetch this

        // Convert to JSON string
        return new Gson().toJson(certData);
    }
}
