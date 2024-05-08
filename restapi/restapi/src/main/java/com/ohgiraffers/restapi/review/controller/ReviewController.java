package com.ohgiraffers.restapi.review.controller;

import com.ohgiraffers.restapi.common.Criteria;
import com.ohgiraffers.restapi.common.PageDTO;
import com.ohgiraffers.restapi.common.PagingResponseDTO;
import com.ohgiraffers.restapi.common.ResponseDTO;
import com.ohgiraffers.restapi.review.dto.ReviewDTO;
import com.ohgiraffers.restapi.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {
	
	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
	private final ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@Operation(summary = "상품 리뷰 등록 요청", description = "해당 상품 리뷰 등록이 진행됩니다.", tags = { "ReviewController" })
	@PostMapping("/reviews")
    public ResponseEntity<ResponseDTO> insertProductReview(@RequestBody ReviewDTO reviewDTO) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "리뷰 입력 성공",  reviewService.insertProductReview(reviewDTO)));
    }
	
	@Operation(summary = "상품 리뷰 리스트 조회 요청", description = "해당 상품에 등록된 리뷰 리스트 조회가 진행됩니다.", tags = { "ReviewController" })
    @GetMapping("/reviews/{productCode}")
    public ResponseEntity<ResponseDTO> selectReviewListWithPaging(@PathVariable String productCode,
                                                                  @RequestParam(name="offset", defaultValue="1") String offset) {
        log.info("[ReviewController] selectReviewListWithPaging : " + offset);
        log.info("[ReviewController] productCode : " + productCode);
        
        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
        cri.setSearchValue(productCode);	// 해당 상품의 리뷰만을 검색하기 위한 검색 조건
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        int total = (int)reviewService.selectReviewTotal(Integer.valueOf(cri.getSearchValue()));
        
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
        pagingResponseDTO.setData(reviewService.selectReviewListWithPaging(cri));

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }
    
	@Operation(summary = "리뷰 상세 페이지 조회 요청", description = "해당 리뷰의 상세 페이지 조회가 진행됩니다.", tags = { "ReviewController" })
    @GetMapping("/reviews/product/{reviewCode}")
    public ResponseEntity<ResponseDTO> selectReviewDetail(@PathVariable String reviewCode) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공",  reviewService.selectReviewDetail(Integer.valueOf(reviewCode))));
    }
    
	@Operation(summary = "리뷰 수정 요청", description = "리뷰 작성자의 리뷰 수정이 진행됩니다.", tags = { "ReviewController" })
    @PutMapping("/reviews")
    public ResponseEntity<ResponseDTO> updateProductReview(@RequestBody ReviewDTO reviewDTO) {

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "리뷰 수정 성공",  reviewService.updateProductReview(reviewDTO)));
    }
}
