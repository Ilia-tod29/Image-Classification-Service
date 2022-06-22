import { Relation } from "./relation-model";

export interface Image {
  id?: number;
  url: string;
  added_on: string;
  service: string;
  width: number;
  height: number;
  imageTags: Relation[];
}
