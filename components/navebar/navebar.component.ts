import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navebar',
  templateUrl: './navebar.component.html',
  styleUrls: ['./navebar.component.css']
})
export class NavebarComponent implements OnInit {

  isLoggedIn=false;
  user=null;

  constructor(public login: LoginService){}
  ngOnInit(): void {
   // throw new Error('Method not implemented.');
   this.isLoggedIn=this.login.isLoggedIn();
   this.user=this.login.getUser()
  }

  public logout(){
    this.login.logout();
    this.isLoggedIn=false;
    this.user=null; 
    window.location.reload();
    
  }

}
