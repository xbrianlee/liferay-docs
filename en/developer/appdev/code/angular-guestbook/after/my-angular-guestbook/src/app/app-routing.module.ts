import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddEntryComponent }      from './add-entry/add-entry.component';
import { ViewGuestbookComponent }      from './view-guestbook/view-guestbook.component';

const routes: Routes = [
  { path: '', redirectTo: '/view-guestbook', pathMatch: 'full' },
  { path: 'add-entry', component: AddEntryComponent },
  { path: 'view-guestbook', component: ViewGuestbookComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
  {useHash: true, enableTracing: false}//added for portal migration
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ViewGuestbookComponent, AddEntryComponent] //added for portal migration