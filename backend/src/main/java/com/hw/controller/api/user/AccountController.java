package com.hw.controller.api.user;

import com.hw.exception.UserNotFoundException;
import com.hw.model.User;
import com.hw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RepositoryRestController
@RequestMapping("api/account")
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCurrentUser(HttpServletRequest request,
                                                PersistentEntityResourceAssembler assembler) {
        try {
            User currentUser = userService.getCurrentUser(request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assembler.toResource(currentUser));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateGeneralInformation(HttpServletRequest request,
                                                PersistentEntityResourceAssembler assembler) {
        try {
            User currentUser = userService.getCurrentUser(request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assembler.toResource(currentUser));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@RequestParam String currentPassword,
                                         @RequestParam String newPassword,
                                         HttpServletRequest request,
                                         PersistentEntityResourceAssembler assembler) {
        try {
            userService.changePassword(request, currentPassword, newPassword);
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


}
