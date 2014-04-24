package org.flywind2.biotools.model.messages;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the jd_message database table.
 * 
 */
@Entity
@Table(name="jd_message")
public class JdMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="message_id", unique=true, nullable=false)
	private Long messageId;

	@Column(length=1000)
	private String text;

	//bi-directional many-to-one association to JdResponse
	@OneToMany(mappedBy="jdMessage",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<JdResponse> jdResponses;

	public JdMessage() {
	}

	public Long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<JdResponse> getJdResponses() {
		if(this.jdResponses==null){
			this.jdResponses = new ArrayList<JdResponse>();
		}
		return this.jdResponses;
	}

	public void setJdResponses(List<JdResponse> jdResponses) {
		this.jdResponses = jdResponses;
	}

	public JdResponse addJdRespons(JdResponse jdRespons) {
		getJdResponses().add(jdRespons);
		jdRespons.setJdMessage(this);

		return jdRespons;
	}

	public JdResponse removeJdRespons(JdResponse jdRespons) {
		getJdResponses().remove(jdRespons);
		jdRespons.setJdMessage(null);

		return jdRespons;
	}

}