import {Component, OnDestroy} from '@angular/core';
import {map} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../services/storage.service";
import {BackEndService} from "../../../services/back-end.service";

@Component({
  selector: 'ics-image-container',
  templateUrl: './image-container.component.html',
  styleUrls: ['./image-container.component.scss'],
})
export class ImageContainerComponent implements OnDestroy {
  page: number;
  tags: any;
  isSearching = false;
  keyword = 'name';
  images$ = this.storageService.items
    .pipe(
      map((images) => {
        return images.content;
      })
    );
  pages$ = this.storageService.items
    .pipe(
      map((content) => {
        return new Array(content.totalPages);
      })
    );


  constructor(private activatedRoute: ActivatedRoute,
              private storageService: StorageService,
              private router: Router,
              private backEndService: BackEndService
              ) {

    if(this.router.getCurrentNavigation()?.extras.state){
      this.page = this.router.getCurrentNavigation()?.extras.state?.['page'];
    } else {
      const sessionPage = sessionStorage.getItem('page');
      this.page = sessionPage ? JSON.parse(sessionPage) : 0;
      this.storageService.page = this.page;
      this.storageService.updateInfo();
    }
  }

  ngOnDestroy(): void {
    sessionStorage.clear();
  }

  //For pagination
  setPage(i: number, event: any) {
    event.preventDefault();
    this.page = i;
    this.storageService.page = this.page;
    sessionStorage.setItem('page', JSON.stringify(this.page));
    this.storageService.updateInfo();
  }


  //For search bar
  switchSearchingMethods(tag: string) {
    if(this.isSearching) {
      this.images$ = this.storageService.allItems
        .pipe(
          map(images => {
            return images
              .filter(
                image => image.imageTags
                  .find(
                    relation => relation.tag.name === tag));
          })
        )
    }
    else {
      this.images$ = this.storageService.items
        .pipe(
          map((images) => {
            return images.content;
          })
        );
    }
  }

  onSearchChange(tag: string) {
    tag = tag.toLowerCase();
    if(tag == "") {
      this.isSearching = false;
      this.switchSearchingMethods(tag);
    }
    else {
      this.isSearching = true;
      this.switchSearchingMethods(tag);
    }
    this.getTags();
  }

  private getTags() {
    this.backEndService.getAllTags().subscribe(data => {
      this.tags = data;
    })
  }

  //For ng-autocomplete
  selectEvent(item: any) {
    // do something with selected item
    this.onSearchChange(item.name);
  }

  focusOut(e: any){
    this.onSearchChange(e.target.value);
  }

  clearInput() {
    this.onSearchChange('');
  }
}
