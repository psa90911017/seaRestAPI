package com.ohgiraffers.restapi.product.service;

import com.ohgiraffers.restapi.common.Criteria;
import com.ohgiraffers.restapi.product.dto.ProductAndCategoryDTO;
import com.ohgiraffers.restapi.product.dto.ProductDTO;
import com.ohgiraffers.restapi.product.entity.Product;
import com.ohgiraffers.restapi.product.entity.ProductAndCategory;
import com.ohgiraffers.restapi.product.repository.ProductAndCategoryRepository;
import com.ohgiraffers.restapi.product.repository.ProductRepository;
import com.ohgiraffers.restapi.util.FileUploadUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);
	private final ProductRepository productRepository;
	private final ProductAndCategoryRepository productAndCategoryRepository;
	private final ModelMapper modelMapper;

	/* 설명. 이미지 파일 저장 경로와 응답용 URL (WebConfig 설정파일 추가하기) */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;
	
	@Autowired
    public ProductService(ProductRepository productRepository, ProductAndCategoryRepository productAndCategoryRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.productAndCategoryRepository = productAndCategoryRepository;
	}

	public int selectProductTotal() {
        log.info("[ProductService] selectProductTotal() Start");
        
        /* 설명. 페이징 처리 결과를 Page 타입으로 반환받음 */
        List<Product> productList = productRepository.findByProductOrderable("Y");
        
        log.info("[ProductService] selectProductTotal() End");
        
        return productList.size();
    }

	public Object selectProductListWithPaging(Criteria cri) {
		log.info("[ProductService] selectProductListWithPaging() Start");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("productCode").descending());
	        
        Page<Product> result = productRepository.findByProductOrderable("Y", paging);
        List<Product> productList = (List<Product>)result.getContent();

        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        
        log.info("[ProductService] selectProductListWithPaging() End");
        
        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	public Object selectProduct(int productCode) {
		log.info("[ProductService] selectProduct() Start");
		 
		Product product = productRepository.findById(productCode).get();
	    product.setProductImageUrl(IMAGE_URL + product.getProductImageUrl());
	     
	    log.info("[ProductService] selectProduct() End");
	     
	    return modelMapper.map(product, Product.class);
	}

	public Object selectSearchProductList(String search) {
		log.info("[ProductService] selectSearchProductList() Start");
        log.info("[ProductService] searchValue : {}", search);
        
        List<Product> productListWithSearchValue = productRepository.findByProductNameContaining(search);

        log.info("[ProductService] productListWithSearchValue : {}", productListWithSearchValue);

        for(int i = 0 ; i < productListWithSearchValue.size() ; i++) {
            productListWithSearchValue.get(i).setProductImageUrl(IMAGE_URL + productListWithSearchValue.get(i).getProductImageUrl());
        }
        
        log.info("[ProductService] selectSearchProductList() End");

        return productListWithSearchValue.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	public Object selectProductListAboutMeal() {
		log.info("[ProductService] selectProductListAboutMeal() Start");

        List<Product> productListAboutMeal = productRepository.findByCategoryCode(1);

        for(int i = 0 ; i < productListAboutMeal.size() ; i++) {
            productListAboutMeal.get(i).setProductImageUrl(IMAGE_URL + productListAboutMeal.get(i).getProductImageUrl());
        }

        log.info("[ProductService] selectProductListAboutMeal() End");

        return productListAboutMeal.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	public Object selectProductListAboutDessert() {
		log.info("[ProductService] selectProductListAboutDessert() Start");

        List<Product> productListAboutDessert = productRepository.findByCategoryCode(2);

        for(int i = 0 ; i < productListAboutDessert.size() ; i++) {
            productListAboutDessert.get(i).setProductImageUrl(IMAGE_URL + productListAboutDessert.get(i).getProductImageUrl());
        }

        log.info("[ProductService] selectProductListAboutDessert() End");

        return productListAboutDessert.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	public Object selectProductListAboutBeverage() {
		log.info("[ProductService] selectProductListAboutBeverage() Start");

        List<Product> productListAboutBeverage = productRepository.findByCategoryCode(3);

        for(int i = 0 ; i < productListAboutBeverage.size() ; i++) {
            productListAboutBeverage.get(i).setProductImageUrl(IMAGE_URL + productListAboutBeverage.get(i).getProductImageUrl());
        }

        log.info("[ProductService] selectProductListAboutBeverage() End");

        return productListAboutBeverage.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
	}

	public int selectProductTotalForAdmin() {
		log.info("[ProductService] selectProductTotalForAdmin() Start");
		
        int result = productRepository.findAll().size();

        log.info("[ProductService] selectProductTotalForAdmin() End");
        
        return result;
	}

	public Object selectProductListWithPagingForAdmin(Criteria cri) {
		log.info("[ProductService] selectProductListWithPagingForAdmin() Start");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("productCode").descending());
	        
        Page<ProductAndCategory> result = productAndCategoryRepository.findAll(paging);
        List<ProductAndCategory> productList = (List<ProductAndCategory>)result.getContent();

        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        
        log.info("[ProductService] selectProductListWithPagingForAdmin() End");
        
		return productList.stream().map(product -> modelMapper.map(product, ProductAndCategoryDTO.class)).collect(Collectors.toList());
	}

	public Object selectProductForAdmin(int productCode) {
		log.info("[ProductService] selectProductForAdmin() Start");
		
		Product product = productRepository.findById(productCode).get();
        product.setProductImageUrl(IMAGE_URL + product.getProductImageUrl());
        
        log.info("[ProductService] selectProductForAdmin() End");
        
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
               
        return productDTO;
	}
	
	@Transactional
	public Object insertProduct(ProductDTO productDTO, MultipartFile productImage) {
        log.info("[ProductService] insertProduct() Start");
        log.info("[ProductService] productDTO : {}", productDTO);
        
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;

        try {
        	
        	/* 설명. util 패키지에 FileUploadUtils 추가 */
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);

            productDTO.setProductImageUrl(replaceFileName);

            log.info("[ProductService] insert Image Name : {}", replaceFileName);

            Product insertProduct = modelMapper.map(productDTO, Product.class);
            
            productRepository.save(insertProduct);
            
            result = 1;
        } catch (Exception e) {
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        
        return (result > 0) ? "상품 입력 성공" : "상품 입력 실패";
	}

	@Transactional
	public Object updateProduct(ProductDTO productDTO, MultipartFile productImage) {
		log.info("[ProductService] updateProduct() Start");
        log.info("[ProductService] productDTO : {}", productDTO);
        
        String replaceFileName = null;
        int result = 0;

        try {
        	
        	/* 설명. update 할 엔티티 조회 */
        	Product product = productRepository.findById(productDTO.getProductCode()).get();
        	String oriImage = product.getProductImageUrl();
            log.info("[updateProduct] oriImage : {}", oriImage);
            
            /* 설명. update를 위한 엔티티 값 수정 */
            product.setCategoryCode(productDTO.getCategoryCode());
            product.setProductName(productDTO.getProductName());
            product.setProductPrice(productDTO.getProductPrice());
            product.setProductOrderable(productDTO.getProductOrderable());
            product.setCategoryCode(productDTO.getCategoryCode());
            product.setProductStock(productDTO.getProductStock());
            product.setProductDescription(productDTO.getProductDescription());
            
            if(productImage != null){
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);
                log.info("[updateProduct] InsertFileName : {}", replaceFileName);
                
                product.setProductImageUrl(replaceFileName);	// 새로운 파일 이름으로 update
                log.info("[updateProduct] deleteImage : {}", oriImage);
                
                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : {}", isDelete);
            } else {
            	
                /* 설명. 이미지 변경 없을 경우 */
            	product.setProductImageUrl(oriImage);
            }

            result = 1;
        } catch (IOException e) {
            log.info("[updateProduct] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] updateProduct End ===================================");
        return (result > 0) ? "상품 업데이트 성공" : "상품 업데이트 실패";
	}

}
