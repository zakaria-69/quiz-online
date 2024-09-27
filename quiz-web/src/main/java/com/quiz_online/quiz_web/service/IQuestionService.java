package com.quiz_online.quiz_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.quiz_online.quiz_web.model.Question;

public interface IQuestionService {
	// Create a question(create)
	Question createQuestion(Question question);
	//Get all the questions (getAll)
	List<Question> getAllQuestions();
	//Get a single Question By Id (getOne)
	Optional<Question> getQuestionById(Long id);
	// Get all subjects (getAll)
	List<String> getAllSubjects();
	//update a question (patch / update)
	Question updateQuestion(Long id,Question question) throws NotFoundException;
	//Delete a question by id (delete)
	Void deleteQuestion(Long id);
	// get questions for user (read)
	List<Question> getQuestionForUser(Integer numberOfQuestions, String subjects);
}
