package com.example.lab5_ultimate.model.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReaderEntity.class)
public abstract class ReaderEntity_ {

	public static volatile SingularAttribute<ReaderEntity, String> name;
	public static volatile SingularAttribute<ReaderEntity, Integer> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}

