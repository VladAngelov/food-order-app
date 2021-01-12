import {
    RouterModule,
    Routes
} from "@angular/router";

import { OrderAddComponent } from "src/app/components/order/order-add/order-add.component";
import { OrderEditComponent } from "src/app/components/order/order-edit/order-edit.component";
import { OrderListComponent } from "src/app/components/order/order-list/order-list.component";
import { AppConstants } from "src/app/constants/app.constants";

const ORDER = AppConstants.ORDER_URL;
const EDIT = AppConstants.EDIT_BY_ID;
const ALL = AppConstants.ALL_URL;

const routes: Routes = [
    {
        path: ORDER,
        children: [
            {
                path: '',
                pathMatch: 'full',
                component: OrderAddComponent
            },
            {
                path: EDIT,
                component: OrderEditComponent
            },
            {
                path: ALL,
                component: OrderListComponent
            }
        ]
    }
];
export const OrderRoutingModule = RouterModule.forChild(routes);
