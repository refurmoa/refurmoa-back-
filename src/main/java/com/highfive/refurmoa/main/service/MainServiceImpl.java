package com.highfive.refurmoa.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.admin.repository.BannerRepository;
import com.highfive.refurmoa.entity.Banner;
import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.post.dto.reponse.MainListDTO;
import com.highfive.refurmoa.post.repository.BoardRepository;

@Service
public class MainServiceImpl implements MainService {

	private final BoardRepository repository;
	private final BannerRepository bannerRepository;

	public MainServiceImpl(BoardRepository repository, BannerRepository bannerRepository) {
		this.repository = repository;
		this.bannerRepository = bannerRepository;
	}

	@Override
	public List<List<MainListDTO>> mainList() {
		List<List<MainListDTO>> array = new ArrayList<>();
		array.add(new ArrayList<>());
		array.add(new ArrayList<>());
		array.add(new ArrayList<>());
		Date date = new Date();
		List<Board> best = repository.mainBest(date);
		List<Board> start = repository.mainStart(date);
		List<Board> end = repository.mainEnd(date);
		for (int i = 0; i < 15; i++) {
			if (i < best.size())
				array.get(0).add(new MainListDTO(best.get(i)));
			if (i < start.size())
				array.get(1).add(new MainListDTO(start.get(i)));
			if (i < end.size())
				array.get(2).add(new MainListDTO(end.get(i)));
		}
		return array;
	}
	@Override
	public List<Banner> bannerList(){ return bannerRepository.findRef(); }
	@Override
	public List<Banner> bannerAdList(){ return bannerRepository.findAd(); }
}
