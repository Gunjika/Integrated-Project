package com.capgemini.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "testId_generator")
	@SequenceGenerator(name = "testId_generator", initialValue = 101, allocationSize = 1)
	private Integer testId;

	@Column(length = 25)
	private String testName;

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Override
	public String toString() {
		return "Test [testId=" + testId + ", testName=" + testName + "]";
	}

	public Test(Integer testId, String testName) {

		this.testId = testId;
		this.testName = testName;
	}

	public Test() {

	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

}