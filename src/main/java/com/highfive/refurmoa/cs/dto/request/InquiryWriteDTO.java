package com.highfive.refurmoa.cs.dto.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryWriteDTO {
	
	private String id;
	private String inqTitle;
	private String inqCon;
	private String mainImgName;
	private Date inqDate;

}
