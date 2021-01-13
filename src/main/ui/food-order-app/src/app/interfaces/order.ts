import { IBase } from "./base";
import { IProduct } from "./product";

export interface IOrder extends IBase {
    products: IProduct[];
    userData: String;
    date: String;
    sum: number;
    address: String;
    active: boolean;
}