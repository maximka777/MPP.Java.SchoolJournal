package school.journal.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.journal.controller.exception.ControllerException;
import school.journal.controller.util.ErrorObject;
import school.journal.entity.Clazz;
import school.journal.service.exception.ServiceException;
import school.journal.service.impl.ClassService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/classes")
public class ClassAPIController {

    private static Logger LOGGER = Logger.getLogger(ClassAPIController.class);

    @Autowired
    @Qualifier("ClassService")
    private ClassService classService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(HttpServletRequest request)
            throws ControllerException {
        ResponseEntity resultResponse = null;
        try {
            LOGGER.info("Get Class list controller method");
            resultResponse = new ResponseEntity(classService.read(), HttpStatus.OK);
        } catch (ServiceException exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Can't get Class list"), HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Some critical error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultResponse;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity create(HttpServletRequest request, @RequestBody Clazz clazz)
            throws ControllerException {
        ResponseEntity resultResponse = null;
        try {
            LOGGER.info("Create Class controller method");
            resultResponse = new ResponseEntity(classService.create(clazz), HttpStatus.CREATED);
        } catch (ServiceException exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Can't create class"), HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Some critical error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultResponse;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(HttpServletRequest request, @RequestBody Clazz clazz)
            throws ControllerException {
        ResponseEntity resultResponse = null;
        try {
            LOGGER.info("Update Class controller method");
            resultResponse = new ResponseEntity(classService.update(clazz), HttpStatus.OK);
        } catch (ServiceException exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Can't update class"), HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Some critical error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultResponse;
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(HttpServletRequest request, @PathVariable("classId") int classId)
            throws ControllerException {
        ResponseEntity resultResponse = null;
        try {
            LOGGER.info("Delete Class controller method");
            classService.delete(classId);
            resultResponse = new ResponseEntity(HttpStatus.OK);
        } catch (ServiceException exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Can't delete class"), HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Some critical error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultResponse;
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOne(HttpServletRequest request, @PathVariable("classId") int classId)
            throws ControllerException {
        ResponseEntity resultResponse = null;
        try {
            LOGGER.info("Get Class entity Controller method");
            resultResponse = new ResponseEntity(classService.getOne(classId), HttpStatus.OK);
        } catch (ServiceException exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Error in class getting"), HttpStatus.BAD_REQUEST);
        } catch (Exception exc) {
            LOGGER.error(exc);
            resultResponse = new ResponseEntity(new ErrorObject("Some critical error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resultResponse;
    }
}

