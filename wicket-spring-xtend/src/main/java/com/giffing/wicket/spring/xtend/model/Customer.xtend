package com.giffing.wicket.spring.xtend.model

import com.giffing.wicket.spring.xtend.activeannotations.EntityMetaModel
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.ArrayList
import java.util.List
import javax.persistence.ManyToOne

@Entity
@Accessors
@EntityMetaModel
class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id
	String username
	String password
	String firstname
	String lastname
	boolean active
	
	@ManyToOne
	List<Group> groups = new ArrayList
	
	def addGroup(Group group){
		getGroups.add = group
	}
}

