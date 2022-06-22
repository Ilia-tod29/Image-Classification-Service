import {Component, OnInit} from '@angular/core';
import {Image} from "../../models/image-model";
import {map} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../services/storage.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'ics-image-page',
  templateUrl: './image-page.component.html',
  styleUrls: ['./image-page.component.scss']
})
export class ImagePageComponent implements OnInit{
  page!: number;

  content$ = this.storageService.allItems
    .pipe(
        map((images) => {
          const id = this.activatedRoute.snapshot.paramMap.get('id') || '';
          const imageToReturn: Image | undefined = images.find((x: Image) => x.id === parseInt(id));
          imageToReturn?.imageTags.sort((tag1, tag2) => tag2.confidence - tag1.confidence);
          return imageToReturn;
        })
    );

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private storageService: StorageService,
    public datePipe: DatePipe
  ) {
    const id = this.activatedRoute.snapshot.paramMap.get('page') || '';
    this.page = parseInt(id);
    this.storageService.page = this.page;
  }

  goToHome() {
    this.router.navigate(['/']);
  }
  goToGallery() {
    this.router.navigate(['/gallery'], {
      state: {page: this.page}
    });
  }

  ngOnInit(): void {
  }


  format(added_on: string): string {
    const newDate = new Date(added_on);
    const formatDate = this.datePipe.transform(newDate, "dd-MMM-YYY HH:mm:ss");
    return <string>formatDate;
  }
}
