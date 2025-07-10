package cl.com.bci.mariani.service;

import cl.com.bci.mariani.dto.ResponseUserActiveDTO;
import cl.com.bci.mariani.dto.UserDTO;

public interface UserService {

    ResponseUserActiveDTO createUser (UserDTO user);
    ResponseUserActiveDTO findUser (String token);


}
