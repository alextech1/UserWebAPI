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
using UserWebAPI.Data;
using System.Configuration;
using System.Net.Http;
using Microsoft.EntityFrameworkCore;

namespace UserWebAPI.Controllers
{
    public class OrderStatusController : ControllerBase
    {
        private DataContext _dataContext;
        private PushNotificationLogic _pushNotificationLogic;

        public OrderStatusController(DataContext context)
        {
            _dataContext = context;
            _pushNotificationLogic = new PushNotificationLogic(context);
        }



        [AllowAnonymous]
        [Route("api/getOrderStatus")]
        [HttpPost]
        public async Task<IActionResult> GetOrderStatus([FromBody] string UserId)
        {
            //string title = "";
            //string body = "";
            object data = "";

            List<OrderStatus> orderStatus = _dataContext.OrderStatus.ToList();
            User userItem = _dataContext.Users.Find(UserId);
            //Task<User> userItem = _dataContext.Users.Where(x => x.Id == UserId).FirstOrDefaultAsync();
            string UserName = userItem.UserName;

            for (int i = 0; i < orderStatus.Count(); i++)
            {
                if (orderStatus[i].UserId == UserId)
                {
                    string message;
                    if (orderStatus[i].MessageId == 1)
                    {
                        message = "The order is being prepared";                   
                    }
                    else 
                    { 
                        message = "The order is out of delivery";                        
                    }


                    //title = "GetOrderStatus";
                    //body = message;

                    //this.pushNotificationLogic.PushNotification(body, title, UserName);

                    return await Task.Run(() => Ok(new
                    {
                        message = message
                    }));
                }
            }
            //title = "GetOrderStatus";
            //body = "There is no order status";

            //this.pushNotificationLogic.PushNotification(body, title, UserName);
            return BadRequest(new { message = "There is no order status" });

        }

        [AllowAnonymous]
        [Route("api/addOrderStatus")]
        [HttpPost]
        public async Task<IActionResult> AddOrderStatus([FromBody]Status model)
        {
            try
            {
                string title = "Order Status";
                string body = "Failed to retrieve status";
                string UserName = "";

                List<OrderStatus> orderStatus = _dataContext.OrderStatus.ToList();
                //Task<User> user = _dataContext.Users.FirstOrDefaultAsync(x => x.Id == model.UserId); //Find(model.UserId);
                User user = _dataContext.Users.Find(model.UserId);
                UserName = user.UserName;

                for (int i = 0; i < orderStatus.Count(); i++)
                {
                    if (orderStatus[i].UserId == model.UserId)
                        _dataContext.OrderStatus.Remove(orderStatus[i]);
                }

                if (model.MessageId == 1)
                {
                    body = "The order is being prepared";
                }
                else if (model.MessageId == 2)
                {
                    body = "The order is out for delivery";
                }

                OrderStatus order = new OrderStatus();                
                order.UserId = model.UserId;
                order.MessageId = model.MessageId;
                _dataContext.OrderStatus.Add(order);
                _dataContext.SaveChanges();
                // Use this for Android devices:
                _pushNotificationLogic.PushNotification(body, title, UserName);

                // Alternative pushnotification
                //var pushSent = await PushNotificationLogic.SendPushNotification(tokens, title, body, data);

                return await Task.Run(() => Ok(new
                {
                    message = "Success"
                }));
            } catch
            {
                return Ok(new
                {
                    message = "Failed"
                });
            }
        }       
    }
}
