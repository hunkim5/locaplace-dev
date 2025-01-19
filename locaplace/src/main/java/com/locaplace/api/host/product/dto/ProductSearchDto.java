package com.locaplace.api.host.product.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductSearchDto {
	@Schema(description="호스트아이디")
	private String hostNid;
	@Schema(description="상품명")
	private String productName;
	@Schema(description="가까운 지하철또는 기차")
	private List<String> nearTrain;
	@Schema(description="인기순")
	private String goodsAmtYn;
	@Schema(description="낮은가격순")
	private String useAmountRowYn;
	@Schema(description="높은가격순")
	private String useAmountHightYn;
	@Schema(description="최신등록순")
	private String createDtmYn;
	@Schema(description="반려동물")
	private String animalsYn;
	@Schema(description="다인실")
	private String commonRoomYn;
	@Schema(description="주차가능")
	private String parkingYn;
	@Schema(description="그외지역")
	private String besideAreaYn;
}
