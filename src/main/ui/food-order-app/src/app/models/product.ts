import { ProductType } from "../enums/product-type";
import { IProduct } from "../interfaces/product";

export class Product implements IProduct {
    id: string;
    name: string;
    content: string;
    volume: number;
    price: number;
    type: ProductType;
    picture: string;
    constructor() { }
}
