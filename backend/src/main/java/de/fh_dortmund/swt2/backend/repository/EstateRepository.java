package de.fh_dortmund.swt2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.fh_dortmund.swt2.backend.model.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    
    @Query("SELECT e from Estate e WHERE " + 
    "(:city IS NULL or e.address.city = :city) AND " + 
    "(:minPrice IS NULL or e.rentCold >= :minPrice) AND " + 
    "(:maxprice IS NULL or e.rentCold <= :maxPrice) AND " + 
    "(:type IS NULL or e.type = :type)")
    List<Estate> findByFilters(@Param("city") String city, 
                               @Param("minPrice") Double minPrice,
                               @Param("maxPrice") Double maxPrice,
                               @Param("type") String type);
}
