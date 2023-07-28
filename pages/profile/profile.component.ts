import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

interface User {
  username: string;
  id: number;
  firstname:string;
  lastname:string;
  phone: string;
  authorities: { authority: string }[];
  enabled: boolean;
  // Add other properties as needed
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements  OnInit{
  user: User | null = null;

  constructor(private login:LoginService){

  }
  ngOnInit(): void {
    this.user=this.login.getUser();
    
  }

}
