import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImagePageComponent } from './image-page/image-page.component';
import {ImagePageRoutingModule} from "./image-page-routing.module";



@NgModule({
  declarations: [
    ImagePageComponent
  ],
  imports: [
    CommonModule,
    ImagePageRoutingModule
  ]
})
export class ImagePageModule { }
