import { ProductType } from "../enums/product-type";
import { IBase } from "./base";

export interface IProduct<T = string> extends IBase {
    name: string;
    content: string;
    volume: number;
    price: number;
    type: ProductType;
    picture: string;
}