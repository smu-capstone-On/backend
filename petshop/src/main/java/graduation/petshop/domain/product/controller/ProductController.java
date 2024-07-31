package graduation.petshop.domain.product.controller;

import graduation.petshop.domain.product.dto.request.ProductRequest;
import graduation.petshop.domain.product.entity.ProductSortType;
import graduation.petshop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody final ProductRequest request) {
        log.info("request[{}]", request);
        return ResponseEntity.ok(productService.save(request));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> patch(@RequestBody final ProductRequest request, @PathVariable Long productId) {
        log.info("request[{}]", request);
        return ResponseEntity.ok(productService.patch(productId, request));
    }

    @GetMapping
    public ResponseEntity<?> getBoardList(@RequestParam(required = false) ProductSortType sort,
                                          @RequestParam(required = false) Boolean isReserved) {

        if (sort == null && isReserved == null) {
            return ResponseEntity.ok(productService.getAll());
        } else if (sort == null && isReserved != null) {
            return ResponseEntity.ok(productService.getByReserveStatus(isReserved));
        } else if (sort != null && isReserved == null) {
            return ResponseEntity.ok(productService.getAllBySort(sort));
        } else if (sort != null && isReserved != null) {
            return ResponseEntity.ok(productService.getAllBySortAndReserveStatus(sort, isReserved));
        }
        return ResponseEntity.ok().build();
    }
}
