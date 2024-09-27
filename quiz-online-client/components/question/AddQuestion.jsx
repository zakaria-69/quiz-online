import React, { useState } from 'react'
import { createQuestion, getSubjects } from '../../utils/QuizService';

const AddQuestion = () => {
    const [question, setQuestion] = useState("");
    const [questionType, setQuestionType] = useState("single");
    const [choices, setChoices] = useState([""]);
    const [correctAnswers, setCorrectAnswers] = useState([""]);
    const [subject, setSubject] = useState("");
    const [newSubject, setNewSubject] = useState("");
    const [subjectOptions, setSubjectOptions] = useState([""]);

    const fetchSubjects = async () => {
        try {
            const subjectsData = await getSubjects()
            setSubjectOptions(subjectsData);
        } catch (error) {
            console.error("error fetching subjects", error);
        }
    }

    const handleAddChoices = async () => {
        const lastChoice = choices[choices.length - 1]
        const lastChoiceLetter = lastChoice ? lastChoice.charAt(0) : "A"; 
        const newChoiceLetter = String.fromCharCode(lastChoiceLetter.charCodeAt(0) + 1);
        const newChoice = `${newChoiceLetter}.`;
        setChoices([...choices, newChoice]);
    }

    const handleRemoveChoice = (index) => {
        setChoices(choices.filter((choice, i) => i !== index))
    };

    const handleChoiceChange = (index, value) => {
        setChoices(choices.map((choice, i) => ( i === index ? value : choice)));
    }

    const handleCorrectAnswerChange = (index, value) => {
        setCorrectAnswers(correctAnswers.map((answer,i) => (i === index ? value : answer)));
    }

    const handleCorrectAnswer = () => {
        setCorrectAnswers([...correctAnswers, ""]);
    }

    const handleRemoveCorrectAnswer = (index) => {
        setCorrectAnswers(correctAnswers.filter((answer, i) => (i !== index)));
    }

    const handleSubmitAnswer = async (e) => {
        e.preventDefault();
        try {
            const result = 
            {
                question,
                questionType,
                choices,
                correctAnswers : correctAnswers.map((answer) => {
                    const choiceLetter = answer.charAt(0).toUpperCase();
                    const choiceIndex = choiceLetter.charCodeAt(0) - 65;
                    return choiceIndex >= 0 && choiceIndex < choices.length ? choiceLetter : null;
                }),
                subject,
                }
                await createQuestion(result)
                setQuestion("")
                setQuestionType("single");
                setChoices([""]);
                setCorrectAnswers([""]);
                setSubject([""]);
        } catch (error) {
            console.error("error submitting answer", error)
        }
    }

    const handleAddSubject = () => {
        if (newSubject.trim() !== ""){
            setSubject(newSubject.trim());
            setSubjectOptions([...subjectOptions, newSubject.trim()]);
            setNewSubject("");
        }
    }
  return (
    <div className='container'>
        <div className='row justify-content-center'>
            <div className='col-md-6 mt-5 w-100 p-3'>
                <div className='card w-100 p-3'>
                    <div className='card-header'>
                        <h5 className='card-title'>Add New Question</h5>
                    </div>

                    <div className='card-body'>
                        <form onSubmit={handleSubmitAnswer} className='p-2'>
                            <div className='mb-3'>
                                <label htmlFor='subject' className='form-label text-info'>
                                    Selectionnez un sujet
                                </label>
                                <select
                                id="subject"
                                value={subject}
                                onChange={(e) => setSubject(e.target.value)}
                                className='form-control'
                                >
                                    <option value={""}>Selectionnez un sujet</option>
                                    <option value={"New"}>Ajoutez un nouveau sujet</option>
                                    {subjectOptions.map((option) => (
                                    <option key={option} value={option}>
                                        {option}
                                    </option>
                                ))}
                                </select>
                            </div>

                        </form>
                    </div>
                </div>

            </div>

        </div>
    </div>
  )
}

export default AddQuestion;