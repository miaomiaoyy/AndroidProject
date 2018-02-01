import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  // constructor() { }
  constructor(private router: Router) { }
  login(username: String, password: String) {
    console.log('navigate to profile');
    this.router.navigate(['/profile']);
  }
  register() {
    this.router.navigate(['/register']);
  }
  ngOnInit() {
  }


}
