package com.eros.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select last_insert_rowid()")
	@Column(name = "id")
	private Long id;

	@Column(name = "authorId")
	private Long authorId;

	@Column(name = "content")
	private String content;

	@Transient
	private User author;

	public Long getId() {
		return id;
	}

	public Message setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public Message setAuthorId(Long authorId) {
		this.authorId = authorId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Message setContent(String content) {
		this.content = content;
		return this;
	}

	public User getAuthor() {
		return author;
	}

	public Message setAuthor(User author) {
		this.author = author;
		return this;
	}
}
