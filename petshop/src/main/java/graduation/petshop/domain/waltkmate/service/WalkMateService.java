package graduation.petshop.domain.waltkmate.service;

import graduation.petshop.domain.waltkmate.dto.request.WalkMateRequest;
import graduation.petshop.domain.waltkmate.entity.SexType;
import graduation.petshop.domain.waltkmate.entity.WalkMate;
import graduation.petshop.domain.waltkmate.repository.WalkMateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WalkMateService {

    private final WalkMateRepository walkMateRepository;

    @Transactional
    public WalkMate save(WalkMateRequest request) {
        return walkMateRepository.save(request.of());
    }

    public List<WalkMate> getWalkMates(SexType sexType, Integer minAge, Integer maxAge, Boolean hasPet) {
        if (sexType != null && minAge != null && hasPet != null) {
            return walkMateRepository.findAllBySexTypeAndHasPetAndAgeBetween(sexType, hasPet, minAge, maxAge);
        } else {
            return walkMateRepository.findAll();
        }
    }
}
