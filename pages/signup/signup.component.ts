import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
//import { of } from 'rxjs';
import swal from 'sweetalert2'; 

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
  constructor(private userService: UserService, private snack: MatSnackBar){}

  // ngOnInit(): void{}
   public user={
    username: '',
    password: '',
    firstname:'',
    lastname:'',
    email:'',
    phone:'',

   };

   ngOnInit(): void{};

   formSubmit()
    {
      console.log(this.user);
      if(this.user.username=='' || this.user.username==null){
        //alert("user is required!! ");
        this.snack.open("Username is required!!",' ',{
          duration:3000,
        });
        return; 
      }
      //add user: userservice;
      this.userService.addUser(this.user).subscribe(
        {
          next: 
          response =>swal.fire('successfully done!','user registration is successfull', 'success'),
         // this.snack.open("Registration successfull!!"," ",{
          // duration:3000}),
          
          error:  error=>this.snack.open("User with this username already present in DB!! try with another one","OK ",{
            duration:3000
          })

        }
       

      );

    }
   

}
