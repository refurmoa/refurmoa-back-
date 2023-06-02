package com.highfive.refurmoa.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.post.dto.reponse.MainListDTO;
import com.highfive.refurmoa.post.repository.BoardRepository;

@Service
public class MainServiceImpl implements MainService {
	
	private BoardRepository repository;

	 public MainServiceImpl(BoardRepository repository ) {
	        this.repository = repository;
	 }
	
	@Override
	public  List<MainListDTO>[] mainList(){
			
		List<MainListDTO>[] array = new ArrayList[3];
		array[0] = new ArrayList<>();
		array[1] = new ArrayList<>();
		array[2] = new ArrayList<>();
		Date date =new Date();
		List<Board> best=repository.mainBest();
		List<Board> start=repository.mainStart();
		List<Board> end=repository.mainEnd(date);
		for(int i=0;i<15;i++) {
			if(i<best.size())array[0].add(new MainListDTO(best.get(i)));
			if(i<start.size())array[1].add(new MainListDTO(start.get(i)));
			if(i<end.size())array[2].add(new MainListDTO(end.get(i)));
		}		
		return array;
	}

}
