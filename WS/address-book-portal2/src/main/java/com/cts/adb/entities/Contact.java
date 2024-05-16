package com.cts.adb.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactId;
	
	@NotNull(message = "Full Name is a mandatory field")
	@NotBlank(message = "Full Name is a mandatory field")
	@Size(min = 5,max=25,message = "Full Name must be between 5 and 25 characters in length")
	private String fullName;
	
	@NotNull(message = "Mobile Number is a mandatory field")
	@NotBlank(message = "Mobile Number is a mandatory field")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile Number must be a ten digit number")
	private String mobileNumber;
	
	@NotNull(message = "Mail Id is a mandatory field")
	@NotBlank(message = "Mail Id is a mandatory field")
	@Email(message = "Please provide a valid mail id")
	private String meilId;
	
	@NotNull(message = "Date of Birth is a mandatory field")
	@PastOrPresent(message = "Date Of Birth can not be a future date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfBith;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer contactId, String fullName, String mobileNumber, String meilId, LocalDate dateOfBith) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
		this.meilId = meilId;
		this.dateOfBith = dateOfBith;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMeilId() {
		return meilId;
	}

	public void setMeilId(String meilId) {
		this.meilId = meilId;
	}

	public LocalDate getDateOfBith() {
		return dateOfBith;
	}

	public void setDateOfBith(LocalDate dateOfBith) {
		this.dateOfBith = dateOfBith;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", fullName=" + fullName + ", mobileNumber=" + mobileNumber
				+ ", meilId=" + meilId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactId, fullName, meilId, mobileNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return contactId == other.contactId && Objects.equals(fullName, other.fullName)
				&& Objects.equals(meilId, other.meilId) && Objects.equals(mobileNumber, other.mobileNumber);
	}
	
	
}
