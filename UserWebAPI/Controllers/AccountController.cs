using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.AspNetCore.Mvc;
using UserWebAPI.Models;
using AutoMapper;
using Microsoft.Extensions.Configuration;

namespace UserWebAPI.Controllers
{
    public class AccountController : ControllerBase
    {
        private readonly IConfiguration _config;
        private readonly IMapper _mapper;
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly SignInManager<ApplicationUser> _signInManager;

        public AccountController (IConfiguration config, 
            IMapper mapper,
            UserManager<ApplicationUser> userManager,
            SignInManager<ApplicationUser> signInManager)
        {
            _userManager = userManager;
            _signInManager = signInManager;
            _mapper = mapper;
            _config = config;
        }

        [Route("api/User/Register", Name = "GetUser") ]
        [HttpPost]
        public async Task<ActionResult> Register([FromBody]AccountModel model) //add async Task<Result>
        {
            //var userStore = new UserStore<ApplicationUser>(new DataContext());
            var userStore = _mapper.Map<ApplicationUser>(model);
            //var manager = new UserManager<ApplicationUser>(userStore);
            var manager = await _userManager.CreateAsync(userStore, model.Password); 
            var user = new ApplicationUser() { UserName = model.UserName, Email = model.Email };
            //var user = _mapper.Map<ApplicationUser>(userStore);
            user.FirstName = model.FirstName;
            user.LastName = model.LastName;

            if (manager.Succeeded)
            {
                //IdentityResult result = manager.Create(user, model.Password);
                return CreatedAtRoute("GetUser", new { id = userStore.Id }, user);
            }
            return BadRequest(manager.Errors);
        }

    }
}
