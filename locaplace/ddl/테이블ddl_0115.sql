CREATE TABLE `user` (
	`user_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '사용자일련번호',
	`email`	varchar(50)	NOT NULL	COMMENT '이메일',
	`password`	text	NOT NULL	COMMENT '패스워드',
	`need_password_change_yn`	varchar(2)	NOT NULL	DEFAULT 'N'	COMMENT '비밀번호수정필요여부',
	`verified_yn`	varchar(2)	NOT NULL	DEFAULT 'N'	COMMENT '사용자인증여부',
	`name`	varchar(20)	NULL	COMMENT '실명',
	`phone_number`	varchar(20)	NULL	COMMENT '휴대전화번호',
	`gender`	char(1)	NULL	COMMENT '성별',
	`nickname`	varchar(20)	NOT NULL	COMMENT '닉네임',
	`marketing_yn`	char(1)	NOT NULL	DEFAULT 'N'	COMMENT '마케팅정보이용동의여부',
	`location_yn`	char(1)	NOT NULL	DEFAULT 'N'	COMMENT '위치정보이용동의여부',
	`kyc_level`	tinyint	NULL	COMMENT '인증단계 1,2,3단계',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`user_nid`)
)COMMENT='사용자';

CREATE TABLE `user_detail` (
	`user_detail_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '사용자상세일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '사용자일련번호',
	`user_type`	varchar(5)	NOT NULL	COMMENT 'GUEST,HOST,ADMIN',
	`introduction`	text	NULL	COMMENT '자기소개',
	`foreign_yn`	char(1)	NOT NULL	DEFAULT 'N'	COMMENT '외국인여부',
	`resident_number`	varchar(100)	NULL	COMMENT '주민등록번호 또는 외국인 등록번호',
	`passport_number`	varchar(100)	NULL	COMMENT '여권번호',
	`authentication_step`	varchar(20)	NULL	COMMENT '마지막인증과정',
	`profile_img`	text	NULL,
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`user_detail_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='사용자 상세';

CREATE TABLE `user_oauth` (
	`user_oauth_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '사용자소셜연동 일련번호',
	`user_nid`	int unsigned 	NOT NULL	COMMENT '유저NID',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NOT NULL	COMMENT '수정일시',	
	PRIMARY KEY (`user_oauth_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='사용자소셜연동';

CREATE TABLE `user_verification` (
	`user_verification_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '유저인증정보일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '유저NID',
	`email_verification_token`	text	NOT NULL	COMMENT '사용자이메일인증토큰',
	`email_verification_expired_dtm`	datetime	NULL	COMMENT '이메일토큰인증유효기간',
	`password_change_token`	text	NULL	COMMENT '암호변경인증토큰',
	`password_change_verification_yn`	char(1)	NULL	COMMENT '암호변경이메일인증여부',
	`password_verification_expired_dtm`	datetime	NULL	COMMENT '암호변경토큰인증유효기간',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`user_verification_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='유저인증정보';

CREATE TABLE `common_code` (
	`common_code_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '공통코드 일련번호',
	`classification_code`	varchar(50)	NOT NULL	COMMENT '분류코드키',
	`code`	varchar(50)	NOT NULL	COMMENT '코드',
	`code_name`	varchar(100)	NULL	COMMENT '코드명',
	`order`	smallint	NULL	COMMENT '순서',
	`use_yn`	char(1)	NULL	DEFAULT 'Y'	COMMENT '사용여부',
	`etc_val1`	varchar(100)	NULL	COMMENT '기타값1',
	`etc_val2`	varchar(100)	NULL	COMMENT '기타값2',
	`etc_val3`	varchar(100)	NULL	COMMENT '기타값3',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`common_code_nid`),
	KEY `classification_code_IDX` (`classification_code`) USING BTREE
)COMMENT='공통코드';

CREATE TABLE `classification_code` (
	`classification_code`	varchar(50)	NOT NULL	COMMENT '분류코드키',
	`classification_name`	varchar(100)	NULL	COMMENT '분류코드명',
	`use_yn`	char(1)	NOT NULL	DEFAULT 'Y'	COMMENT '사용여부',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`classification_code`)
)COMMENT='공통코드분류';

CREATE TABLE `product` (
	`product_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '상품일련번호',
	`host_nid`	int unsigned	NOT NULL	COMMENT '호스트일련번호',
	`product_name`	varchar(50)	NOT NULL	COMMENT '상품명',
	`user_fee`	smallint	NOT NULL	COMMENT '이용금액',
	`deposit`	smallint	NOT NULL	COMMENT '보증금',
	`product_comment`	text	NULL	COMMENT '상품소개',
	`how_to_use`	text	NULL	COMMENT '공간이용방법',
	`latitude`	DECIMAL(9,6)	NULL	COMMENT '위도',
	`longitude`	DECIMAL(10,6)	NULL	COMMENT '경도',
	`sido`	varchar(10)	NULL	COMMENT '시도',
	`sigungu`	varchar(10)	NULL	COMMENT '시군구',
	`address`	varchar(50)	NULL	COMMENT '주소',
	`address_detail`	varchar(50)	NULL	COMMENT '상세주소',
	`near_train`	varchar(10)	NULL	COMMENT '가까운 지하철또는 기차',
	`max_guest_number`	tinyint	NULL	COMMENT '게스트 최대인원수',
	`product_status`	varchar(10)	NULL	COMMENT '공개(OPEN),비공개(HIDE)',
	`exclusive_area`	tinyint	NULL	COMMENT '전용면적',
	`exclusive_area_unit`	varchar(10)	NULL	COMMENT '전용면적 단위',
	`number_floors`	tinyint	NULL	COMMENT '층수',
	`floor_type`	varchar(10)	NULL	COMMENT '층수타입: 지상(GROUND),지하(UNDERGROUND)',
	`parking_yn`	char(1)	NULL	DEFAULT 'N'	COMMENT '주차가능 여부',
	`elevator_yn`	char(1)	NULL	DEFAULT 'N'	COMMENT '승강기 여부',
	`animals_yn`	char(1)	NULL	DEFAULT 'N'	COMMENT '반려동물 가능 여부',
	`room_qty`	tinyint	NULL	COMMENT '방갯수',
	`washroom_qty`	tinyint	NULL	COMMENT '화장실갯수',
	`living_room_qty`	tinyint	NULL	COMMENT '거실갯수',
	`kitchen_qty`	tinyint	NULL	COMMENT '주방 갯수',
	`room_type`	varchar(10)	NULL	COMMENT '방타입(INDIVIDUAL,COMMON)',
	`multiple_layer`	varchar(10)	NULL	COMMENT '복층여부(LAYER,MULTI_LAYER)',
	`living_room_type`	varchar(10)	NULL	COMMENT '거실타입(INDIVIDUAL,COMMON)',
	`kitchen_type`	varchar(10)	NULL	COMMENT '주방타입(INDIVIDUAL,COMMON)',
	`washroom_type`	varchar(10)	NULL	COMMENT '화장실타입(INDIVIDUAL,COMMON)',
	`img_file_nid`	int	NULL	COMMENT '이미지파일 아이디',
	`wanted_guest_level`	tinyint	NOT NULL	COMMENT '원하는 게스트 단계',
	`del_yn`	char(1)	NOT NULL DEFAULT 'N'	COMMENT '삭제여부',
	`create_dtm`	datetime	NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`product_nid`),
	KEY `host_nid_IDX` (`host_nid`) USING BTREE
)COMMENT='상품';

CREATE TABLE `host` (
	`host_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '호스트일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '사용자일련번호',
	`introduce_me`	text	NULL	COMMENT '나의소개',
	`bank_receives`	varchar(50)	NOT NULL	COMMENT '대금수령은행',
	`account_name`	varchar(20)	NOT NULL	COMMENT '계좌소유자명',
	`receipt_type`	varchar(50)	NULL	COMMENT '영수증발행타입(CASH,TAX)현금영수증,세금계산서',
	`cash_receipt`	varchar(50)	NULL	COMMENT '현금영수증 정보',
	`business_number`	varchar(20)	NULL	COMMENT '사업자번호',
	`business_name`	varchar(20)	NULL	COMMENT '사업자명',
	`ceo_name`	varchar(10)	NULL	COMMENT '대표자명',
	`opening_day`	date	NULL	COMMENT '개업년월',
	`business_email`	varchar(20)	NULL	COMMENT '사업자 이메일',
	`kyc_level`	tinyint	NULL	COMMENT '인증레벨 1,2,3단계',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	`update_dtm`	datetime	NULL	COMMENT '수정일시',
	PRIMARY KEY (`host_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='호스트';

CREATE TABLE `product_option_info` (
	`product_option_info_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '상품기본정보일련번호',
	`product_nid`	int unsigned	NOT NULL	COMMENT '상품일련번호',
	`classification_code`	varchar(50)	NOT NULL	COMMENT '분류코드키',
	`common_code_nid`	int unsigned	NOT NULL	COMMENT '공통코드 일련번호',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일시',
	PRIMARY KEY (`product_option_info_nid`),
	KEY `product_nid_IDX` (`product_nid`) USING BTREE
);

CREATE TABLE `reservation` (
	`reservation_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '예약일련번호',
	`product_nid`	int unsigned NOT NULL	COMMENT '상품일련번호',
	`host_nid`	int unsigned	NOT NULL	COMMENT '호스트일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '게스트일련번호',
	`contract_start_dtm`	date	NOT NULL	COMMENT '계약 시작일(입실시작일과동일)',
	`contract_end_dtm`	date	NOT NULL	COMMENT '계약종료일(퇴실일과동일)',
	`guest_count`	tinyint	NOT NULL	COMMENT '게스트명수',
	`contract_type`	varchar(10)	NOT NULL	DEFAULT 'NORMAL'	COMMENT '일반:NORMAL,정기:REGULAR',
	`contract_status`	varchar(10)	NOT NULL	DEFAULT 'WAIT'	COMMENT '공통코드 reservation_status 참조',
	`rev_req_dtm`	datetime	NULL	DEFAULT now()	COMMENT '예약요청일(예약일과동일)',
	`approved`	datetime	NULL	COMMENT '승인완료일시',
	`approval_cancel_dtm`	datetime	NULL	COMMENT '승인취소일시',
	`payment_dtm`	datetime	NULL	COMMENT '결제완료일시',
	`payment_amt`	smallint	NULL	COMMENT '결제금액',
	`payback_amt`	smallint	NULL	COMMENT '환불금액',
	`payment_type`	varchar(10)	NULL	COMMENT '카드(CARD),간편(SIMPLE),무통장(BANK)',
	`bank_name`	varchar(20)	NULL	COMMENT '은행명',
	`account_no`	varchar(20)	NULL	COMMENT '계좌번호',
	`account_owner`	varchar(20)	NULL	COMMENT '계좌소유자명',
	`ext_contract_yn`	char(1)	NULL	DEFAULT 'N'	COMMENT '계약연장여부',
	`deposit_return_yn`	char(1)	NULL	DEFAULT 'N'	COMMENT '보증금반환여부',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	`update_dtm`	datetime	NULL	COMMENT '수정일',
	PRIMARY KEY (`reservation_nid`),
	KEY `product_nid_IDX` (`product_nid`) USING BTREE
)COMMENT='예약';

CREATE TABLE `review` (
	`review_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '리뷰일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '사용자일련번호',
	`reservation_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '예약일련번호',
	`review_content`	text	NULL	COMMENT '리뷰 내용',
	`review_score`	decimal(2,1)	NULL	COMMENT '만족도 점수',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	PRIMARY KEY (`review_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='리뷰';

CREATE TABLE `chatting_room` (
	`chatting_room_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '채팅룸일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '채팅개설자',
	`source_nid`	int	NULL	COMMENT '출처일련번호',
	`room_type`	varchar(20)	NULL	COMMENT '룸타입(1:1문의:ONE,예약상품문의:REV,상품문의:PRD)',
	`room_title`	varchar(200)	NULL	COMMENT '방제목',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '채팅방 개설일',
	PRIMARY KEY (`chatting_room_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='채팅룸';

CREATE TABLE `chat_participant` (
	`chatting_room_nid`	int unsigned	NOT NULL	COMMENT '채팅룸일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '사용자일련번호',
	PRIMARY KEY (`chatting_room_nid`,`user_nid`)
)COMMENT='채팅참여자';

CREATE TABLE `chatting` (
	`chatting_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '채팅일련번호',
	`chatting_room_nid`	int unsigned 	NOT NULL	COMMENT '채팅룸일련번호',
	`user_nid`	int unsigned 	NOT NULL	COMMENT '사용자일련번호',
	`message`	text	NULL	COMMENT '메세지',
	`chatting_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '채팅일시',
	PRIMARY KEY (`chatting_nid`),
	KEY `chatting_room_nid_IDX` (`chatting_room_nid`) USING BTREE,
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='채팅';

CREATE TABLE `select_product_mng` (
	`select_product_mng`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '선택상품관리일련번호',
	`product_nid`	int unsigned	NOT NULL	COMMENT '상품일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '사용자일련번호',
	`select_gubun`	varchar(10)	NOT NULL	COMMENT '좋와요:GOOD  관심상품:INTEREST',
	`select_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '선택일시',
	PRIMARY KEY (`select_product_mng`),
	KEY `product_nid_user_nid_IDX` (`product_nid`,`user_nid`) USING BTREE
)COMMENT='선택상품관리';

CREATE TABLE `board` (
	`board_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '게시판일련번호',
	`board_gubun`	varchar(10)	NOT NULL	COMMENT '게시판구분(자유게시판:FREE,공지사항:NOTICE,FAQ:FAQ)',
	`title`	varchar(200)	NULL	COMMENT '제목',
	`content`	text	NULL	COMMENT '내용',
	`file_uuid`	varchar(52)	NULL	COMMENT '파일 uuid',
	`search_cnt`	smallint	NULL	COMMENT '조회수',
	`parent_nid`	int	NULL	COMMENT '부모글',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	`update_dtm`	datetime	NULL	COMMENT '수정일',
	PRIMARY KEY (`board_nid`),
	KEY `board_gubun_IDX` (`board_gubun`) USING BTREE
)COMMENT='게시판';

CREATE TABLE `file_mng` (
	`file_mng_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '파일관리일련번호',
	`file_group_nid`	int	NULL	COMMENT '파일출처일련번호',
	`file_path`	VARCHAR(255)	NULL COMMENT '파일위치',
	`main_yn`	CHAR(1)	NULL COMMENT '대표사진여부',
	`org_name`	VARCHAR(100)	NULL COMMENT '원본파일명',
	`convert_name`	VARCHAR(200)	NULL COMMENT '변화파일명',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	PRIMARY KEY (`file_mng_nid`),
	KEY `file_group_nid_IDX` (`file_group_nid`) USING BTREE
)COMMENT='파일관리';

CREATE TABLE `user_card` (
	`user_card_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '사용자카드일련번호',
	`user_nid`	int unsigned	NOT NULL	COMMENT '사용자일련번호',
	`card_code`	varchar(10)	NULL	COMMENT '카드사(코드화필요)',
	`card_gubun`	varchar(10)	NULL	COMMENT '개일:PERSONAL,법인:CORPORATE',
	`birthday`	date	NULL	COMMENT '생년월일',
	`business_number`	varchar(20)	NULL	COMMENT '사업자번호',
	`card_number`	varchar(30)	NULL	COMMENT '카드번호',
	`validit`	varchar(10)	NULL	COMMENT '유효기간',
	`card_password`	varchar(30)	NULL	COMMENT '카드비밀번호',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	`update_dtm`	datetime	NULL	COMMENT '수정일',
	PRIMARY KEY (`user_card_nid`),
	KEY `user_nid_IDX` (`user_nid`) USING BTREE
)COMMENT='사용자카드';

CREATE TABLE `unused_date` (
	`unused_date_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '사용불가 날짜일련번호',
	`product_nid`	int unsigned	NOT NULL	COMMENT '상품일련번호',
	`unused_start_dt`	date	NOT NULL	COMMENT '사용불가 시작날짜',
	`unused_end_dt`	date	NOT NULL	COMMENT '사용불가 종료날짜',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	PRIMARY KEY (`unused_date_nid`),
	KEY `user_nid_IDX` (`product_nid`) USING BTREE
)COMMENT='상품사용불가 날짜';

CREATE TABLE `jwt_token` (
	`jwt_token_nid`	int unsigned AUTO_INCREMENT	NOT NULL	COMMENT '',
	`user_nid`	int unsigned	NULL	COMMENT '',
	`tokenHash`	varchar(500)	NULL	COMMENT '',
	`expireDtm`	datetime	NULL	COMMENT '',
	`create_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '생성일',
	PRIMARY KEY (`jwt_token_nid`)
)COMMENT='';