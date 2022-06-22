import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import {ClarityModule} from "@clr/angular";
import { ClarityIcons, processOnVmIcon, moonIcon, sunIcon } from '@cds/core/icon';
import {AnalyseModule} from "./home-page/analyse.module";
import {GalleryModule} from "./gallery/gallery.module";
import {PageNotFoundModule} from "./page-not-found/page-not-found.module";
import {HttpClientModule} from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {AutocompleteLibModule} from "angular-ng-autocomplete";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {FormsModule} from "@angular/forms";

ClarityIcons.addIcons(processOnVmIcon, moonIcon, sunIcon);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ClarityModule,
    AutocompleteLibModule,
    AnalyseModule,
    GalleryModule,
    PageNotFoundModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    FormsModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
