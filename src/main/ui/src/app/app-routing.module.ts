import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { RecommendComponent } from './components/recommend/recommend.component';

const appRoutes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'recommend', component: RecommendComponent },
    { path: '',
      redirectTo: '/home',
      pathMatch: 'full'
    },
  ];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes, {useHash: true})],
    exports: [RouterModule]
})

export class AppRoutingModule{}

export const routingComponents = [HomeComponent, RecommendComponent]