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
import ch.prevo.open.node.services.JobStartService;

@RestController
class JobStartController {

    private static Logger LOGGER = LoggerFactory.getLogger(JobStartController.class);

    private JobStartService jobStartService;

    @Inject
    public JobStartController(JobStartService jobStartService) {
        this.jobStartService = jobStartService;
    }

    @RequestMapping("/job-start")
    public ResponseEntity<SortedSet<InsurantInformation>> getAllJobStartData() {
        try {
            Set<InsurantInformation> jobStartData = this.jobStartService.getAllJobStartData();
            return ResponseEntity.ok(new TreeSet<>(jobStartData));
        } catch (Exception e) {
            LOGGER.error("Could not load job start data", e);
            return ResponseEntity.notFound().build();
        }
    }
}
