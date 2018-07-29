package com.mykafka.work;

public class RandomComments {
	String body;
	Integer overallCounter;
	String recipient;
	Integer recipientCounter;
	String sender;
	long timestamp;

	public RandomComments() {

	}

	public RandomComments(String body, Integer overallCounter, String recipient, Integer recipientCounter,
			String sender, long timestamp) {
		super();
		this.body = body;
		this.overallCounter = overallCounter;
		this.recipient = recipient;
		this.recipientCounter = recipientCounter;
		this.sender = sender;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "RandomComments [body=" + body + ", overallCounter=" + overallCounter + ", recipient=" + recipient
				+ ", recipientCounter=" + recipientCounter + ", sender=" + sender + ", timestamp=" + timestamp + "]";
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getOverallCounter() {
		return overallCounter;
	}

	public void setOverallCounter(Integer overallCounter) {
		this.overallCounter = overallCounter;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Integer getRecipientCounter() {
		return recipientCounter;
	}

	public void setRecipientCounter(Integer recipientCounter) {
		this.recipientCounter = recipientCounter;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}