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
using Microsoft.AspNetCore.Authorization;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Text;
using System.Security.Claims;
using UserWebAPI.Entities;
using UserWebAPI.Services;

namespace UserWebAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("[controller]")]
    public class AccountController : ControllerBase
    {
        //private IUserService _userService;
        private readonly IMapper _mapper;
        private readonly IConfiguration _config;

        private readonly UserManager<User> _userManager;
        private readonly SignInManager<User> _signInManager;

        public AccountController(
            //IUserService userService,
            IMapper mapper,
            IConfiguration config,
            UserManager<User> userManager,
            SignInManager<User> signInManager
            )
        {
            _userManager = userManager;
            _signInManager = signInManager;
            //_userService = userService;
            _mapper = mapper;
            _config = config;
        }

        [AllowAnonymous]
        [Route("api/User/Login")]
        [HttpPost]
        public async Task <IActionResult> Login([FromBody]AccountModel model)
        {
            //var user = _userService.Login(model.UserName, model.Password);
            var user = await _userManager.FindByNameAsync(model.UserName);

            var result = await _signInManager.CheckPasswordSignInAsync(user, model.Password, false);

            if (user == null)
                return BadRequest(new { message = "Username or password is incorrect" });            

            if (result.Succeeded)
            {
                return Ok(new
                {
                    token = GenerateJwtToken(model)
                });
            }
            return Unauthorized();
        }

        [Route("api/User/Register", Name = "GetUser") ]
        [HttpPost]
        public async Task<IActionResult> Register([FromBody]AccountModel model) //add async Task<Result>
        {
            var userStore = _mapper.Map<User>(model);
            var manager = await _userManager.CreateAsync(userStore, model.Password); 
            var user = new User() { UserName = model.UserName, Email = model.Email };
            user.FirstName = model.FirstName;
            user.LastName = model.LastName;

            if (manager.Succeeded)
            {
                return CreatedAtRoute("GetUser", new { id = userStore.Id }, user);
            }
            return BadRequest(manager.Errors);
        }

        private static string GenerateJwtToken (AccountModel model)
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
