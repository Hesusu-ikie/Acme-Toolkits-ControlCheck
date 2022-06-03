package acme.features.administrator.administratorDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAdministratorDashboardRepository extends AbstractRepository {

	// COMPONENT ----------------------------------------------------------------------------------------------------
	
	@Query("select count(c) from Invention c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT")
	Double findNumberComponents();
	
	@Query("select concat(c.technology,':', c.retailPrice.currency,':', avg(c.retailPrice.amount)) from Invention c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT group by c.technology, c.retailPrice.currency")
	List<String> findAverageRetailPriceComponent();
	
	@Query("select concat(c.technology,':', c.retailPrice.currency,':',stddev(c.retailPrice.amount)) from Invention c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT group by c.technology, c.retailPrice.currency")
	List<String> findDeviationRetailPriceComponent();
	
	@Query("select concat(c.technology,':', c.retailPrice.currency,':', min(c.retailPrice.amount)) from Invention c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT group by c.technology, c.retailPrice.currency")
	List<String> findMinimumRetailPriceComponent();
	
	@Query("select concat(c.technology,':', c.retailPrice.currency,':', max(c.retailPrice.amount)) from Invention c where c.inventionType=acme.entities.inventions.InventionType.COMPONENT group by c.technology, c.retailPrice.currency")
	List<String> findMaximumRetailPriceComponent();
	
	// TOOLS ----------------------------------------------------------------------------------------------------
	
	@Query("select count(t) from Invention t where t.inventionType=acme.entities.inventions.InventionType.TOOL")
	Double findNumberTools();

	@Query("select concat(t.retailPrice.currency, ':', avg(t.retailPrice.amount)) from Invention t where t.inventionType=acme.entities.inventions.InventionType.TOOL group by t.retailPrice.currency")
	List<String> findAverageRetailPriceTools();
	
	@Query("select concat(t.retailPrice.currency, ':', stddev(t.retailPrice.amount)) from Invention t where t.inventionType=acme.entities.inventions.InventionType.TOOL group by t.retailPrice.currency")
	List<String> findDeviationRetailPriceTools();
	
	@Query("select concat(t.retailPrice.currency, ':', min(t.retailPrice.amount)) from Invention t where t.inventionType=acme.entities.inventions.InventionType.TOOL group by t.retailPrice.currency")
	List<String> findMinimumRetailPriceTools();
	
	@Query("select concat(t.retailPrice.currency,':', max(t.retailPrice.amount)) from Invention t where t.inventionType=acme.entities.inventions.InventionType.TOOL group by t.retailPrice.currency")
	List<String> findMaximumRetailPriceTools();
	
	// PATRONAGES ----------------------------------------------------------------------------------------------------
	
	@Query("select concat(p.status, ':', count(p)) from Patronage p group by p.status")
	List<String> findNumberPatronages();
	
	@Query("select concat(p.status, ':',avg(p.budget.amount)) from Patronage p group by p.status")
	List<String> findAverageBudgetPatronage();
	
	@Query("select concat(p.status, ':',stddev(p.budget.amount)) from Patronage p group by p.status")
	List<String> findDeviationBudgetPatronage();
	
	@Query("select concat(p.status, ':',min(p.budget.amount)) from Patronage p group by p.status")
	List<String> findMinimumBudgetPatronage();
	
	@Query("select concat(p.status, ':',max(p.budget.amount)) from Patronage p group by p.status")
	List<String> findMaximumBudgetPatronage();
	
	// Peman ----------------------------------------------------------------------------------------------------
	
	@Query("SELECT count(p) from Peman p where p.invention.inventionType=acme.entities.inventions.InventionType.TOOL")
	Double findInventionsWithPeman();
	
	@Query("SELECT concat(p.expenditure.currency,':', avg(p.expenditure.amount)) FROM Peman p GROUP BY p.expenditure.currency")
	List<String> findAverageExpenditurePeman();
	
	@Query("SELECT concat(p.expenditure.currency,':', stddev(p.expenditure.amount)) FROM Peman p GROUP BY p.expenditure.currency")
	List<String> findDeviationExpenditurePeman();
	
	@Query("SELECT concat(p.expenditure.currency,':', min(p.expenditure.amount)) FROM Peman p GROUP BY p.expenditure.currency")
	List<String> findMinimumExpenditurePeman();
	
	@Query("SELECT concat(p.expenditure.currency,':', max(p.expenditure.amount)) FROM Peman p GROUP BY p.expenditure.currency")
	List<String> findMaximumExpenditurePeman();
	
	
}
