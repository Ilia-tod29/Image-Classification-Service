import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'ics-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.scss']
})
export class PageNotFoundComponent {

  constructor(private router: Router) { }

  public goToHome(): void {
    this.router.navigate(['/']);
  }
}
