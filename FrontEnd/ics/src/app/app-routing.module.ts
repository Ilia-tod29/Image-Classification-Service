import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ImageContainerComponent} from "./gallery/components/image-container/image-container.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found/page-not-found.component";
import {AnalyserComponent} from "./home-page/analyser/analyser.component";

const routes: Routes = [
  {
    path: '',
    component: AnalyserComponent
  },
  {
    path: 'gallery',
    component: ImageContainerComponent
  },
  {
    path: 'gallery/:id/:page',
    loadChildren: () => import('./image-page/image-page.module').then((m) => m.ImagePageModule)
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
