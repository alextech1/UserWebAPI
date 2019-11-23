using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using UserWebAPI.Dtos;
using UserWebAPI.Entities;
using UserWebAPI.Models;

namespace UserWebAPI.Controllers
{
    public class AdminController : ControllerBase
    {
        private readonly IMapper _mapper;
        private readonly UserManager<User> _userManager;
        private readonly SignInManager<User> _signInManager;

        public AdminController(
            IMapper mapper,
            UserManager<User> userManager,
            SignInManager<User> signInManager
        )
        {
            _userManager = userManager;
            _signInManager = signInManager;
            _mapper = mapper;
        }

        [AllowAnonymous]
        [Route("api/admin/login")]
        [HttpPost]
        public async Task<IActionResult> Login([FromBody]AccountModel model)
        {
            try
            {
                var user = await _userManager.FindByNameAsync(model.UserName);

                if (user == null)
                {
                    return BadRequest(new { message = "Username or password is incorrect" });
                }

                if (user.Role != 0)
                {
                    return BadRequest(new { message = "The user has no admin permission" });
                }

                var result = await _signInManager.CheckPasswordSignInAsync(user, model.Password, false);

                if (result.Succeeded)
                {
                    return Ok(new
                    {
                        token = GenerateJwtToken(model)
                    });
                }
            }
            catch (Exception e) {

            }
            return Unauthorized();
        }

        [AllowAnonymous]
        [Route("api/admin/getUsers")]
        [HttpPost]
        public async Task<IActionResult> GetUsers()
        {
            List<User> users =  _userManager.Users.ToList();
            for (int i = 0; i < users.Count(); i++)
            {
                if (users[i].Role == 0)
                    users.RemoveAt(i);
            }
            return Ok(new
            {
                users = users
            }); ;
        }

        [AllowAnonymous]
        [Route("api/admin/getUserByName")]
        [HttpPost]
        public async Task<IActionResult> GetUserByName([FromBody] String Name)
        {
            List<User> users = _userManager.Users.ToList();
            for (int i = 0; i < users.Count(); i++)
            {
                if (users[i].UserName == Name)
                {
                    return Ok(new
                    {
                        user = users[i]
                    }); ;
                }
            }
            return BadRequest(new { message = "No Such User" });
        }

        [AllowAnonymous]
        [Route("api/admin/saveRole", Name = "SaveRole")]
        [HttpPost]
        public async Task<IActionResult> SaveRole([FromBody]AccountModel model) //add async Task<Result>
        {
       
            var userStore = _mapper.Map<User>(model);
            var user = await _userManager.FindByNameAsync(model.UserName);
            user.Role = userStore.Role;
            try
            {
                var manager = await _userManager.UpdateAsync(user);

                if (manager.Succeeded)
                {
                    return Ok(new
                    {
                        result = "success"
                    });

                }
            }
            catch (Exception e)
            {
                e.ToString();
            }
            return BadRequest("");
        }

        private static string GenerateJwtToken(AccountModel model)
        {
            var claims = new List<Claim>
            {
                new Claim (ClaimTypes.NameIdentifier, model.Id.ToString()),
                new Claim (ClaimTypes.Name, model.UserName)
            };

            var secretKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("KeyForSignInSecret@1234"));
            var signinCredentials = new SigningCredentials(secretKey, SecurityAlgorithms.HmacSha256);

            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(claims),
                Expires = DateTime.Now.AddDays(1),
                SigningCredentials = signinCredentials,
                Audience = "http://localhost:5000",
                Issuer = "http://localhost:5000"
            };

            var tokenHandler = new JwtSecurityTokenHandler();
            var token = tokenHandler.CreateToken(tokenDescriptor);

            return tokenHandler.WriteToken(token);
        }
     
}
}
