import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  url: string = "https://ensolvers-challenge-production.up.railway.app/"


  public login(data: any) {
    return this.http.post(this.url + "/login", data);
  }


}
