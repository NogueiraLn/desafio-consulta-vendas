package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

//	@Query(nativeQuery = true, 
//			value = "SELECT SUM(sales.amount), seller.name FROM TB_SALES as sales "
//			+ "INNER JOIN TB_SELLER as seller ON seller.id = sales.seller_id "
//			+ "WHERE sales.date > :minDate "
//			+ "AND sales.date < :maxDate "
//			+ "GROUP BY seller.name ")
//	List<SummaryMinProjection> searchSaleSummary(LocalDate minDate, LocalDate maxDate);
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SummaryMinDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale as obj "
			+ "WHERE obj.date > :minDate "
			+ "AND obj.date < :maxDate "
			+ "GROUP BY obj.seller.name ")
	List<SummaryMinDTO> searchSaleSummary(LocalDate minDate, LocalDate maxDate);
	
}
