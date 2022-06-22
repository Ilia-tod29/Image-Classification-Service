import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnalyserComponent } from './analyser/analyser.component';
import {ClrIconModule, ClrInputModule} from "@clr/angular";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [AnalyserComponent],
  imports: [
    CommonModule,
    ClrInputModule,
    ReactiveFormsModule,
    ClrIconModule
  ],
  exports: [AnalyserComponent]
})
export class AnalyseModule { }
