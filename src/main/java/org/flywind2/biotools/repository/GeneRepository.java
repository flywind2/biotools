/**
 * 
 */
package org.flywind2.biotools.repository;

import java.util.Collection;
import java.util.List;

import org.flywind2.biotools.model.gene.Gene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author SuFeng
 *
 */
public interface GeneRepository extends JpaRepository<Gene, Long> {

	@Query(value="select g from Gene g where symbol in :symbols")
	List<Gene> findByProperty(@Param("symbols") String[] value);
	
	/**
	 * find by gene symbol
	 * @param symbols
	 * @return
	 */
	List<Gene> findBySymbolIn(Collection<String> symbols);
	
	List<Gene> findByGeneIDIn(Collection<String> geneIDs);

}
