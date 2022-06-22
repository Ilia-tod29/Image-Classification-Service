import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ImagePageComponent} from "./image-page/image-page.component";

const routes: Routes = [
  {
    path: '',
    component: ImagePageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImagePageRoutingModule { }
