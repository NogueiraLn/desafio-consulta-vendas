package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<SaleMinDTO> getReport(String minDate, String maxDate) {
		
	return null;	
	}
	
	public List<SummaryMinDTO> getSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minimunDate;
		LocalDate maximumDate;
		
		minimunDate = (minDate.length() <= 0) ? today.minusYears(1L) : LocalDate.parse(minDate) ;
		maximumDate = (maxDate.length() <= 0) ? today : LocalDate.parse(maxDate) ;
		
		
//		List<SummaryMinProjection> summary = repository.searchSaleSummary(minimunDate, maximumDate);
//		List<SummaryMinDTO> listSummaryMinDTO = summary.stream().map(x -> new SummaryMinDTO(x)).collect(Collectors.toList());
		
		List<SummaryMinDTO> listSummaryMinDTO = repository.searchSaleSummary(minimunDate, maximumDate);
		
		
		return listSummaryMinDTO;
	}
	
}
