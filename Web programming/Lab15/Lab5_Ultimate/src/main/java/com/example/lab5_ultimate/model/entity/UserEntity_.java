package com.example.lab5_ultimate.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

	public static volatile SingularAttribute<UserEntity, String> password;
	public static volatile SingularAttribute<UserEntity, String> role;
	public static volatile SingularAttribute<UserEntity, String> name;
	public static volatile SingularAttribute<UserEntity, Integer> id;

	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String NAME = "name";
	public static final String ID = "id";

}

