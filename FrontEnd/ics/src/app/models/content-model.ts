import {Image} from "./image-model";

export class Content {
  content: Image[];
  page: number;
  totalPages: number

  constructor() {
    this.content = [];
    this.totalPages = 0;
    this.page = 0;
  }
}
