package graduation.petshop.domain.waltkmate.controller;

import graduation.petshop.domain.waltkmate.dto.request.WalkMateRequest;
import graduation.petshop.domain.waltkmate.entity.SexType;
import graduation.petshop.domain.waltkmate.service.WalkMateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/walkmate")
public class WalkMateController {

    private final WalkMateService walkMateService;

    @PostMapping
    public ResponseEntity<?> saveWalkMate(@RequestBody WalkMateRequest request) {
        return ResponseEntity.ok(walkMateService.save(request));
    }

    @GetMapping
    public ResponseEntity<?> getWalkMates(@RequestParam(required = false) SexType sexType,
                                          @RequestParam(required = false) Integer minAge,
                                          @RequestParam(required = false) Integer maxAge,
                                          @RequestParam(required = false) Boolean hasPet) {
        return ResponseEntity.ok(walkMateService.getWalkMates(sexType, minAge, maxAge, hasPet));
    }
}
