import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit{

  qId: any;
  qTitle: any;
  quesId:any;


  questions=[
    {
      quesId:'',
      content:'',
      option1:'',
      option2:'',
      option3:'',
      option4:'',
      answer:'',

      
    }

  ];


  constructor(private _router:ActivatedRoute,private _question:QuestionService,private _snack:MatSnackBar){}
  ngOnInit(): void {
    this.qId=this._router.snapshot.params['qId'];
    this.qTitle=this._router.snapshot.params['title'];
    //console.log(this.qId);
    //console.log(this.qTitle);
    this._question.getQuestionsOfQuiz(this.qId).subscribe(
      (data:any)=>{
        console.log(data);
        this.questions=data;
        
      },
      (error)=>{
        console.log(error);
        
      }
    )
    
    
    
  }
  //delete question
  deleteQuestion(qId:any){
    //alert(qId);
    Swal.fire({
      icon:'info',
      showCancelButton:true,
      confirmButtonText:'Delete',
      title:"Are you sure, want to delete this question?"
    }).then((result)=>{
      if(result.isConfirmed){
        //configm
        this._question.deleteQuestion(qId).subscribe(
          (data:any)=>{
            this._snack.open('Question Deleted','Ok',{
              duration:3000
            });
            this.questions=this.questions.filter((q)=>q.quesId!=qId);
            
          },
          (error)=>{
            this._snack.open('Error in deleting question!!',"Ok",{
              duration:3000
            });
            console.log(error);
            

          }
        )
      }
    })


  }
 
  

}
