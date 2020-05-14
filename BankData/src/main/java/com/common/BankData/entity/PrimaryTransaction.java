package com.common.BankData.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PrimaryTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Date date;

	@Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime localDateTime;

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	private String description;

	private String type;
	private String status;
	private double amount;
	@Transient
	private BigDecimal availableBalance;

	private String recipientName;
	private long recipientAccountNo;
	@Transient
	private long phoneNo;






	private long accountId;


	public long getRecipientAccountNo() {
		return recipientAccountNo;
	}

	public void setRecipientAccountNo(long recipientAccountNo) {
		this.recipientAccountNo = recipientAccountNo;
	}

//	@ManyToOne
//	@JoinColumn(name = "primary_account_id")
//	@Transient
//	private Account primaryAccount;

	public PrimaryTransaction() {

	}

	public PrimaryTransaction(Date date, String description, String status, double amount, String recipientName, long recipientAccountNo, long accountId, LocalDateTime localDateTime, String type) {
		this.date = date;
		this.description = description;
		this.status = status;
		this.amount = amount;
		this.recipientName = recipientName;
		this.recipientAccountNo = recipientAccountNo;
		this.accountId = accountId;
		this.localDateTime=localDateTime;
		this.type=type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

//	public Account getPrimaryAccount() {
//		return primaryAccount;
//	}
//
//	public void setPrimaryAccount(Account primaryAccount) {
//		this.primaryAccount = primaryAccount;
//	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}


	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
}
