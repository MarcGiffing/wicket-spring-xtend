package com.giffing.wicket.spring.xtend.repository.services.customer

import com.giffing.wicket.spring.xtend.model.Customer
import java.util.List
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.CrudRepository

interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	def List<Customer> findAll(Specification<Customer> specs)

	def int countByUsernameIgnoreCase(String username)

	def Customer findByUsernameIgnoreCase(String username)
	
}
