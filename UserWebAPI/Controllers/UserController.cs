﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using UserWebAPI.Entities;
using UserWebAPI.Models;

namespace UserWebAPI.Controllers
{
    public class UserController : ControllerBase
    {
        private DataContext dataContext;

        public UserController(DataContext context)
        {
            this.dataContext = context;
        }



        [AllowAnonymous]
        [Route("api/setToken")]
        [HttpPost]
        public async Task<IActionResult> SetToken([FromBody] TokenModel model)
        {
            string message = "Token has been set";
            User userItem = dataContext.Users.Where(x => x.Email.CompareTo(model.UserEmail) == 0).FirstOrDefault();

            if (userItem == null)
            {
                message = "Username is invalid";
            } else
            {
                userItem.Token = model.Token;
                dataContext.SaveChanges();
            }


            return Ok(new
            {
                message = message
            });
            //return BadRequest(new { message = "There is no order status" });
        }
    }
}