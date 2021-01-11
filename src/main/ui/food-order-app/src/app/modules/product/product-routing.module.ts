import { RouterModule, Routes } from "@angular/router";
import { ProductAddComponent } from "src/app/components/product/product-add/product-add.component";
import { ProductEditComponent } from "src/app/components/product/product-edit/product-edit.component";
import { ProductListComponent } from "src/app/components/product/product-list/product-list.component";
import { AppConstants } from "src/app/constants/app.constants";

const MENU = AppConstants.PRODUCT_URL;
const EDIT = AppConstants.EDIT_BY_ID;
const ADD = AppConstants.ADD_URL;

const routes: Routes = [
    {
        path: MENU,
        children: [
            {
                path: '',
                pathMatch: 'full',
                component: ProductListComponent
            },
            {
                path: ADD,
                component: ProductAddComponent
            },
            {
                path: EDIT,
                component: ProductEditComponent
            }
        ]
    }
];
export const ProductRoutingModule = RouterModule.forChild(routes);
