import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { LoginService } from './login.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private login: LoginService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):

  | Observable<boolean | UrlTree>
  | Promise<boolean | UrlTree>
  | boolean
  | UrlTree {
    console.log('AdminGuard is being executed.');

    if (this.login.isLoggedIn() && this.login.getUserRole() === 'ADMIN') {
      return true;
    }
    console.log('User is not logged in or not an ADMIN. Navigating to login page.');
    this.router.navigate(['login']);
    return false;
  }
}

