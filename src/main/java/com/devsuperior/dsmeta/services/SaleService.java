package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
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

	public List<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate minimunDate = 
				(minDate.length() <= 0) ? today.minusYears(1L) : LocalDate.parse(minDate);
		LocalDate maximumDate = 
				(maxDate.length() <= 0) ? today : LocalDate.parse(maxDate);

		
//		List<ReportMinProjection> report = repository.searchSaleReport(minimunDate, maximumDate, name);
//		List<SaleMinDTO> listReportMinDTO = report.stream().map(x -> new SaleMinDTO(x)).collect(Collectors.toList());

		
		List<SaleMinDTO> listSaleDTO = repository.searchSaleReport(minimunDate, maximumDate, name, pageable);
		
		return listSaleDTO;
		
	}

	public List<SummaryMinDTO> getSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate minimunDate = 
				(minDate.length() <= 0) ? today.minusYears(1L) : LocalDate.parse(minDate);
		LocalDate maximumDate = 
				(maxDate.length() <= 0) ? today : LocalDate.parse(maxDate);

//		List<SummaryMinProjection> summary = repository.searchSaleSummary(minimunDate, maximumDate);
//		List<SummaryMinDTO> listSummaryMinDTO = summary.stream().map(x -> new SummaryMinDTO(x)).collect(Collectors.toList());

		List<SummaryMinDTO> listSummaryMinDTO = repository.searchSaleSummary(minimunDate, maximumDate);

		return listSummaryMinDTO;
	}

}
