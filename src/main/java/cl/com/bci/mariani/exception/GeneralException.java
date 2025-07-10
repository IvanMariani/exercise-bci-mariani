package cl.com.bci.mariani.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class GeneralException extends RuntimeException {

    private final String[] errorMessagePlaceholders;

}
