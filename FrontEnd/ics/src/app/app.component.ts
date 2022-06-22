import {Component, ViewEncapsulation} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'ics-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{
  title = 'ics';

  isDarkTheme: boolean;
  constructor(private router: Router) {
    const theme = localStorage.getItem('theme');
    if(theme){
      this.isDarkTheme = theme == 'dark';
    }
    else {
      this.isDarkTheme = false;
    }
  }

  toddleTheme() {
    if (!this.isDarkTheme) {
      localStorage.setItem('theme', 'dark')
    } else {
      localStorage.setItem('theme', 'light')
    }
  }
}
