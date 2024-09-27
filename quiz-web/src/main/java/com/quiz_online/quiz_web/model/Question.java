package com.quiz_online.quiz_web.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter //get rid of getters and setters via lombock
@Setter
@Entity
public class Question {
	//define a primary key
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotBlank // NotBlank means cannot be empty or not specified
private String question;
@NotBlank
private String subject;
@NotBlank
private String questionType;

@NotBlank
@ElementCollection // ElementCollection means it will be its own Table in the database
private List<String> choices;

@NotBlank
@ElementCollection
private List<String> correctAnswers;

public Object getQuestion() {
	// TODO Auto-generated method stub
	return null;
}

public Object getChoices() {
	// TODO Auto-generated method stub
	return null;
}

public Object getCorrectAnswers() {
	// TODO Auto-generated method stub
	return null;
}

public void setQuestion(Object question2) {
	// TODO Auto-generated method stub
	
}

public void setChoices(Object choices2) {
	// TODO Auto-generated method stub
	
}

public void setCorrectAnswers(Object correctAnswers2) {
	// TODO Auto-generated method stub
	
}
}
