package school.journal.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.journal.controller.exception.ControllerException;
import school.journal.entity.Mark;
import school.journal.service.IMarkService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/marks")
public class MarkAPIController extends BaseController<Mark> {

    private static Logger LOGGER = Logger.getLogger(MarkAPIController.class);

    private final IMarkService markService;

    @Autowired
    public MarkAPIController(@Qualifier("MarkService") IMarkService markService) {
        this.markService = markService;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity get(HttpServletRequest request)
            throws ControllerException {
        return read(markService, "Can't get full mark list", LOGGER);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity create(HttpServletRequest request, @RequestBody Mark mark)
            throws ControllerException {
        return create(markService, mark, "Can't create mark", LOGGER);
    }

    @RequestMapping(method = PUT)
    @ResponseBody
    public ResponseEntity update(HttpServletRequest request, @RequestBody Mark mark)
            throws ControllerException {
        return update(markService, mark, "Can't update mark", LOGGER);
    }

    @RequestMapping(value = "/{markId}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(HttpServletRequest request, @PathVariable("markId") int markId)
            throws ControllerException {
        return delete(markService, markId, "Can't delete mark", LOGGER);
    }

    @RequestMapping(value = "/{markId}", method = GET)
    @ResponseBody
    public ResponseEntity getOne(HttpServletRequest request, @PathVariable("markId") int markId)
            throws ControllerException {
        return getOne(markService::getOne, markId, "Can't delete Mark", LOGGER);
    }

    @RequestMapping(method = GET, params = {"subjectId", "classId", "termId"})
    @ResponseBody
    public ResponseEntity getMarksForSubjectInClass(HttpServletRequest request, @RequestParam(value = "subjectId") int subjectId, @RequestParam(value = "classId") int classId, @RequestParam(value = "termId", required = false, defaultValue = 0 + "") int termId)
            throws ControllerException {
        return doResponse(markService::getMarksForSubjectInClass, subjectId, classId, termId, "Cant get marks for subject in class", LOGGER);
    }

    @RequestMapping(method = GET, params = {"classId", "termId"})
    @ResponseBody
    public ResponseEntity getMarksForTermOrder(HttpServletRequest request, @RequestParam(value = "classId") int classId, @RequestParam(value = "termId", required = false, defaultValue = 0 + "") int termId)
            throws ControllerException {
        return doResponse(markService::getMarksForTermOrderInClass, classId, termId, "Can't get marks for class in term", LOGGER);
    }

    @RequestMapping(method = GET, params = {"pupilId", "termId"})
    @ResponseBody
    public ResponseEntity getMarksForPupil(HttpServletRequest request, @RequestParam(value = "pupilId") int pupilId, @RequestParam(value = "termId", required = false, defaultValue = 0 + "") int termId)
            throws ControllerException {
        return doResponse(markService::getMarksForPupil, pupilId, termId, "Cant get Marks for pupil", LOGGER);
    }
}
