using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using System;
using System.Linq;
using UserWebAPI.Entities;
using UserWebAPI.Models;

public class YourDbContextSeedData
{
    private DataContext _context;

    public YourDbContextSeedData(DataContext context)
    {
        _context = context;
    }

    public async void SeedAdminUser()
    {
        var user = new User
        {
            UserName = "admin",
            NormalizedUserName = "ADMIN",
            Email = "admin@email.com",
            NormalizedEmail = "email@email.com",
            EmailConfirmed = true,
            Role = 0,
            LockoutEnabled = false,
            SecurityStamp = Guid.NewGuid().ToString()
        };

        if (!_context.Users.Any(u => u.UserName == user.UserName))
        {
            var password = new PasswordHasher<User>();
            var hashed = password.HashPassword(user, "password");
            user.PasswordHash = hashed;
            _context.Users.Add(user);
            //await userStore.AddToRoleAsync(user, "admin");
        }

        await _context.SaveChangesAsync();
    }
}