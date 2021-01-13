import { IOrder } from "../interfaces/order";
import { IProduct } from "../interfaces/product";

export class Order implements IOrder {
    products: IProduct<string>[];
    userData: String;
    date: String;
    sum: number;
    address: String;
    active: boolean;
    id: string;

    constructor() { }
}