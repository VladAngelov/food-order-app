import {
  Routes,
  RouterModule
} from '@angular/router';
import { ContactsComponent } from './components/contacts/contacts/contacts.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AppConstants } from './constants/app.constants';


const HOME = AppConstants.HOME_URL;
const CONTACTS = AppConstants.CONTACTS;

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: `${HOME}`
      },
      {
        path: `${HOME}`,
        component: HomeComponent,
        data: {
          title: 'Начало'
        }
      },
      {
        path: `${CONTACTS}`,
        component: ContactsComponent
      },
      {
        path: '**',
        component: NotFoundComponent,
        data: {
          title: '404'
        }
      }
    ]
  },
];
export const AppRoutingModule = RouterModule.forRoot(routes);
