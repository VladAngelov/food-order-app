import {
    RouterModule,
    Routes
} from "@angular/router";
import { LogInComponent } from "src/app/components/user/log-in/log-in.component";
import { AppConstants } from "src/app/constants/app.constants";

const USER = AppConstants.USER_URL;
const LOG_IN_URL = AppConstants.LOG_IN;

const routes: Routes = [
    {
        path: USER,
        children: [
            {
                path: LOG_IN_URL,
                component: LogInComponent,
                data: {
                    isLoggedIn: false,
                }
            }
        ]
    }
];
export const UserRoutingModule = RouterModule.forChild(routes);
