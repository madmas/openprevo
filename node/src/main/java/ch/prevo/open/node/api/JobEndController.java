package ch.prevo.open.node.api;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.prevo.open.encrypted.model.InsurantInformation;
import ch.prevo.open.node.services.JobEndService;

@RestController
public class JobEndController {

    private static Logger LOGGER = LoggerFactory.getLogger(JobEndController.class);

    private JobEndService jobEndService;

    @Inject
    public JobEndController(JobEndService jobEndService) {
        this.jobEndService = jobEndService;
    }

    @RequestMapping("/job-end")
    public ResponseEntity<SortedSet<InsurantInformation>> getAllJobEndData() {
        try {
            Set<InsurantInformation> allJobEndData = this.jobEndService.getAllJobEndData();
            return ResponseEntity.ok(new TreeSet<>(allJobEndData));
        } catch (Exception e) {
            LOGGER.error("Could not load job end data", e);
            return ResponseEntity.notFound().build();
        }
    }
}
