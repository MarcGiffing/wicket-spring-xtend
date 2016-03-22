package com.giffing.wicket.spring.xtend.repository.services.customer.impl

import com.giffing.wicket.spring.xtend.model.Customer
import com.giffing.wicket.spring.xtend.repository.services.customer.CustomerRepository
import com.giffing.wicket.spring.xtend.repository.services.customer.CustomerRepositoryService
import java.util.List
import javax.inject.Inject
import org.springframework.stereotype.Repository

import static com.giffing.wicket.spring.xtend.repository.services.customer.specs.CustomerSpecs.*
import static org.springframework.data.jpa.domain.Specifications.*

@Repository
class CustomerRepositoryServiceImpl implements CustomerRepositoryService {

	CustomerRepository repo

	@Inject
	new(CustomerRepository customerRepository) {
		this.repo = customerRepository
	}

	override void save(Customer customer) {
		repo.save(customer)
	}

	override Customer findByUsername(String username) {
		repo.findByUsernameIgnoreCase(username)
	}

	override void delete(Long id) {
		repo.delete(id)
	}

	override List<Customer> findAll() {
		repo.findAll(
			where(
				usernameLike("yea")
			).and(
				firstnameEquals("asd")
			)
		)
	}

	override long count() {
		repo.count
	}

}
