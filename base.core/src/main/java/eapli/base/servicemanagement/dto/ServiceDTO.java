package eapli.base.servicemanagement.dto;

public class ServiceDTO {

    public final String title;
    public final String serviceCode;
    public final String description;

    public ServiceDTO(String title, String serviceCode, String description) {
        this.title = title;
        this.serviceCode = serviceCode;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Service Code:" + serviceCode + "\n"+
                "\t-title=" + title + "\n"+
                "\t-description=" + description
                ;
    }
}
