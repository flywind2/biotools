package org.flywind2.model.messages;

import java.io.Serializable;

import javax.persistence.*;

import org.flywind2.model.messages.JdMessage;


/**
 * The persistent class for the jd_response database table.
 * 
 */
@Entity
@Table(name="jd_response")
public class JdResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="response_id", unique=true, nullable=false)
	private Long responseId;

	@Column(length=1000)
	private String text;

	//bi-directional many-to-one association to JdMessage
	@ManyToOne
	@JoinColumn(name="message_id", nullable=false)
	private JdMessage jdMessage;

	public JdResponse() {
	}

	public Long getResponseId() {
		return this.responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JdMessage getJdMessage() {
		return this.jdMessage;
	}

	public void setJdMessage(JdMessage jdMessage) {
		this.jdMessage = jdMessage;
	}

}