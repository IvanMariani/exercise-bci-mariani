package cl.com.bci.mariani.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DuplicateUserException extends GeneralException{
    @Builder
    public DuplicateUserException(String[] errorMessagePlaceholders) {
        super(errorMessagePlaceholders);
    }
}
