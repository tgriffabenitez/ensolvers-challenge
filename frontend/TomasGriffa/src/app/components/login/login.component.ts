import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private loginService:LoginService, private router:Router) { }

  public email: string = "";
  public password: string = "";


  onSumbitForm(data : any) {
    this.email = data.inputEmail;
    this.password = data.inputPassword;

    let userData = {
      "email": this.email,
      "password": this.password
    }

    this.loginService.login(userData).subscribe((res : any) => {

      // guardo los datos del cliente en el localStorage
      localStorage.setItem("userId", res);

      alert("Bienvenido!");
      this.router.navigate(['/home']);
    }, (err : any) => {
      alert("Verifique los datos ingresados");
    })


  }
}