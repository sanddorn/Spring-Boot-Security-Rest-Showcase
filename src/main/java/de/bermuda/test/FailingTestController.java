package de.bermuda.test;

import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

;

@Controller
@RequestMapping(path="/failing")
public class FailingTestController implements MyPasswordApi {

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/test")
    @Secured("ROLE_USER")
    public ResponseEntity<GetEntity> getMethod() {
        var getEntity = new GetEntity();
        getEntity.part1="Part1";
        getEntity.part2="Part2";
        return ResponseEntity.ok(getEntity);
    }


    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/checkPassword",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<List<String>> checkPassword(@RequestBody(required = false) String body) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    public static class GetEntity {
        private String part1;
        private String part2;

        public String getPart1() {
            return part1;
        }

        public void setPart1(String part1) {
            this.part1 = part1;
        }

        public String getPart2() {
            return part2;
        }

        public void setPart2(String part2) {
            this.part2 = part2;
        }
    }
}
