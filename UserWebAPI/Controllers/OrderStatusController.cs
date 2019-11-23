using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using UserWebAPI.Dtos;
using UserWebAPI.Entities;
using UserWebAPI.Models;
using System.Configuration;
 

namespace UserWebAPI.Controllers
{
    public class OrderStatusController : ControllerBase
    {
        private DataContext dataContext;

        public OrderStatusController(DataContext context)
        {
            this.dataContext = context;
        }

        [AllowAnonymous]
        [Route("api/getOrderStatus")]
        [HttpPost]
        public async Task<IActionResult> GetOrderStatus([FromBody] int UserId)
        {
            List<OrderStatus> orderStatus = dataContext.OrderStatus.ToList();
            for (int i = 0; i < orderStatus.Count(); i++)
            {
                if (orderStatus[i].UserId == UserId)
                {
                    string message;
                    if (orderStatus[i].MessageId == 1)
                    {
                        message = "The order is in the oven";
                    }
                    else message = "The order is out of delivery";

                    return Ok(new
                    {
                        message = message
                    });
                }
            }

            return BadRequest(new { message = "There is no order status" });

        }

        [AllowAnonymous]
        [Route("api/addOrderStatus")]
        [HttpPost]
        public async Task<IActionResult> AddOrderStatus([FromBody]Status model)
        {

            List<OrderStatus> orderStatus = dataContext.OrderStatus.ToList();
            for (int i = 0; i < orderStatus.Count(); i++)
            {
                if (orderStatus[i].UserId == model.UserId)
                    dataContext.OrderStatus.Remove(orderStatus[i]);
            }
            OrderStatus order = new OrderStatus();
            order.UserId = model.UserId;
            order.MessageId = model.MessageId;
            dataContext.OrderStatus.Add(order);
            dataContext.SaveChanges();
            return Ok(new
            {
                message = "Success"
            });

        }
    }
}
