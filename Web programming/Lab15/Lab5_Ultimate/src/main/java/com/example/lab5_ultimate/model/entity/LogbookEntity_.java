package com.example.lab5_ultimate.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.sql.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LogbookEntity.class)
public abstract class LogbookEntity_ {

	public static volatile SingularAttribute<LogbookEntity, Date> endDate;
	public static volatile SingularAttribute<LogbookEntity, ReaderEntity> readerByReaderId;
	public static volatile SingularAttribute<LogbookEntity, BookEntity> bookByBookId;
	public static volatile SingularAttribute<LogbookEntity, Integer> id;

	public static final String END_DATE = "endDate";
	public static final String READER_BY_READER_ID = "readerByReaderId";
	public static final String BOOK_BY_BOOK_ID = "bookByBookId";
	public static final String ID = "id";

}

