package com.quiz_online.quiz_web.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quiz_online.quiz_web.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query("SELECT DISTINCT q.subject FROM Question q")
	List<String> findDistinctSubject();

	Page<Question> findBySubject(String Subject, Pageable pageable);

}
