package com.quiz_online.quiz_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quiz_online.quiz_web.model.Question;
import com.quiz_online.quiz_web.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
	
	private final QuestionRepository questionRepository = null;

	@Override
	public Question createQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionRepository.save(question);
	}

	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	@Override
	public Optional<Question> getQuestionById(Long id) {
		// TODO Auto-generated method stub
		return questionRepository.findById(id);
	}

	@Override
	public List<String> getAllSubjects() {
		// TODO Auto-generated method stub
		return questionRepository.findDistinctSubject();
	}

	@Override
	public Question updateQuestion(Long id, Question question) throws ChangeSetPersister.NotFoundException{
		// TODO Auto-generated method stub
		Optional<Question> theQuestion = this.getQuestionById(id);
		if (theQuestion.isPresent()) {
			Question updatedQuestion = theQuestion.get();
			updatedQuestion.setQuestion(question.getQuestion());
			updatedQuestion.setChoices(question.getChoices());
			updatedQuestion.setCorrectAnswers(question.getCorrectAnswers());
			return questionRepository.save(updatedQuestion);
		} else {
			throw new ChangeSetPersister.NotFoundException();
		}
	}

	@Override
	public Void deleteQuestion(Long id) {
		// TODO Auto-generated method stub
		questionRepository.deleteById(id);
		return null;
	}

	@Override
	public List<Question> getQuestionForUser(Integer numberOfQuestions, String subject) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(0, numberOfQuestions);

		return questionRepository.findBySubject(subject, pageable).getContent();
		}

}
