package cl.com.bci.mariani.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidMessageException extends GeneralException{
    @Builder
    public ValidMessageException(String[] errorMessagePlaceholders) {
        super(errorMessagePlaceholders);
    }
}
