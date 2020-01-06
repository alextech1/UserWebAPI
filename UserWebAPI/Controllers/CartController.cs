using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using UserWebAPI.Entities;
using UserWebAPI.Models;

namespace UserWebAPI.Controllers
{
    public class CartController : ControllerBase
    {
        private DataContext dataContext;

        public CartController(DataContext context)
        {
            this.dataContext = context;
        }

        [AllowAnonymous]
        [Route("api/addCart")]
        [HttpPost]
        public async Task<IActionResult> AddCart([FromBody] AddCartModel model)
        {
            string message = "success";
            if (model.productId == 0 || model.userId == 0 || model.quantity == 0)
            {
                message = "invalid value";

                return Ok(new
                {
                    message = message
                });
            }

            try
            {
                Cart cart = new Cart();
                cart.UserId = Convert.ToInt32(model.userId);
                cart.ProductId = Convert.ToInt32(model.productId);
                cart.Quantity = Convert.ToInt32(model.quantity);
                dataContext.Carts.Add(cart);
                dataContext.SaveChanges();
                return Ok(new
                {
                    message = message
                });
            }
            catch(Exception ex)
            {
                message = ex.Message;

                return Ok(new
                {
                    message = message
                });
            }
            //return BadRequest(new { message = "Cart bad request" });

        }

        [AllowAnonymous]
        [Route("api/editCart")]
        [HttpPost]
        public async Task<IActionResult> EditCart([FromBody] AddCartModel model)
        {
            string message = "success";

            try
            {
                Cart cart = dataContext.Carts.Find(model.cartId);
                cart.Quantity = model.quantity;
                dataContext.SaveChanges();
                return Ok(new
                {
                    message = message
                });
            }
            catch(Exception ex)
            {
                message = ex.Message;

                return Ok(new
                {
                    message = message
                });
            }

            //return BadRequest(new { message = "Cart bad request" });

        }

        [AllowAnonymous]
        [Route("api/getCarts")]
        [HttpPost]
        public async Task<IActionResult> GetCarts()
        {
            string message = "success";
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();

            try
            {
                dataList = GetCartsFunc();

                return Ok(new
                {
                    message = message,
                    cartList = dataList
                });
            }
            catch (Exception ex)
            {
                message = ex.Message;

                return Ok(new
                {
                    message = message,
                    cartList = dataList
                });
            }

            //return BadRequest(new { message = "Cart bad request" });

        }

        private List<Dictionary<string, object>> GetCartsFunc()
        {
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();
            List<Cart> cartList = new List<Cart>();

            cartList = dataContext.Carts.ToList();

            foreach (var cartItem in cartList)
            {
                Dictionary<string, object> item = new Dictionary<string, object>();
                item.Add("id", cartItem.Id);
                Dictionary<string, object> pItem = new Dictionary<string, object>();

                try
                {
                    Product product = dataContext.Products.Find(cartItem.ProductId);
                    pItem.Add("productId", product.Id);
                    pItem.Add("name", product.Name);
                    pItem.Add("photo", product.Photo);
                    pItem.Add("price", product.Price);
                    item.Add("product", pItem);
                }
                catch (Exception ex)
                {
                    pItem.Add("productId", 0);
                    pItem.Add("name", "");
                    pItem.Add("photo", "");
                    pItem.Add("price", 0);
                    item.Add("product", pItem);
                }

                item.Add("quantity", cartItem.Quantity);
                dataList.Add(item);
            }

            return dataList;
        }

        [AllowAnonymous]
        [Route("api/deleteCart")]
        [HttpPost]
        public async Task<IActionResult> DeleteCart([FromBody] AddCartModel model)
        {         
            string message = "success";
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();

            try
            {
                Cart cart = dataContext.Carts.Find(model.cartId);
                dataContext.Carts.Remove(cart);
                dataContext.SaveChanges();
                dataList = GetCartsFunc();
                return Ok(new
                {
                    message = message,
                    cartList = dataList
                });
            }
            catch(Exception ex)
            {
                message = ex.Message;
                dataList = GetCartsFunc();
                return Ok(new
                {
                    message = message,
                    cartList = dataList
                });
            }
            
            //return BadRequest(new { message = "Cart bad request" });

        }
    }
}