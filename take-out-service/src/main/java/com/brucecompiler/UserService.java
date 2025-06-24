package com.brucecompiler;

import com.brucecompiler.dto.UserLoginDTO;
import com.brucecompiler.entity.User;

/**
 * Service interface for managing user-related operations
 *
 * @author Bruce Compiler
 */
public interface UserService {

    /**
     * Handles user login process.
     *
     * @param userLoginDTO the data transfer object the temp code
     * @return a {@link User} entity object
     */
    User login(UserLoginDTO userLoginDTO);
}
