package com.brother.exams.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@CreatedBy
	protected U createdBy;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;

	@LastModifiedBy
	protected U lastModifiedBy;

	@LastModifiedDate
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;

}
