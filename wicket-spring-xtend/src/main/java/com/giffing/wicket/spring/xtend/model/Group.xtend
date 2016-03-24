package com.giffing.wicket.spring.xtend.model

import com.giffing.wicket.spring.xtend.activeannotations.EntityMetaModel
import javax.persistence.Entity
import org.eclipse.xtend.lib.annotations.Accessors

@Entity
@Accessors
@EntityMetaModel
class Group {
	
	Long id
	String name
	
}