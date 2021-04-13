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
        private DataContext _dataContext;

        public CartController(DataContext context)
        {
            _dataContext = context;
        }

        [AllowAnonymous]
        [Route("api/findUserCart")]
        [HttpPost]
        public async Task<IActionResult> FindUserCart([FromBody] AddCartModel model)
        {
            string message = "success";

            if(model.userId == "")
            {
                message = "invalid value";

                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
            }

            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();

            try
            {
                dataList = GetCartForUserFunc(model.userId);

                return await Task.Run(() => Ok(new
                {
                    message = message,
                    cartList = dataList
                }));
            }
            catch (Exception ex)
            {
                message = ex.Message;

                return await Task.Run(() => Ok(new
                {
                    message = message,
                    cartList = dataList
                }));
            }
        }

        [AllowAnonymous]
        [Route("api/addCart")]
        [HttpPost]
        public async Task<IActionResult> AddCart([FromBody] AddCartModel model)
        {
            string message = "success";
            if (model.productId == 0 || model.userId == "" || model.quantity == "")
            {
                message = "invalid value";

                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
            }

            try
            {
                Cart cart = new Cart();
                //cart.UserId = Convert.ToInt32(model.userId);
                cart.UserId = model.userId;
                cart.ProductId = model.productId;
                cart.Quantity = Convert.ToInt32(model.quantity);
                _dataContext.Carts.Add(cart);
                _dataContext.SaveChanges();
                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
            }
            catch(Exception ex)
            {
                message = ex.Message;

                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
            }
            //return BadRequest(new { message = "Cart bad request" });

        }

        [AllowAnonymous]
        [Route("api/editCart")]
        [HttpPost]
        public async Task<IActionResult> EditCart([FromBody] AddCartModel model)
        {
            string message = "success";

            if (model.quantity == "")
            {
                message = "invalid value";

                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
            }

            try
            {
                Cart cart = _dataContext.Carts.Find(model.cartId);
                cart.Quantity = Convert.ToInt32(model.quantity);
                _dataContext.SaveChanges();
                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
            }
            catch(Exception ex)
            {
                message = ex.Message;

                return await Task.Run(() => Ok(new
                {
                    message = message
                }));
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

                return await Task.Run(() => Ok(new
                {
                    message = message,
                    cartList = dataList
                }));
            }
            catch (Exception ex)
            {
                message = ex.Message;

                return await Task.Run(() => Ok(new
                {
                    message = message,
                    cartList = dataList
                }));
            }

            //return BadRequest(new { message = "Cart bad request" });

        }

        private List<Dictionary<string, object>> GetCartForUserFunc(string userId)
        {
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();
            List<Cart> cartList = new List<Cart>();

            cartList = _dataContext.Carts.Where(y => y.UserId == userId).ToList();

            foreach (var cartItem in cartList)
            {
                Dictionary<string, object> item = new Dictionary<string, object>();
                item.Add("id", cartItem.Id);
                Dictionary<string, object> pItem = new Dictionary<string, object>();

                try
                {
                    Product product = _dataContext.Products.Find(cartItem.ProductId);
                    pItem.Add("productId", product.Id);
                    pItem.Add("name", product.Name);
                    pItem.Add("photo", product.Photo);
                    pItem.Add("price", product.Price);
                    item.Add("product", pItem);
                }
                catch
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

        private List<Dictionary<string, object>> GetCartsFunc()
        {
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();
            List<Cart> cartList = new List<Cart>();

            cartList = _dataContext.Carts.ToList();

            foreach (var cartItem in cartList)
            {
                Dictionary<string, object> item = new Dictionary<string, object>();
                item.Add("id", cartItem.Id);
                Dictionary<string, object> pItem = new Dictionary<string, object>();

                try
                {
                    Product product = _dataContext.Products.Find(cartItem.ProductId);
                    pItem.Add("productId", product.Id);
                    pItem.Add("name", product.Name);
                    pItem.Add("photo", product.Photo);
                    pItem.Add("price", product.Price);
                    item.Add("product", pItem);
                }
                catch
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
                Cart cart = await _dataContext.Carts.FindAsync(model.cartId);
                _dataContext.Carts.Remove(cart);
                _dataContext.SaveChanges();
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