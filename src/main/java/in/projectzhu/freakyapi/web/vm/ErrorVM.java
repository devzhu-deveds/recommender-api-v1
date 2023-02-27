package in.projectzhu.freakyapi.web.vm;


import in.projectzhu.freakyapi.domain.enumerations.APIStatus;
import lombok.Data;


@Data
public class ErrorVM {
    private Integer code;

    private APIStatus status;

    private String statusReason;

    public ErrorVM() {
    }

    public ErrorVM(Integer code, String statusReason) {
        this.status = APIStatus.ERROR;
        this.code = code;
        this.statusReason = statusReason;
    }

    public ErrorVM(Integer code, APIStatus status, String statusReason) {
        this.code = code;
        this.status = status;
        this.statusReason = statusReason;
    }
}
