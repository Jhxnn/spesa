package com.spesa.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spesa.dtos.RevenueDto;
import com.spesa.models.Revenue;
import com.spesa.repositories.RevenueRepository;

@Service
public class RevenueService {
	
	@Autowired
	RevenueRepository revenueRepository;
	
	public List<Revenue> getUserRevenues(String id) {
		
		return revenueRepository.findByUserId(id);
		
		
	}
	
	public Revenue createRevenue(RevenueDto revenueDto) {
		
		var revenue = new Revenue();
		BeanUtils.copyProperties(revenueDto, revenue);
		return revenueRepository.save(revenue);
	}
	
	public double totalRevenues(String id) {
		return revenueRepository.revenueTotal(id);
	}
	public Revenue updateRevenue(UUID id, Revenue revenue) {
		
		var revenue0 = revenueRepository.findById(id).orElseThrow(() -> new RuntimeException("receita nao encontrada"));
		
		revenue0.setCategory(revenue.getCategory());
		revenue0.setData(revenue.getData());
		revenue0.setDescription(revenue.getDescription());
		revenue0.setValue(revenue.getValue());
		
		return revenueRepository.save(revenue0);
		
	}
	
	public void deleteRevenue(UUID id) {
		
		var user = revenueRepository.findById(id).orElseThrow(() -> new RuntimeException("receita nao encontrada"));
		revenueRepository.delete(user);
	}
	

}
