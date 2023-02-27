package in.projectzhu.freakyapi.domain;



import in.projectzhu.freakyapi.domain.enumerations.ApplicationStatus;
import in.projectzhu.freakyapi.domain.enumerations.ProductSource;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tbl_applications")
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String developerEmail;

    private String developerPhoneNumber;

    private String keyMetrics;

    @Enumerated(EnumType.STRING)
    private ProductSource productSource;

    private ApplicationStatus status;

    private String apiKey;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    //no args constructor
    public Application() {
    }

    //all args constructor
    public Application(Long id, String name, String developerEmail, String developerPhoneNumber, String keyMetrics, ProductSource productSource, ApplicationStatus status, String apiKey, LocalDate createdOn, LocalDate updatedOn) {
        this.setId(id);
        this.setName(name);
        this.setDeveloperEmail(developerEmail);
        this.setDeveloperPhoneNumber(developerPhoneNumber);
        this.setKeyMetrics(keyMetrics);
        this.setProductSource(productSource);
        this.setStatus(status);
        this.setApiKey(apiKey);
        this.setCreatedOn(createdOn);
        this.setUpdatedOn(updatedOn);
    }


    //getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }

    public String getDeveloperPhoneNumber() {
        return developerPhoneNumber;
    }

    public void setDeveloperPhoneNumber(String developerPhoneNumber) {
        this.developerPhoneNumber = developerPhoneNumber;
    }

    public String getKeyMetrics() {
        return keyMetrics;
    }

    public void setKeyMetrics(String keyMetrics) {
        this.keyMetrics = keyMetrics;
    }

    public ProductSource getProductSource() {
        return productSource;
    }

    public void setProductSource(ProductSource productSource) {
        this.productSource = productSource;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    //to string
    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developerEmail='" + developerEmail + '\'' +
                ", developerPhoneNumber='" + developerPhoneNumber + '\'' +
                ", keyMetrics='" + keyMetrics + '\'' +
                ", productSource=" + productSource +
                ", status=" + status +
                ", apiKey='" + apiKey + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
