package cl.com.bci.mariani.service;

import cl.com.bci.mariani.dto.ResponseUserDTO;
import cl.com.bci.mariani.dto.UserDTO;

public interface UserService {

    ResponseUserDTO createUser (UserDTO user);


}
