import { IImageModel } from "../interfaces/image";

export class ImageModel implements IImageModel {
    name: string;
    type: string;
    bytes: string;
    url: string;
    constructor() { }
}