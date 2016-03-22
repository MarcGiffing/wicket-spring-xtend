package com.giffing.wicket.spring.xtend.repository.services.customer

import com.giffing.wicket.spring.xtend.model.Customer
import java.util.List

interface CustomerRepositoryService {

	def List<Customer> findAll()

	def void save(Customer customer)

	def Customer findByUsername(String username)
	
	def long count()
	
	def void delete(Long id)
}
