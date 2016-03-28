package com.giffing.wicket.spring.xtend.model

import com.giffing.wicket.spring.xtend.activeannotations.EntityMetaModel
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import org.eclipse.xtend.lib.annotations.Accessors

@Entity
@Accessors
@EntityMetaModel
class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id
	String name
	
}