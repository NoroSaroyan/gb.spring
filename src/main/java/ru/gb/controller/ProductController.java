package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Product;
import ru.gb.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;


    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findAll() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Product findById(@PathVariable int id) {
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.remove(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public List<Product> save(@RequestBody Product product) {
        repository.add(product);
        return repository.findAll();
    }

}