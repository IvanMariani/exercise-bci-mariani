package cl.com.bci.mariani.errorhandler;

import lombok.Getter;

import java.util.List;

@Getter
public class ListAPIError {

    private List<APIError> error;

    public ListAPIError (List<APIError> error) {
        this.error = error;
    }
}
