import { Cart } from '../entities/cart.entity';

export class CartRes {
    message: string;
    cartList: Cart[];
}