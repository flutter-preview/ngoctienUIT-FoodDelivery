package com.tnt.food_delivery.controller;

import com.tnt.food_delivery.common.JwtUtils;
import com.tnt.food_delivery.model.Product;
import com.tnt.food_delivery.model.request.ProductRequest;
import com.tnt.food_delivery.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tnt.food_delivery.model.Register.getCurrentTime;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<?> getProduct(@RequestParam String name, @RequestParam String status) {
        try {
            return ResponseEntity.ok(productRepository.findItem(name, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không tìm thấy thông tin sản phẩm");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable String id) {
        try {
            return ResponseEntity.ok(productRepository.findById(id).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không tìm thấy thông tin sản phẩm");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createProduct(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody Product product) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            if (userID.equals(product.getRestaurantID()) && role.equals("RESTAURANT")) {
                return ResponseEntity.ok(productRepository.save(product));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền thay đổi thông tin sản phẩm này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id,
            @RequestBody ProductRequest newProduct) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            Product product = productRepository.findById(id).get();
            if (role.equals("RESTAURANT")
                    && userID.equals(product.getRestaurantID())
                    && product.getStatus() != Product.ProductStatus.DELETE) {
                product.setUpdateAt(getCurrentTime());
                if (newProduct.getName() != null) {
                    product.setName(newProduct.getName());
                }
                if (newProduct.getDescription() != null) {
                    product.setDescription(newProduct.getDescription());
                }
                if (newProduct.getImage() != null) {
                    product.setImage(newProduct.getImage());
                }
                if (newProduct.getIsSize() != null) {
                    product.setIsSize(newProduct.getIsSize());
                    if (newProduct.getIsSize()) {
                        product.setM(newProduct.getM());
                        product.setL(newProduct.getL());
                        product.setS(newProduct.getS());
                    } else {
                        product.setPrice(newProduct.getPrice());
                    }
                }
                return ResponseEntity.ok(productRepository.save(product));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền thay đổi thông tin sản phẩm này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/launch")
    public ResponseEntity<?> acceptProduct(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            Product product = productRepository.findById(id).get();
            if (role.equals("ADMIN")
                    || role.equals("RESTAURANT")
                    && userID.equals(product.getRestaurantID())
                    && product.getStatus() == Product.ProductStatus.CANCEL) {
                product.setUpdateAt(getCurrentTime());
                product.setStatus(Product.ProductStatus.LAUNCH);
                return ResponseEntity.ok(productRepository.save(product));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền thay đổi thông tin sản phẩm này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelProduct(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            Product product = productRepository.findById(id).get();
            if (role.equals("ADMIN")
                    || role.equals("RESTAURANT")
                    && userID.equals(product.getRestaurantID())
                    && product.getStatus() == Product.ProductStatus.LAUNCH) {
                product.setUpdateAt(getCurrentTime());
                product.setStatus(Product.ProductStatus.CANCEL);
                return ResponseEntity.ok(productRepository.save(product));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền thay đổi thông tin sản phẩm này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String id) {
        try {
            String content = JwtUtils.decodeJwtToken(token.split(" ")[1]).getSubject();
            String userID = content.split("~")[0];
            String role = content.split("~")[1];
            Product product = productRepository.findById(id).get();
            if (role.equals("ADMIN")
                    || role.equals("RESTAURANT")
                    && userID.equals(product.getRestaurantID())) {
                product.setUpdateAt(getCurrentTime());
                product.setStatus(Product.ProductStatus.DELETE);
                return ResponseEntity.ok(productRepository.save(product));
            }
            return ResponseEntity.badRequest().body("Bạn không có quyền thay đổi thông tin sản phẩm này");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
