import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Image} from "../models/image-model";
import {Content} from "../models/content-model";
import {Tag} from "../models/tag-model";

@Injectable({ providedIn: 'root' })
export class BackEndService {
  api = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  getAllImages() {
    return this.httpClient.get<Image[]>(
      `${this.api}/images/all-images`);
  }

  getAllTags() {
    return this.httpClient.get<Tag[]>(
      `${this.api}/tags`);
  }

  getImages(page: number) {
    return this.httpClient.get<Content>(`${this.api}/images?page=${page}`);
  }

  getImage(id: number) {
    return this.httpClient.get<Image>(`${this.api}/images/${id}`);
  }

  addImage(url: string) {
    return this.httpClient.post<Image>(`${this.api}/images`, url);
  }
}
