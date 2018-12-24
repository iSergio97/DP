
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private WarrantyRepository	warrantyRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public WarrantyService() {
		super();
	}

	// Methods ----------------------------------------------------------------

	public Warranty create() {
		Warranty warranty = new Warranty();
		List<String> applicableLaws = new ArrayList<>();
		warranty.setApplicableLaws(applicableLaws);
		warranty.setTerms("");
		warranty.setTitle("");

		return warranty;
	}

	public Warranty save(final Warranty warranty) {
		Assert.isTrue(warranty != null);
		return this.warrantyRepository.save(warranty);
	}

	public Iterable<Warranty> save(final Iterable<Warranty> warranties) {
		Assert.isTrue(warranties != null);
		return this.warrantyRepository.save(warranties);
	}

	public void delete(final Warranty warranty) {
		Assert.isTrue(warranty != null);
		this.warrantyRepository.delete(warranty);
	}

	public void delete(final Iterable<Warranty> warranties) {
		Assert.isTrue(warranties != null);
		this.warrantyRepository.delete(warranties);
	}

	public Warranty findById(final int id) {
		return this.warrantyRepository.findOne(id);
	}

	public List<Warranty> findAll() {
		return this.warrantyRepository.findAll();
	}

	// Specific Methods ----------------------------------------------------------------

}
