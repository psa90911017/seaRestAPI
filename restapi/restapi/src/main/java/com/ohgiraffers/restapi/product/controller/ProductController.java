package com.ohgiraffers.restapi.product.controller;


import com.ohgiraffers.restapi.common.Criteria;
import com.ohgiraffers.restapi.common.PageDTO;
import com.ohgiraffers.restapi.common.PagingResponseDTO;
import com.ohgiraffers.restapi.common.ResponseDTO;
import com.ohgiraffers.restapi.product.dto.ProductDTO;
import com.ohgiraffers.restapi.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = "상품 리스트 조회 요청", description = "상품 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products")
	public ResponseEntity<ResponseDTO> selectProductListWithPaging(
			@RequestParam(name = "offset", defaultValue = "1") String offset) {

		/* 설명. common 패키지에 Criteria, PageDTO, PagingResponseDTO 추가 */
		log.info("[ProductController] selectProductListWithPaging : " + offset);

		int total = productService.selectProductTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
		/* 목차. 1. offset의 번호에 맞는 페이지에 뿌릴 Product들 */
		pagingResponseDTO.setData(productService.selectProductListWithPaging(cri));
		
		/* 목차. 2. PageDTO(Criteria(보고싶은 페이지, 한페이지에 뿌릴 개수), 전체 상품 수) : 화면에서 페이징 처리에 필요한 개념들을 더 계산해서 추출함 */
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
	}
	
	@Operation(summary = "상품 상세 조회 요청", description = "상품의 상세 페이지 처리가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/{productCode}")
    public ResponseEntity<ResponseDTO> selectProductDetail(@PathVariable int productCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProduct(productCode)));
    }
	
	@Operation(summary = "검색 상품 리스트 조회 요청", description = "검색어에 해당되는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/search")
    public ResponseEntity<ResponseDTO> selectSearchProductList(@RequestParam(name="s", defaultValue="all") String search) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectSearchProductList(search)));
    }
	
	@Operation(summary = "식사 상품 리스트 조회 요청", description = "식사 카테고리에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/meals")
    public ResponseEntity<ResponseDTO> selectProductListAboutMeal() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutMeal()));
    }
	
	@Operation(summary = "디저트 상품 리스트 조회 요청", description = "디저트 카테고리에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/dessert")
    public ResponseEntity<ResponseDTO> selectProductListAboutDessert() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutDessert()));
    }

	@Operation(summary = "음료 상품 리스트 조회 요청", description = "음료 카테고리에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products/beverage")
    public ResponseEntity<ResponseDTO> selectProductListAboutBeverage() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutBeverage()));
    }
    
	@Operation(summary = "관리자 페이지 상품 리스트 조회 요청", description = "관리자 페이지에서 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products-management")
    public ResponseEntity<ResponseDTO> selectProductListWithPagingForAdmin(@RequestParam(name="offset", defaultValue="1") String offset) {
        log.info("[ProductController] selectProductListWithPagingForAdmin : " + offset);
        int total = productService.selectProductTotalForAdmin();
        
        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(productService.selectProductListWithPagingForAdmin(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }
    
	@Operation(summary = "관리자 페이지 상품 상세 페이지 조회 요청", description = "관리자 페이지에서 상품 상세 페이지 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products-management/{productCode}")
    public ResponseEntity<ResponseDTO> selectProductDetailForAdmin(@PathVariable int productCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProductForAdmin(productCode)));
    }
    
    /* 
     * 설명. 파일 업로드 시에 static 폴더의 파일이 바로 적용되지 않을 때,
     *  (이미지가 정상적으로 업로드 되었음에도 불구하고 이미지를 읽어오지 못하는 경우)
     *  : Window -> Preferences의 General -> Workspace에서 맨 위의 Refresh using native hooks or polling 체크할 것
     */
	@Operation(summary = "상품 등록 요청", description = "해당 상품 등록이 진행됩니다.", tags = { "ProductController" })
    @PostMapping(value = "/products")
    public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductDTO productDTO, MultipartFile productImage) {
    	
    	return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 입력 성공",  productService.insertProduct(productDTO, productImage)));
    }
    
	@Operation(summary = "상품 수정 요청", description = "해당 상품 수정이 진행됩니다.", tags = { "ProductController" })
    @PutMapping(value = "/products")
    public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute ProductDTO productDTO, MultipartFile productImage) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 수정 성공",  productService.updateProduct(productDTO, productImage)));
    }
}
