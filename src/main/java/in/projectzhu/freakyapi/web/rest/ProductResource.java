package in.projectzhu.freakyapi.web.rest;


import in.projectzhu.freakyapi.domain.Product;
import in.projectzhu.freakyapi.exceptions.EntityNotFoundException;
import in.projectzhu.freakyapi.service.ProductService;
import in.projectzhu.freakyapi.web.vm.ErrorVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAll(){
        log.info("REST request to get all products");

        return productService.getAll();
    }

    @PostMapping("/products")
    public Product save(@RequestBody Product product) {
        log.info("REST request to save product : {}", product);

        return productService.save(product);
    }

    @PostMapping("/products/bulk")
    public List<Product> retrieveMany(@RequestBody List<Integer> ids) {
        log.info("REST request to get multiple products : {}", ids);

        return productService.getMany(ids);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getOne(@PathVariable Integer id){
        log.info("REST request to find product with id : {}", id);

        Product product = null;
        try {
            product = productService.getOne(id);
        } catch (EntityNotFoundException e) {
            ErrorVM errorVM = new ErrorVM(404, e.getMessage());
            return ResponseEntity.status(404).body(errorVM);
        }

        return ResponseEntity.ok(product);
    }
}
