package ecommerce_backend_api.service;

import ecommerce_backend_api.entity.Product;
import ecommerce_backend_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
    return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {

    productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {

    Product existingProduct = productRepository.findById(id).orElse(null);

    if(existingProduct != null) {

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());

        return productRepository.save(existingProduct);
    }

    return null;
}
}