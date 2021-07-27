import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private _authService: AuthService, private _router: Router) {}
  canActivate(): boolean {
    if (this._authService.isLogged()) {
      return true;
    } else {
      this._router.navigate(['login']);
      return false;
    }
  }
}