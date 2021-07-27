import { Injectable } from '@angular/core';
const KEYTOKEN = 'auth-token';
const KEYUSER = 'auth-user';
@Injectable({
  providedIn: 'root',
})
export class TokenStorageService {
  constructor() {}

  signOut(): void {
    window.localStorage.clear();
  }

  saveToken(token: string): void {
    window.localStorage.removeItem(KEYTOKEN);
    window.localStorage.setItem(KEYTOKEN, token);
  }

  getToken(): string {
    return localStorage.getItem(KEYTOKEN); //non-null assertion operator
  }

  saveUser(user: any): void {
    window.localStorage.removeItem(KEYUSER);
    window.localStorage.setItem(KEYUSER, JSON.stringify(user));
  }

  getUser(): any {
    return JSON.parse(localStorage.getItem(KEYUSER));
  }
}