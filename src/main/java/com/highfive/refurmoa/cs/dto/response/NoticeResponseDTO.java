package com.highfive.refurmoa.cs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoticeResponseDTO {
	private String originfilename;	// 첨부할 때 사용된 원파일명
	private String storedfilename;	// 서버에 저장될 때 사용할 파일명
}
