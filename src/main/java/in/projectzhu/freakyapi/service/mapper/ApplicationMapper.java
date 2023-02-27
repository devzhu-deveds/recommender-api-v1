package in.projectzhu.freakyapi.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.projectzhu.freakyapi.domain.Application;
import in.projectzhu.freakyapi.service.dto.ApplicationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Application toEntity(ApplicationDTO applicationDTO) {
        if (applicationDTO == null) {
            return null;
        }

        Application application = new Application();
        application.setId(applicationDTO.getId());
        application.setName(applicationDTO.getName());
        application.setDeveloperEmail(applicationDTO.getDeveloperEmail());
        application.setDeveloperPhoneNumber(applicationDTO.getDeveloperPhoneNumber());

        String keyMetricsStr = null;
        if (application.getKeyMetrics() != null) {
            try {
                keyMetricsStr = objectMapper.writeValueAsString(applicationDTO.getKeyMetrics());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        application.setKeyMetrics(keyMetricsStr);

        application.setProductSource(applicationDTO.getProductSource());
        application.setStatus(applicationDTO.getStatus());
        application.setApiKey(applicationDTO.getApiKey());
        application.setCreatedOn(applicationDTO.getCreatedOn());
        application.setUpdatedOn(applicationDTO.getUpdatedOn());

        return application;
    }

    public ApplicationDTO toDTO(Application application) {
        if (application == null) {
            return null;
        }

        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setName(application.getName());
        applicationDTO.setDeveloperEmail(application.getDeveloperEmail());
        applicationDTO.setDeveloperPhoneNumber(application.getDeveloperPhoneNumber());

        List<String> keyMetrics = null;

        if (application.getKeyMetrics() != null) {
            try {
                keyMetrics = objectMapper.readValue(application.getKeyMetrics(), List.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        applicationDTO.setKeyMetrics(keyMetrics);

        applicationDTO.setProductSource(application.getProductSource());
        applicationDTO.setStatus(application.getStatus());
        applicationDTO.setApiKey(application.getApiKey());
        applicationDTO.setCreatedOn(application.getCreatedOn());
        applicationDTO.setUpdatedOn(application.getUpdatedOn());

        return applicationDTO;
    }

    public List<Application> toEntity(List<ApplicationDTO> applicationDTOList) {

        List<Application> applications = new ArrayList<>();

        if (applicationDTOList.isEmpty()) {
            return applications;
        }

        for (ApplicationDTO applicationDTO : applicationDTOList) {
            Application application = toEntity(applicationDTO);
            applications.add(application);
        }

        return applications;
    }

    public List<ApplicationDTO> toDTO(List<Application> applicationList) {

        List<ApplicationDTO> applicationDTOList = new ArrayList<>();

        if (applicationList.isEmpty()) {
            return applicationDTOList;
        }

        for (Application application : applicationList) {
            ApplicationDTO applicationDTO = toDTO(application);
            applicationDTOList.add(applicationDTO);
        }

        return applicationDTOList;
    }
}
