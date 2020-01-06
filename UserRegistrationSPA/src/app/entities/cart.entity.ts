import { Product } from './product.entity';

export class Cart {
    id: number;
    product: Product;    
    quantity: number;
}