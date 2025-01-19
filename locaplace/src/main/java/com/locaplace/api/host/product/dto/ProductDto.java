package com.locaplace.api.host.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.locaplace.api.common.dto.FileMngDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductDto {
	@Schema(description="상품일련번호")
	private int productNid;
	@Schema(description="호스트일련번호")
	private int hostNid;
	@Schema(description="상품명")
	private String productName;
	@Schema(description="이용금액")
	private int useAmount;
	@Schema(description="보증금")
	private int deposit;
	@Schema(description="상품소개")
	private String productComment;
	@Schema(description="공간이용방법")
	private String howToUse;
	@Schema(description="위도")
	private BigDecimal latitude;
	@Schema(description="경도")
	private BigDecimal longitude;
	@Schema(description="시도")
	private String sido;
	@Schema(description="시군구")
	private String sigungu;
	@Schema(description="주소")
	private String address;
	@Schema(description="상세주소")
	private String addressDetail;
	@Schema(description="가까운 지하철또는 기차")
	private String nearTrain;
	@Schema(description="게스트 최대인원수")
	private int maxGuestNumber;
	@Schema(description="공개(OPEN),비공개(HIDE)")
	private String productStatus;
	@Schema(description="전용면적")
	private int exclusiveArea;
	@Schema(description="전용면적 단위")
	private String exclusiveAreaUnit;
	@Schema(description="층수")
	private int numberFloors;
	@Schema(description="층수타입: 지상(GROUND),지하(UNDERGROUND)")
	private String floorType;
	@Schema(description="주차가능 여부")
	private String parkingYn;
	@Schema(description="승강기 여부")
	private String elevatorYn;
	@Schema(description="반려동물 가능 여부")
	private String animalsYn;
	@Schema(description="방갯수")
	private int roomQty;
	@Schema(description="화장실갯수")
	private int washroomQty;
	@Schema(description="거실갯수")
	private int livingRoomQty;
	@Schema(description="주방 갯수")
	private int kitchenQty;
	@Schema(description="방타입(INDIVIDUAL,COMMON)")
	private String roomType;
	@Schema(description="복층여부(LAYER,MULTILAYER)")
	private String multipleLayer;
	@Schema(description="거실타입(INDIVIDUAL,COMMON)")
	private String livingRoomType;
	@Schema(description="주방타입(INDIVIDUAL,COMMON)")
	private String kitchenType;
	@Schema(description="화장실타입(INDIVIDUAL,COMMON)")
	private String washroomType;
	@Schema(description="이미지파일 아이디")
	private int imgFileNid;
	@Schema(description="원하는 게스트 단계")
	private int wantedGuestLevel;
	@Schema(description="좋와요 총 갯수")
	private int goodsAmt;
	@Schema(description="삭제여부")
	private String delYn;

	@Schema(description="선택상품 옵션")
	private List<ProductOptionInfoDto> productOptionInfoDtos;
	@Schema(description="사용불가 날짜")
	private List<UnusedDateDto> unusedDateDtos;
	@Schema(description="상품리뷰")
	private List<ReviewDto> reviewDtos;
	@Schema(description="이미지첨부파일")
	private List<FileMngDto> fileMngDtos;
}
