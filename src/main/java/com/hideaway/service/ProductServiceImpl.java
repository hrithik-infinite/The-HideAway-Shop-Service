package com.hideaway.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hideaway.exception.ProductException;
import com.hideaway.model.Category;
import com.hideaway.model.Product;
import com.hideaway.repository.CategoryRepository;
import com.hideaway.repository.ProductRepository;
import com.hideaway.request.CreateProductRequest;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());
        if (topLevel != null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLevelCategory);
        }
        Category secondLevel = categoryRepository.findByNameAndParant(req.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {

            Category secondLavelCategory = new Category();
            secondLavelCategory.setName(req.getSecondLevelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLavelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParant(req.getThirdLevelCategory(),
                secondLevel.getName());
        if (thirdLevel == null) {

            Category thirdLavelCategory = new Category();
            thirdLavelCategory.setName(req.getThirdLevelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLavelCategory);
        }
        
        Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountPercent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product savedProduct = productRepository.save(product);

		System.out.println("products - " + product);

		return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
    	Product product = findProductById(productId);

		System.out.println("delete product " + product.getId() + " - " + productId);
		product.getSizes().clear();
		productRepository.delete(product);

		return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
            Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

}
