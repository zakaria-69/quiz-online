package com.quiz_online.quiz_web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz_online.quiz_web.model.Question;
import com.quiz_online.quiz_web.service.IQuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuestionController {
private final IQuestionService questionService = null;
// CREATE
@PostMapping("/create-new-question")
public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question){
	Question createdQuestion = questionService.createQuestion(question);
	return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
}
// GET ALL
@GetMapping("/all-questions")
public ResponseEntity<List<Question>> getAllQuestions(){
	List<Question> questions = questionService.getAllQuestions();
	return ResponseEntity.ok(questions);
}
//GET ONE
@GetMapping("/question/{id}")
public ResponseEntity<Question> getQuestionById(@PathVariable Long id) throws NotFoundException{
	Optional<Question> theQuestion = questionService.getQuestionById(id);
	if (theQuestion.isPresent()) {
		return ResponseEntity.ok(theQuestion.get());
	}else {
		throw new ChangeSetPersister.NotFoundException();
	}	
}
// UPDATE
@PutMapping("/question/{id}/update")
public ResponseEntity<Question> updateQuestion(@PathVariable Long id,@RequestBody Question question) throws NotFoundException{
	//Optional<Question> theQuestion = questionService.getQuestionById(id);
	Question updatedQuestion = questionService.updateQuestion(id, question);	
	return ResponseEntity.ok(updatedQuestion);	
}
// DELETE
@DeleteMapping("/question/{id}/delete")
public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) throws NotFoundException {
	questionService.deleteQuestion(id);
	return ResponseEntity.noContent().build();
}
// GET ALL SUBJECTS
@GetMapping("/subjects")
public ResponseEntity<List<String>> getAllSubjects(){
	List<String> subjects = questionService.getAllSubjects();
	return ResponseEntity.ok(subjects);
}
// GET QUESTION FOR USER SHUFFLE THEM AND SET A MIN FOR QUESTIONS
@GetMapping("/quiz/fetch-questions-for-user")
public ResponseEntity<List<Question>> getQuestionsForUser
(@RequestParam Integer numberOfQuestions,@RequestParam String subject) {
	List<Question> allQuestions = questionService.getQuestionForUser(numberOfQuestions, subject);
	List<Question> mutableQuestions = new ArrayList<>(allQuestions);
	Collections.shuffle(mutableQuestions);
	
	int availableQuestions = Math.min(numberOfQuestions, mutableQuestions.size());
	List<Question> randomQuestions =mutableQuestions.subList(0, availableQuestions);
	return ResponseEntity.ok(randomQuestions);
	
}
}
