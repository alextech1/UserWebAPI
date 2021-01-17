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

namespace UserWebAPI.Controllers
{
    public class OrderStatusController : ControllerBase
    {
        private DataContext dataContext;
        private PushNotificationLogic pushNotificationLogic;

        public OrderStatusController(DataContext context)
        {
            this.dataContext = context;
            this.pushNotificationLogic = new PushNotificationLogic(context);
        }



        [AllowAnonymous]
        [Route("api/getOrderStatus")]
        [HttpPost]
        public async Task<IActionResult> GetOrderStatus([FromBody] string UserId)
        {
            //string title = "";
            //string body = "";
            object data = "";

            List<OrderStatus> orderStatus = dataContext.OrderStatus.ToList();
            User userItem = dataContext.Users.Find(UserId);
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
                string title = "Add Order Status";
                string body = "Success";
                string UserName = "";

                List<OrderStatus> orderStatus = dataContext.OrderStatus.ToList();
                User user = dataContext.Users.Find(model.UserId);
                UserName = user.UserName;

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
                // Use this for Android devices:
                // this.pushNotificationLogic.PushNotification(body, title, UserName);

                // Alternative pushnotification
                // var pushSent = await PushNotificationLogic.SendPushNotification(tokens, title, body, data);

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
