package school.journal.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.journal.aop.Secured;
import school.journal.controller.exception.ControllerException;
import school.journal.entity.Clazz;
import school.journal.entity.enums.RoleEnum;
import school.journal.service.impl.ClassService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/classes")
public class ClassAPIController extends BaseController<Clazz>{
    private static final Logger LOGGER = Logger.getLogger(ClassAPIController.class);
    private final ClassService classService;

    @Autowired
    public ClassAPIController(@Qualifier("ClassService") ClassService classService) {
        this.classService = classService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Secured(RoleEnum.PUPIL)
    public ResponseEntity getAll(HttpServletRequest request)
            throws ControllerException {
        return read(classService::read, "Can't get full class list", LOGGER);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Secured(RoleEnum.DIRECTOR_OF_STUDIES)
    public ResponseEntity create(HttpServletRequest request, @RequestBody Clazz clazz)
            throws ControllerException {
        return createOrUpdate(classService::create, clazz, "Can't create class", LOGGER);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @Secured(RoleEnum.DIRECTOR_OF_STUDIES)
    public ResponseEntity update(HttpServletRequest request, @RequestBody Clazz clazz)
            throws ControllerException {
        return createOrUpdate(classService::update, clazz, "Can't update class", LOGGER);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
    @ResponseBody
    @Secured(RoleEnum.DIRECTOR_OF_STUDIES)
    public ResponseEntity delete(HttpServletRequest request, @PathVariable("classId") int classId)
            throws ControllerException {
        return delete(classService::delete, classId, "Can't delete class by id", LOGGER);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.GET)
    @ResponseBody
    @Secured(RoleEnum.PUPIL)
    public ResponseEntity getOne(HttpServletRequest request, @PathVariable("classId") int classId)
            throws ControllerException {
        return getOne(classService::getOne, classId, "Can't get class by id", LOGGER);
    }
}

