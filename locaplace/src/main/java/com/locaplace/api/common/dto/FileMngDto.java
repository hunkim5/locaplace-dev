package com.locaplace.api.common.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileMngDto {
	@Schema(description="파일관리일련번호")
	private int fileMngNid;
	@Schema(description="파일출처일련번호")
	private int fileGroupNid;
	@Schema(description="파일위치")
	private String filePath;
	@Schema(description="대표사진여부")
	private String mainYn;
	@Schema(description="원본파일명")
	private String orgName;
	@Schema(description="변화파일명")
	private String convertName;
	@Schema(description="생성일")
	private LocalDateTime createDtm;
}
