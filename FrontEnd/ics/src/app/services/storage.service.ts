import {Injectable} from '@angular/core';
import {BehaviorSubject, concatMap, interval, Subscription, tap, timer} from "rxjs";
import {Image} from "../models/image-model";
import {BackEndService} from "./back-end.service";
import {Content} from "../models/content-model";

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private _items$: BehaviorSubject<Content> = new BehaviorSubject<Content>(new Content());
  private _allItems$: BehaviorSubject<Image[]> = new BehaviorSubject<Image[]>([]);
  public page!: number;

  constructor(
    private backend: BackEndService) {
    const schedule = timer(0,10000);
    schedule.subscribe(
      () => this.getImagesHelper().subscribe()
    );
  }

  get items(): BehaviorSubject<Content> {
    return this._items$;
  }

  get allItems(): BehaviorSubject<Image[]> {
    this.backend.getAllImages().subscribe(
      data => this._allItems$.next(data)
    );
    return this._allItems$;
  }

  public addItem(url: string) {
    return this.backend.addImage(url).pipe(concatMap(() => this.getImagesHelper()));
  }

  public updateInfo() {
    this.backend.getImages(this.page).subscribe(
      data => this._items$.next(data)
    )
  }

  private getImagesHelper() {
    return this.backend.getImages(this.page).pipe(
      // When the request succeeds, update the items observable
      tap((images) => {
        this._items$.next(images);
      })
    );
  }
}
