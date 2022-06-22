import '@cds/core/input/register.js';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {BackEndService} from "../../services/back-end.service";
import {Image} from "../../models/image-model";
import { ClarityIcons, errorStandardIcon } from '@cds/core/icon';
ClarityIcons.addIcons(errorStandardIcon);

@Component({
  selector: 'ics-analyser',
  templateUrl: './analyser.component.html',
  styleUrls: ['./analyser.component.scss'],
})
export class AnalyserComponent implements OnInit {

  public imageFormGroup: FormGroup;
  public errorMessage: string | undefined;

  constructor(formBuilder: FormBuilder,
              private router: Router,
              private backEndService: BackEndService) {
    this.imageFormGroup = formBuilder.group({
      inputUrl: [null, Validators.required]
    });
  }

  ngOnInit(): void {
  }

  get imageUrl() {
    return this.imageFormGroup.get('inputUrl');
  }

  public sendUrl() {
    if(!this.imageFormGroup.invalid) {
      const url = this.imageFormGroup.get('inputUrl')?.value;
      this.backEndService.addImage(url).subscribe(
        (image: Image | undefined) => {
          this.redirect(image?.id);
        },
        (error) => {
          switch (error.status) {
            case 429: this.errorMessage = "Oops... Too many requests! Try again later."; break;
            case 500: this.errorMessage = "Oops... Cannot analyse this URL."; break;
            default: this.errorMessage = "Oops... Some error occurred!"; break;
          }
        }
      );
    }
  }

  private redirect(id: Number | undefined) {
    if(id) {
      this.router.navigate([`/gallery/${id}/0`]);
    }
  }



}
