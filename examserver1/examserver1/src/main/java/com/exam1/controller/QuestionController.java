package com.exam1.controller;

import com.exam1.model.exam.Question;
import com.exam1.model.exam.Quiz;
import com.exam1.service.QuestionService;
import com.exam1.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    // add question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    // update question

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // get all questions of any quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
       /* Quiz quiz=new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);*/

         Quiz quiz = this.quizService.getQuiz(qid);
         Set<Question> questions = quiz.getQuestions();
         List list=new ArrayList(questions);
         if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
             list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
         }
        Collections.shuffle(list);
         return ResponseEntity.ok(list);

    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){
        Quiz quiz=new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);


        //return ResponseEntity.ok(list);

    }


    //get single question

    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }

    //delete question

    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    //eval quiz
    /*@PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);
        questions.forEach(q->{
            System.out.println(q.getGivenAnswer());
        });
        return ResponseEntity.ok("Got questions with answers.");
    }*/




}
