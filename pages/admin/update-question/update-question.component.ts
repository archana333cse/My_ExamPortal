import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrls: ['./update-question.component.css']
})
export class UpdateQuestionComponent implements OnInit{
  //quesId: any;
  constructor(private _route:ActivatedRoute, private _question:QuestionService,private _router:Router, private _quiz:QuizService){

  }
  quesId=0;
  question:any;
  quiz:any
  ngOnInit(): void {
    this.quesId=this._route.snapshot.params['quesId'];
    //alert(this.quesId);
    this._question.getQuestion(this.quesId).subscribe(
      (data:any)=>{
        this.question=data;
        console.log(this.question);

      },
      (error)=>{
        console.log(error);
        
      }
    );
    this._quiz.quizzes().subscribe(
      (data:any)=>{
        this.quiz=data;
      },
      (error)=>{
        alert("Error in loading categories!!");
      }
    )


    
    
  }
  public updateQuestion(){
    this._question.updateQuestion(this.quiz).subscribe(
      (data)=>{
        Swal.fire("Success!",'Question Updated','success').then((e)=>{
          this._router.navigate(['/admin/question'])
        })
      },
      (error)=>{
        Swal.fire('Error!!','Error in updating question','error');
      }
    );

    this._quiz.quizzes().subscribe(
      (data)=>
      {
        this.question=data;
      },
      (error)=>{
        alert("error in loading quizes");
      }
    )
    

  }
  //update form submit
  public updateData(){
    //alert('testing');
    this._question.updateQuestion(this.question).subscribe(
      (data:any)=>{
        Swal.fire("Success!",'Question  Updated','success').then((e)=>{
          this._router.navigate([`/admin/quizzes`]);
        })
      },
      (error)=>{
        Swal.fire('Error!!','Error in updating quiz','error');
      }
    )

  }

}
