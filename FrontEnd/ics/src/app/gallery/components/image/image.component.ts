import {Component, Input, OnInit} from '@angular/core';
import {Image} from "../../../models/image-model";
import {Router} from "@angular/router";

@Component({
  selector: 'ics-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent implements OnInit {

  @Input() image!: Image;
  @Input() page!: number;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public openImage(): void {
    this.router.navigate([`/gallery/${this.image.id}/${this.page}`]);
  }
}
