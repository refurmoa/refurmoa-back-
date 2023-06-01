package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.cs.dto.request.InquiryReplyDTO;
import com.highfive.refurmoa.cs.dto.response.InquiryDetailDTO;
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
    //목록 상세
    @Override
    public InquiryDetailDTO inquiryDetail(int num) {
    	Inquiry tmp =repository.findByNum(num);
    	return new InquiryDetailDTO(tmp);
    }
    //목록 삭제
    @Override
    public int inquiryDelete(int num) {
    	repository.deleteById(num);
    	return 1;
    }
    //이름 조회
    @Override
    public String findName(String id) {
    	String name=memberRepository.findByMemberId(id).getName();
    	return name;
    }
    //답변 등록
    public int writeReply(InquiryReplyDTO answer) {
    	Inquiry tmp =repository.findByNum(answer.getNum());
    	repository.save(new Inquiry(tmp,answer));
    	return 1;
    }
}
