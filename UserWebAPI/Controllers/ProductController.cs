﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using UserWebAPI.Entities;
using UserWebAPI.Models;

namespace UserWebAPI.Controllers
{
    public class ProductController : ControllerBase
    {
        private DataContext _dataContext;

        public ProductController(DataContext context)
        {
            _dataContext = context;
        }

        [AllowAnonymous]
        [Route("api/getProducts")]
        [HttpPost]
        public async Task<IActionResult> GetProducts(string searchStr) //([FromBody] SearchModel model)
        {          
            string message = "success";
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();
            List<Product> productList = new List<Product>();

            try
            {
                if (string.IsNullOrEmpty(searchStr))
                {
                    productList = await _dataContext.Products.ToListAsync();
                } else
                {
                    productList = await _dataContext.Products.Where(t => t.Name.Contains(searchStr)).ToListAsync();
                }
                

                foreach (var productItem in productList)
                {
                    Dictionary<string, object> item = new Dictionary<string, object>();
                    item.Add("Id", productItem.Id);
                    item.Add("Name", productItem.Name);
                    item.Add("Description", productItem.Description);
                    item.Add("Price", productItem.Price);
                    item.Add("Photo", productItem.Photo);
                    item.Add("Quantity", productItem.Quantity);
                    dataList.Add(item);
                }

                return Ok(new
                {
                    message = message,
                    productList = dataList
                });
            }
            catch(Exception ex)
            {
                message = ex.Message;
                
                return Ok(new
                {
                    message = message,
                    productList = dataList
                });
            }

            //return BadRequest(new { message = "There is no order status" });

        }
    }
}