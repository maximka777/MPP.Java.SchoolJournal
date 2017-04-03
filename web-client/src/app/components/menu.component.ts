import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'menu-component',
  templateUrl: './templates/menu.component.html',
  styleUrls: ['./styles/menu.component.css']
})
export class MenuComponent {
  isCollapsed: Boolean = true;
  username: string;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.userSubject.subscribe(user => {
      this.username = user ? user.username : null;
    });
  }

  onLogoutClick(e) {
    e.preventDefault();
    this.authService.logout()
      .then(() => this.router.navigate(['/login']));
  }
}