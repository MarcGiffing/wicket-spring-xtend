package com.giffing.wicket.spring.xtend.repository.services.group

import org.springframework.data.repository.CrudRepository
import com.giffing.wicket.spring.xtend.model.Group

interface GroupRepository extends CrudRepository<Group, Long> {
	
}