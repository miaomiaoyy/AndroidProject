// import { Component, OnInit } from '@angular/core';
//
// @Component({
//   selector: 'app-register',
//   templateUrl: './register.component.html',
//   styleUrls: ['./register.component.css']
// })
// export class RegisterComponent implements OnInit {
//
//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }


import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // constructor() { }
  constructor(private router: Router) { }
  login(username: String, password: String) {
    console.log('navigate to profile');
    this.router.navigate(['/profile']);
  }
  register() {
    this.router.navigate(['/register']);
  }
  cancel() {
    this.router.navigate(['/register']);
  }
  ngOnInit() {
  }


}
