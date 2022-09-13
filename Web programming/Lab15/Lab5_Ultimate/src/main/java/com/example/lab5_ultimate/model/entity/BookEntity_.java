package com.example.lab5_ultimate.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BookEntity.class)
public abstract class BookEntity_ {

	public static volatile SingularAttribute<BookEntity, String> author;
	public static volatile SingularAttribute<BookEntity, Integer> numOfItems;
	public static volatile SingularAttribute<BookEntity, Integer> id;
	public static volatile SingularAttribute<BookEntity, String> title;

	public static final String AUTHOR = "author";
	public static final String NUM_OF_ITEMS = "numOfItems";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

