package cl.com.bci.mariani.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotFoundException extends GeneralException{
    @Builder
    public NotFoundException(String[] errorMessagePlaceholders) {
        super(errorMessagePlaceholders);
    }
}
