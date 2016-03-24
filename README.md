# wicket-spring-xtend

Playing around with xtend and xtends code generation via active annotations.

The aim of this project is to reduce some boilerplate code which can automatically generated
with the help of xtend and xtends active annotation


## Active annotations:

### Generation of JPA Meta Model

```java
@Entity //JPA Entity
@Accessors //Getter Setter creation
@EntityMetaModel //Creation of JPA Code Model - Active annotation
class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id
	String username
}
```	

Creates a 'Customer_' class without usigin the [hibernate-jpamodelgen](https://docs.jboss.org/hibernate/orm/5.0/topical/html/metamodelgen/MetamodelGenerator.html)

```java
public class Customer_ {
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, String> username;
	
}
```	

### Generation of Spring Data JPA Specifications with given Entity.

Automatic creation of Springs static [Specification](http://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#specifications) methods.

```java
@Specs(Customer) //Active annotation to create static Specification methods
class CustomerSpecs {
}
```

Which results in:

```java
	public static final Specification<Customer> usernameEquals(final String value) {
    return new Specification<Customer>() {
    	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    		return cb.equal(root.get(Customer_.username), value);
    	}
    };
  }
  
  public static final Specification<Customer> usernameEqualsIgnoreCase(final String value) {
    return new Specification<Customer>() {
    	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    		return cb.equal(cb.lower(root.get(Customer_.username)), value);
    	}
    };
  }
  
  public static final Specification<Customer> usernameLike(final String value);
  public static final Specification<Customer> usernameLikeIgnoreCase(final String value);
  public static final Specification<Customer> usernameNotLike(final String value);
  public static final Specification<Customer> usernameNotLikeIgnoreCase(final String value);
  public static final Specification<Customer> usernameIsNull();
```

And can be used:

```java

import static package.CustomerSpecs.*

class CustomerRepositoryServiceImpl implements CustomerRepositoryService {
	
	@Inject
	CustomerRepository repo
	}

	override List<Customer> findAll() {
		var it = where(usernameEquals(""))
		repo.findAll(
			and(firstnameEquals("asd"))
		)
	}
}
```
