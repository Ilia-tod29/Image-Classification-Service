import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImageComponent } from './components/image/image.component';
import { ImageContainerComponent } from './components/image-container/image-container.component';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {ClrDatagridModule, ClrInputModule} from "@clr/angular";
import {AutocompleteLibModule} from "angular-ng-autocomplete";



@NgModule({
  declarations: [
    ImageComponent,
    ImageContainerComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    ClrDatagridModule,
    ClrInputModule,
    AutocompleteLibModule
  ],
  exports: [
    ImageContainerComponent
  ]
})
export class GalleryModule { }
