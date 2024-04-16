package cl.com.bci.mariani.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class GeneralException extends RuntimeException {

    private final String[] errorMessagePlaceholders;

}
