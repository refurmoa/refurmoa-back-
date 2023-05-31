package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.cs.dto.response.InquiryListDTO;
import com.highfive.refurmoa.cs.repository.InquiryRepository;
import com.highfive.refurmoa.entity.Inquiry;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.repository.MemberRepository;

@Service
public class InquiryServiceImpl implements InquiryService {
	private InquiryRepository repository;
	private MemberRepository memberRepository;
	
    public InquiryServiceImpl(InquiryRepository repository,MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository=memberRepository;
    }
    //목록 조회
    @Override
    public Page<InquiryListDTO> inquiryList(String id, Pageable pageable){
    	
    	Page<Inquiry> tmp;
    	if(id.equals("admin")) {
    		tmp= repository.findAll(pageable);
    	}
    	else {
	    	Member mem=memberRepository.findByMemberId(id);
	    	tmp=repository.findByMemberId(mem,pageable);	
    	}
    	Page<InquiryListDTO> Page =tmp.map(inquiry->{
			   return new InquiryListDTO(inquiry);	
	 	});
	 	return Page;
    }
}
