import React from 'react';
import axios from 'axios';

export const api = axios.create({
baseURL : "http//localhost/:9192/api/quizzes"
});

export const createQuestion = async (quizQuestion)=> {
    try{
        const response = await api.post("create-new-question", quizQuestion);
        return response.data;

    }catch(error){
        console.error("error creating question", error);
    }
}

export const getAllQuestions= async () => {
    try{
        const response = await api.get("/all-questions")
        return response.data;
    
    } catch(error) {
        console.error("error fetching all questions", error);
        return [];
    }
        }

        export const fetchQuizForUser = async(number, subject) => {
            try {
                const response = await api.get(`/quiz/fetch-questions-for-user?numberOfQuestions${number}$subject=${subject}`)
                return response.data;
            } catch (error) {
                console.error("error fetching quiz for user", error);
            }
        }

        export const getSubjects = async () => {
            try {
                const response = await api.get("/subjects");
                return response.data;
            } catch (error) {
                console.error("error fetching subjects", error);
            }
        }

        export const updateQuestion = async (id, question) => {
            try {
                const response = await api.put(`/question/${id}/update/`, question);
                return response.data;
            } catch (error) {
                console.error("error updating question", error);
            }
        }
        
        export const getQuestionById = async (id) => {
            try{
                const response = await api.get(`/question/${id}`);
                return response.data
            } catch(error) {
                console.error("error fetching question by id", error);
            } 
        }

        export const deleteQuestion = async (id) => {
            try {
               const response = await api.delete(`/question/${id}/delete`);
               return response.data;
            } catch (error) {
                console.error("error deleting question", error);
            }
           
        }