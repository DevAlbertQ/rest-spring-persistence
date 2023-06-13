package br.com.albert.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.albert.utils.DateUtil;

@JsonPropertyOrder({"key", "name", "gender", "birthday", "age","adderss"})
public class PersonDTO extends RepresentationModel<PersonDTO> {

	@JsonProperty("number")
	private Long key;
	private String name;
	private String address;
	private String gender;
	@JsonIgnore
	private Date birthday;
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		Integer age = DateUtil.yearsBetween(this.birthday, new Date());
		return age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
